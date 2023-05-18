package com.example.demo;

import com.alibaba.fastjson2.JSON;
import com.example.demo.dao.jpa.UserJpaRepository;
import com.example.demo.entity.User;
import jakarta.annotation.Resource;
import org.elasticsearch.common.geo.GeoPoint;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private UserJpaRepository jpaRepository;

    @Test
    void contextLoads() {
      User user= User.builder()
              .username("test")
                .age(18)
                .province("上海")
                .city("上海")
                .district("浦东新区")
                .address("上海市浦东新区花园石桥路28弄1-8号-汤臣一品")
              .about("槟榔妹真好玩啊")
              .address(JSON.toJSONString(new GeoPoint(31.238794, 121.508506)))
              .build();
      jpaRepository.save(user);
    }

}
