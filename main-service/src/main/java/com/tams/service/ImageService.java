package com.tams.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tams.domain.Image;

/**
 * @author swiChen
 * @date 2022/1/19
 **/
public interface ImageService extends IService<Image> {

    String getImagePath(Long iId);

}
