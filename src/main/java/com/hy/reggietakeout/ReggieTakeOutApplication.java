package com.hy.reggietakeout;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@Slf4j
@MapperScan("com.hy.reggietakeout.mapper")
@SpringBootApplication
@ServletComponentScan
public class ReggieTakeOutApplication {

    public static void main(String[] args) {

        SpringApplication.run(ReggieTakeOutApplication.class, args);
        log.info("项目启动成功...");
    }

}
