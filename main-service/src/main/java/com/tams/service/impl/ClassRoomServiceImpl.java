package com.tams.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tams.domain.ClassRoom;
import com.tams.dto.PageParam;
import com.tams.dto.PageResult;
import com.tams.enums.ResponseCode;
import com.tams.exception.base.BusinessException;
import com.tams.mapper.ClassRoomMapper;
import com.tams.service.ClassRoomService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author swiChen
 * @date 2022/1/12
 **/

@Service
public class ClassRoomServiceImpl
                   extends ServiceImpl<ClassRoomMapper , ClassRoom>
                   implements ClassRoomService {

    @Override
    public PageResult getList(PageParam pageParam) {

        if (ObjectUtil.isEmpty(pageParam.getPageSize()) || ObjectUtil.isEmpty(pageParam.getPage())){
            pageParam.setPage(1);
            pageParam.setPageSize(10000);
        }

        Page<Object> objects = PageHelper.startPage(pageParam.getPage(), pageParam.getPageSize());
        List<ClassRoom> list =  this.lambdaQuery().like(ObjectUtil.isNotEmpty(pageParam.getKeyword()),ClassRoom::getName , pageParam.getKeyword()).list();
        long total = objects.getTotal();
        PageResult pageResult = new PageResult();
        pageResult.setItems(list);
        pageResult.setTotal(total);
        return pageResult;

    }

    @Override
    public boolean updateClassRoom(ClassRoom classRoom) {
        if (ObjectUtil.isEmpty(classRoom)){
            throw new BusinessException("教室信息不能为空", ResponseCode.NoContent.getCode());
        }
        if (checkDuplicateRoom(classRoom)){
            throw new BusinessException(classRoom.getName() + "已存在", 333);
        }
        return this.updateById(classRoom);
    }

    @Override
    public boolean addClassRoom(ClassRoom classRoom) {

        if (ObjectUtil.isEmpty(classRoom)){
            throw new BusinessException("教室信息不能为空", ResponseCode.NoContent.getCode());
        }
        if (ObjectUtil.isEmpty(classRoom.getType())){
            throw new BusinessException("教室类型不能为空");
        }
        if (checkDuplicateRoom(classRoom)){
            throw new BusinessException(classRoom.getName() + "已存在", 333);
        }
        return save(classRoom);
    }

    private boolean checkDuplicateRoom(ClassRoom classRoom){

       return this.lambdaQuery().ne(ObjectUtil.isNotEmpty(classRoom.getRoomId()) ,ClassRoom::getRoomId , classRoom.getRoomId()).eq(ClassRoom::getName , classRoom.getName()).count() > 0;
    }
}
