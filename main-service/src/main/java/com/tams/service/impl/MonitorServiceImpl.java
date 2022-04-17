package com.tams.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tams.base.redis.RedisService;
import com.tams.base.redis.util.RedisConstant;
import com.tams.base.websocket.WSService;
import com.tams.base.websocket.model.UserWsModel;
import com.tams.domain.Monitor;
import com.tams.domain.Student;
import com.tams.domain.Teacher;
import com.tams.enums.RoleEnum;
import com.tams.mapper.MonitorMapper;
import com.tams.model.LoginModel;
import com.tams.model.SysUser;
import com.tams.service.MonitorService;
import com.tams.service.StudentService;
import com.tams.service.TeacherService;
import com.tams.util.SysUserContextHandler;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author swiChen
 * @date 2022/3/7
 **/
@Service
@Slf4j
public class MonitorServiceImpl extends ServiceImpl<MonitorMapper, Monitor> implements MonitorService {

    private static final String[] ADDR_HEADER = { "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
            "X-Real-IP" };

    private static final String NUKNOWN = "unknown";

    @Resource
    private TeacherService teacherService;

    @Resource
    private StudentService studentService;

    @Resource
    private RedisService redisService;

    @Resource
    private WSService wsService;

    @Override
    public void recordLogin(LoginModel login) {
        ServletRequestAttributes requestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("user-agent"));
        Monitor onlineUser = new Monitor();
        onlineUser.setBrowser(userAgent.getBrowser().getName());
        onlineUser.setDevice(userAgent.getOperatingSystem().getDeviceType().getName());
        onlineUser.setOperationSystem(userAgent.getOperatingSystem().getName());
        onlineUser.setRole(login.getRole());
        onlineUser.setLoginTime(new Date());
        Long uId = Long.valueOf(login.getUsername());
        onlineUser.setUId(Long.valueOf(login.getUsername()));

        String addrIp = getRemoteAddr(request);
        onlineUser.setIp(addrIp);

        if (login.getRole() == RoleEnum.STUDENT){
            Student student = studentService.getById(uId);
            onlineUser.setName(student.getName());
        }else {
            Teacher teacher = teacherService.getById(uId);
            onlineUser.setName(teacher.getName());
        }


            Monitor onlineUserIn = this.lambdaQuery().eq(Monitor::getUId , uId).one();
            if (ObjectUtil.isNotEmpty(onlineUserIn)){
                onlineUser.setId(onlineUserIn.getId());
                this.updateById(onlineUser);
                return;
            }
            this.save(onlineUser);

    }

    @Override
    public Boolean offline(List<Monitor> onlines) {
        if (ObjectUtil.isEmpty(onlines)){
            log.info("在线用户数据不能为空");
            return false;
        }
        onlines.forEach(onlineUser -> {
            String redisK = RedisConstant.getRedisK(onlineUser.getRole());
            String cacheK = redisK+onlineUser.getUId();
            boolean exists = redisService.exists(cacheK);
            boolean isRemove = redisService.remove(cacheK);
            if (exists || !isRemove){
                removeById(onlineUser);
                UserWsModel userWsModel = new UserWsModel();
                userWsModel.setRole(onlineUser.getRole().getRole().toUpperCase());
                userWsModel.setId(String.valueOf(onlineUser.getUId()));
                wsService.reportWsAutoLoginOut(userWsModel);
            }
        });
        return true;
    }

    @Override
    public Boolean offline() {

        SysUser sysUser = SysUserContextHandler.getSysUser();
        redisService.remove(RedisConstant.getRedisK(sysUser.getRole())+sysUser.getUId());
        return this.lambdaUpdate().eq(Monitor::getUId , sysUser.getUId()).eq(Monitor::getRole , sysUser.getRole()).remove();
    }

    @Override
    public Boolean autoOffline() {

        Set<String> keys = redisService.keys(RedisConstant.USER_CACHE_PATTERN);
        if (keys.isEmpty()){
            return offline(this.list());
        }
        Set<Object> ids = keys.stream().map(key -> {
            int index = key.lastIndexOf(":");
            return  key.substring(index+1);
        }).collect(Collectors.toSet());

        List<Monitor> list = this.lambdaQuery().notIn(Monitor::getUId, ids).list();
        return ObjectUtil.isNotEmpty(list) || list.size() > 0 ? offline(list) : false;
    }


    public static String getRemoteAddr(ServletRequest request) {
        String addr = null;
        if (request instanceof HttpServletRequest) {
            HttpServletRequest hsr = (HttpServletRequest) request;
            for (String header : ADDR_HEADER) {
                if (StringUtils.isBlank(addr) || NUKNOWN.equalsIgnoreCase(addr)) {
                    addr = hsr.getHeader(header);
                } else {
                    break;
                }
            }
        }
        if (StringUtils.isBlank(addr) || NUKNOWN.equalsIgnoreCase(addr)) {
            addr = request.getRemoteAddr();
        } else {
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按','分割
            int i = addr.indexOf(",");
            if (i > 0) {
                addr = addr.substring(0, i);
            }
        }
        return addr;
    }
}
