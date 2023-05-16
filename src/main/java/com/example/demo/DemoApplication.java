package com.example.demo;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.example.demo.dao")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DemoApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
    }
    @GetMapping("/test")
    public Object test(){
        return "hello world";
    }
}
