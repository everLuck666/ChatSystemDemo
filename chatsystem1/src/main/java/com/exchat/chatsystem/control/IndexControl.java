package com.exchat.chatsystem.control;

import com.exchat.chatsystem.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
public class IndexControl {
    @Autowired
    UserService userService;

    @GetMapping("/logine")
    public String index(){
        return "show";
    }
    @GetMapping("/corporation")
    public String st(){
        return "league";
    }
    @GetMapping("/chatShow")
    public String chatShow(){
        return "chatshow";
    }
    @RequestMapping("/peoples")
    public String returnChatShow(Map map,
                                 @RequestParam("user") String user){
        List listData = userService.readAllPeopleChat();
        map.put("ids", user);

        map.put("list",listData);
        List list = this.userService.allUser();
        map.put("users",list);

        return "chatshow";
    }
    @RequestMapping("/alone")
    public String test(){
        return "alone";
    }
    @RequestMapping("/register")
    public String register(){
        return "register";
    }
    @RequestMapping("/allchat")
    public String allChat(){
        return "allchat";
    }
}
