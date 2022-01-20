package com.minio.tams.autoconfigure;

import com.minio.tams.exception.MinioException;
import com.minio.tams.properties.MinioProperties;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author swiChen
 * @date 2022/1/20
 **/

@ConditionalOnClass(MinioClient.class)
@EnableConfigurationProperties(MinioProperties.class)
@Configuration
@Slf4j
public class MinioAutoConfigure {

    private MinioProperties properties;

    public MinioAutoConfigure(MinioProperties properties) {
        this.properties = properties;
    }

    @Bean
    public MinioClient minioClient(){

        MinioClient.Builder builder = MinioClient.builder();

        if (properties.getEndpoint() != null){
            builder.endpoint(properties.getEndpoint());
        }else if (properties.getHost() != null && properties.getPort() != null){
            String endpoint = "http://%s:%s";
            String newEndpoint = String.format(endpoint, properties.getHost(), properties.getPort());
            builder.endpoint(newEndpoint);
        }else {
            throw new MinioException("minio url not null");
        }

        if (properties.getAccessKey() != null && properties.getSecretKey() != null){
            log.info("minio build finished");
            return builder.credentials(properties.getAccessKey() , properties.getSecretKey()).build();
        }else {
            throw new MinioException("minio accessKey and secretKey not null");
        }

    }

}
