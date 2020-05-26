package com.exchat.chatsystem.server;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/iserver/{userId}")
@Component
public class WebSocketServer {
    private Session session;

    private String name;

    private static ConcurrentHashMap <String, WebSocketServer> map = new ConcurrentHashMap();
    private static List<Session> list = new ArrayList();

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "userId") String name) {
        this.session = session;
        System.out.println(session);
        this.name = name;
        map.put(name,this);
//        if(!list.contains(session)){
//
//            list.add(session);
//        }

        System.out.println(name+"进入");
        //groupSending(name+"登入");

//        try {
//            sendMessage("连接成功");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    @OnClose
    public void OnClose() {
        System.out.println("推出");
    }

    @OnMessage
    public void OnMessage(String message) {

        System.out.println("我是来自客户端"+message);
        if(message.contains("#")){
            try {
                String[] message2= message.split("#");
                System.out.println(message2[0]+message2[1]);
                sendMessageUser(message2[0],message2[1]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{//群发
               groupSending(message);

        }




    }
    public void groupSending(String message){
//        System.out.println("进入群发");
//        System.out.println("有几个数据"+list.size());
//         for(Session session:list){
//             System.out.println(message);
//             try {
//                 System.out.println("我是session"+session);
//                 session.getBasicRemote().sendText(message);
//             } catch (IOException e) {
//                 e.printStackTrace();
//             }
//         }
        for(String key:map.keySet()){
            try {
                map.get(key).sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

//    服务器推送
    public void sendMessage2(String message){

        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void sendAllClient(String message){


    }
    public void sendMessage(String message) throws IOException {
        if(this.session.isOpen()){
            System.out.println("连接是打开的");
            this.session.getBasicRemote().sendText(message);
        }

    }
//    给指定的用户推送消息

    public void sendMessageUser(String name,String message) throws IOException {
        if(this.session.isOpen()){

            map.get(name).sendMessage(message);
        }

    }
}
