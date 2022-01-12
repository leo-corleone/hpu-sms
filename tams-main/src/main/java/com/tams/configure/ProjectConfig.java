package com.tams.configure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author swiChen
 * @date 2022/1/6
 **/

@Data
@Configuration
@ConfigurationProperties(prefix = "hpu-tams")
public class ProjectConfig {

    private String projectName;

    private String author;

    private String host;

    private String version;

}
