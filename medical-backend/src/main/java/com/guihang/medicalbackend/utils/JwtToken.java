package com.guihang.medicalbackend.utils;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JwtToken {
    static String siganture = "hello";

    // 生成令牌
    public static String createToken(Long id, String username) {
        // 创建JWT对象
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                // 设置header
                .setHeaderParam("HS256", "HS256")
                //设置数据或者参数 payload
                .claim("id", id)
                .claim("username", username)
                // 可以设置主题
                // 设置有效时长
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 100))
                .setId(UUID.randomUUID().toString())
                // 设置signature 签名
                .signWith(SignatureAlgorithm.HS256, siganture)
                .compact();
        return jwtToken;

    }

    // 校验token 是否有效
    public static boolean checkToken(String token) {

        if (token == null) {
            return false;
        }
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey("hello").parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            return false;
        }
        return true;
    }

    // 根据token 获取 id  username
    public static Map<String, Object> getIdAndNameFromToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey("hello").parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        Map<String, Object> map = new HashMap<>();
        map.put("id", claims.get("id"));
        map.put("username", claims.get("username"));
        return map;
    }

}
