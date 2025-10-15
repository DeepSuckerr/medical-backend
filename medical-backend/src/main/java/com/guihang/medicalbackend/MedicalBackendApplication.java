package com.guihang.medicalbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 主启动类启动的时候，扫描指定包下的所有接口 那么包下的所有接口就会被Spring容器进行管理（生成接口的代理实现类）
@MapperScan("com.guihang.medicalbackend.mapper")
public class MedicalBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicalBackendApplication.class, args);
    }

}
