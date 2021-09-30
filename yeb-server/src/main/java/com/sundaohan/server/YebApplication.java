package com.sundaohan.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Auther sundaohan
 * @Package com.sundaohan.server
 * @Title YebApplication
 * @Description TODO
 * @Date 2021/7/21 下午11:19
 */
@SpringBootApplication
@MapperScan("com.sundaohan.server.mapper")
@EnableScheduling
public class YebApplication {

    public static void main(String[] args) {
        SpringApplication.run(YebApplication.class, args);
    }
}
