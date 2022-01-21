package com.tams.base.minio;

import io.minio.MinioClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author swiChen
 * @date 2022/1/20
 **/

@Service
public class MinioService {

    @Resource
    private MinioClient minioClient;



}
