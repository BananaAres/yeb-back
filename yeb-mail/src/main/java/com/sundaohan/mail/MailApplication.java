package com.sundaohan.mail;

import com.sundaohan.server.pojo.MailConstants;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;


/**
 * @Auther sundaohan
 * @Package com.sundaohan.mail
 * @Title YebApplication
 * @Description TODO
 * @Date 2021/8/3 下午3:32
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MailApplication {
    public static void main(String[] args){
        SpringApplication.run(MailApplication.class, args);
    }


    @Bean
    public Queue queue(){
        return new Queue(MailConstants.MAIL_QUEUE_NAME);
    }
}
