package com.exchat.chatsystem.server;

import com.exchat.chatsystem.dao.UserDao;
import com.exchat.chatsystem.dao.UserDaoImpl;
import com.exchat.chatsystem.entity.AloneMessage;
import com.exchat.chatsystem.utils.SpringUtil;
import com.google.inject.Inject;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
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

    //此处是解决无法注入的关键
    private static ApplicationContext applicationContext;
    //你要注入的service或者dao
   UserService userService;
    public static void setApplicationContext(ApplicationContext applicationContext) {
        WebSocketServer.applicationContext = applicationContext;
    }


    private Session session;





    ;

    private String name;

    private static ConcurrentHashMap <String, WebSocketServer> map = new ConcurrentHashMap();
    private static ConcurrentHashMap  aloneMap = new ConcurrentHashMap();
    private static List aloneList = new ArrayList();
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
        if(message.contains("我在多人聊天")){
            String[] messages = message.split("我在多人聊天");
            if(aloneList.indexOf(messages[0]) != -1){
                aloneList.remove(messages[0]);
            }


        }
        if(message.contains("&")){
            String[] message2= message.split("&");

            //message2[0]是要发送给的用户，message[1]是要发送的信息。
            if(-1==aloneList.indexOf(message2[1])){
                aloneList.add(message2[1]);
                System.out.println("++++++++++"+message2[1]+"进入单人聊天");
            }

        }

        if(message.contains("#")){//单人页面发来的信息

            try {
                String[] message2= message.split("#");

                //message2[0]是要发送给的用户，message[1]是要发送的信息。
                String[] userName =  message2[1].split(":");//记录在单人聊天的人，不让多人防止多人聊天的人单发送
                if(-1==aloneList.indexOf(userName[0])){
                    aloneList.add(userName[0]);
                    System.out.println("这个用户目前在单独聊天"+userName[0]);
                }

                System.out.println(message2[0]+message2[1]);

                        if(-1==aloneList.indexOf(message2[0])){//目前在单独聊天页里面没有这个用户，那么这个用户在广播区2、还有种可能性是没有登陆
                            sendMessageUser(message2[0],message2[1]+"---来自单独聊天");
                            System.out.println("++++++++发送了单人信息");
                        }else{
                            sendMessageUser(message2[0],message2[1]);
                            System.out.println("-----------------发送了单人信息");
                        }

                userService = applicationContext.getBean(UserServiceImpl.class);
                AloneMessage aloneMessage = new AloneMessage();
                if(message2[1].contains(":")){
                    String[] user = message2[1].split(":");
                    if(user.length==2){
                        aloneMessage.setName(user[0]);
                        aloneMessage.setMessage(user[1]);
                        aloneMessage.setName2(message2[0]);
                    }

                    //userService.insertAloneMessage(aloneMessage);//把单对单的数据存到数据库
                }







            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{//群发
            if(!message.equals("我还活着")&&!message.contains("&")&&!message.contains("我在多人聊天")){
                if(message.contains(":")){
                    String message1 = message;

                    String[] userName =  message.split(":");
                    if(userName.length==2){
                        System.out.println("++++"+message1);
                        userService = applicationContext.getBean(UserServiceImpl.class);
                        userService.insertPeopleChat(message1);//防止发送空数据
                    }
                    if(aloneList.indexOf(userName[0]) != -1){
                        aloneList.remove(userName[0]);
                    }
                }
                groupSending(message);
                userService = applicationContext.getBean(UserServiceImpl.class);
                System.out.println("群发的message"+message);

            }




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
            System.out.println("我是目前的用户"+key);
            try {
                if(-1==aloneList.indexOf(key)){
                    System.out.println("这个用户没在单独聊天"+key);
                    map.get(key).sendMessage(message);
                }

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
            if(map.get(name) != null){
                map.get(name).sendMessage(message);
                }


            AloneMessage aloneMessage = new AloneMessage();
            String[] user = message.split(":");
            //在给单人发消息的时候，由于用户没有登陆，所以这个时候的消息，带个---来自单人登陆
            String[] message3 = user[1].split("---");
            if(user.length==2) {
                aloneMessage.setName2(name);
                aloneMessage.setMessage(message3[0]);
                aloneMessage.setName(user[0]);
                userService = applicationContext.getBean(UserServiceImpl.class);
                userService.insertAloneMessage(aloneMessage);//把数据存入到数据库
            }

        }

    }
}
