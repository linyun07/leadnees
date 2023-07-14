package com.linyun.user;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author linyun
 * @since 2023/7/13 9:46
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.linyun.user.mapper")
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
        log.error("项目启动成功");
    }
}
