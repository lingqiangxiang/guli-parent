package com.lqx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: Bruce
 * @description: SpringBoot程序入口
 * @date: Created in 2020-04-23 16:02
 * @modified by:
 */
@SpringBootApplication
//common中的添加修改时间属性注册到mybatis-plus中
@ComponentScan(basePackages={"com.lqx","com.lqx.common","com.lqx.edu.mapper"})
public class MicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroServiceApplication.class, args);
    }

}
