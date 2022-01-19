package com.tams.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tams.domain.Image;
import com.tams.mapper.ImageMapper;
import com.tams.service.ImageService;
import org.springframework.stereotype.Service;

/**
 * @author swiChen
 * @date 2022/1/19
 **/
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper , Image> implements ImageService {


    @Override
    public String getImagePath(Long iId) {

        if (ObjectUtil.isEmpty(iId)){
            return null;
        }
        Image image = super.getById(iId);
        if (ObjectUtil.isEmpty(image)){
            return null;
        }
        String realImagePath = image.getPath()+"/"+image.getName()+"."+image.getType();
        return realImagePath;
    }

}
