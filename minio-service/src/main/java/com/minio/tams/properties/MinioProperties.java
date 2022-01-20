package com.minio.tams.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author swiChen
 * @date 2022/1/20
 **/

@ConfigurationProperties(prefix = MinioProperties.MINIO_SERVICE)
@Data
public class MinioProperties {

    public final static String MINIO_SERVICE = "spring.minio";

    private String host;

    private String port;

    private String endpoint;

    private String accessKey;

    private String secretKey;

}
