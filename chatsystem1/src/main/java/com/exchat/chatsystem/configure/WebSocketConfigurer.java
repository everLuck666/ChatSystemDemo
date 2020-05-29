package com.exchat.chatsystem.configure;

import com.exchat.chatsystem.server.MyHandler;
import com.exchat.chatsystem.server.UserService;
import com.exchat.chatsystem.server.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration

public class WebSocketConfigurer  {
   @Bean
    public ServerEndpointExporter serverEndpointExporter(){
       return new ServerEndpointExporter();
   }

@Autowired
    public void setUserService(UserService userService){
    //WebSocketServer.userService = userService;


}

}
