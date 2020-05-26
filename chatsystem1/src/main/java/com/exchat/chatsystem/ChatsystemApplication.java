package com.exchat.chatsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ChatsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatsystemApplication.class, args);
    }
    // 继承SpringBootServletInitializer 实现configure 方便打war 外部服务器部署。

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ChatsystemApplication.class);
    }

}

