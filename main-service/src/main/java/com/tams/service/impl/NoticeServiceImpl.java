package com.tams.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tams.domain.Notice;
import com.tams.exception.base.BusinessException;
import com.tams.mapper.NoticeMapper;
import com.tams.model.SysUser;
import com.tams.service.NoticeService;
import com.tams.util.SysUserContextHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author swichen
 * @data 2021-6-22
 */

@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper , Notice>
		               implements NoticeService {

	@Override
	public boolean addNotice(Notice notice) {

		if (ObjectUtil.isEmpty(notice.getTitle())){
			throw new BusinessException("标题不能为空", 333);
		}
		if (StringUtils.isEmpty(notice.getContent())){
			throw new BusinessException("内容不能为空", 333);
		}
		SysUser sysUser = SysUserContextHandler.getSysUser();
        notice.setAuthor(sysUser.getName());
		return save(notice);
	}

	@Override
	public List<Notice> getNotice(String type) {

		if (StringUtils.isEmpty(type)){
			throw new BusinessException("查询类型不能为空");
		}
		return lambdaQuery().eq(Notice::getType, type).list();
	}

	@Override
	public boolean switchStatus(Long nId) {

		Notice notice = getById(nId);
		if (notice.getStatus().equals("NO")){
			notice.setStatus("OFF");
		}else{
			notice.setStatus("NO");
		}
		return updateById(notice);
	}

	@Override
	public boolean deleteNotice(Long nId) {
		return removeById(nId);
	}
}
