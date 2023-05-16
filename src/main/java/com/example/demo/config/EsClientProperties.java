package com.example.demo.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author hxf
 * @date 2023年05月05日 13:52
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "es.client")
public class EsClientProperties {

    private boolean enabled;

    private String host;

    private String port;

    private String username;

    private String password;

}