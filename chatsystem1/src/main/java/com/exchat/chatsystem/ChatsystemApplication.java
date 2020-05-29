package com.exchat.chatsystem;

import com.exchat.chatsystem.server.WebSocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import javax.xml.ws.Service;

@SpringBootApplication
public class ChatsystemApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext configurableApplicationContext =  SpringApplication.run(ChatsystemApplication.class, args);
        WebSocketServer.setApplicationContext(configurableApplicationContext);
    }
    // 继承SpringBootServletInitializer 实现configure 方便打war 外部服务器部署。

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ChatsystemApplication.class);
    }


}

