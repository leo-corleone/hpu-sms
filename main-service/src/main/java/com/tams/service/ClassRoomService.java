package com.tams.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tams.domain.ClassRoom;
import com.tams.dto.PageParam;
import com.tams.dto.PageResult;

/**
 * @author swiChen
 * @date 2022/1/12
 **/
public interface ClassRoomService extends IService<ClassRoom>{

    PageResult getList(PageParam pageParam);

    boolean updateClassRoom(ClassRoom classRoom);


    boolean addClassRoom(ClassRoom classRoom);


}
