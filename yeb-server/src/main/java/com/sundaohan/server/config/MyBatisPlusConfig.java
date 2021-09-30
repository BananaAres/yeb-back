package com.sundaohan.server.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther sundaohan
 * @Package com.sundaohan.server.config
 * @Title MyBatisPlusConfig
 * @Description Mybatis分页配置
 * @Date 2021/8/2 下午5:07
 */
@Configuration
public class MyBatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
