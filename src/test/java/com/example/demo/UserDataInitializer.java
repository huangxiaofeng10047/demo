package com.example.demo;

import com.example.demo.entity.GeoPointReader;
import com.example.demo.entity.User;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import com.alibaba.fastjson2.JSON;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author hxf
 * @date 2023年05月16日 14:40
 */
public class UserDataInitializer {

    public static List<User> loadUserData() {
        byte[] bytes = new byte[0];
        try {
            bytes = Objects.requireNonNull(UserDataInitializer.class.getResourceAsStream("/hero.json")).readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JSON.register(GeoPoint.class, GeoPointReader.INSTANCE);
        return JSON.parseArray(new String(bytes), User.class);
    }
}
