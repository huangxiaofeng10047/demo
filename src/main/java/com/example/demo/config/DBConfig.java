package com.example.demo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author hxf
 * @date 2023年05月18日 10:51
 */
@Configuration
public class DBConfig {
    @Bean
    @Qualifier("dsOne1")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.one")
    public DataSource dsOne1() {
        return DataSourceBuilder.create().build();
    }
}
