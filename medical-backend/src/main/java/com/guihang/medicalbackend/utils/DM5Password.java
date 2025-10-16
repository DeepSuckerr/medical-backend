package com.guihang.medicalbackend.utils;


import org.springframework.util.DigestUtils;

// 密码加密的工具类
public class DM5Password {

    // 封装一个密码加密的方法
    public static String md5PassWord(String password, String salt) {
        String md5Pwd = password + salt;
        md5Pwd = DigestUtils.md5DigestAsHex(md5Pwd.getBytes());
//        System.out.println(md5Pwd);
        return md5Pwd;
    }
}
