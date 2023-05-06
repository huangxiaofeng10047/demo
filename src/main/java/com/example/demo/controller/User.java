package com.example.demo.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author hxf
 * @date 2023年05月06日 9:43
 */
@Builder
@Setter
@Getter
public class User {
    String userName;
    String userPwd;
    Date createTime;
}
