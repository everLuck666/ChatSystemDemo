package com.exchat.chatsystem.control;

import com.exchat.chatsystem.entity.User;
import com.exchat.chatsystem.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/online")
public class OnlineController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "getList", method = RequestMethod.POST)
    public List getOnline(String user, Map map) {
        List list = this.userService.allUser();
        List onlineUsers = userService.readOnline();
        List users = new ArrayList();

        for (int i = 0; i < list.size(); i++){
            String userName = list.get(i).toString();
            User usr  = new User();
            usr.setUsername(userName);
            if (onlineUsers.indexOf(userName) == -1) {
                usr.setState("离线");
            }else{
                usr.setState("在线");
            }
            users.add(usr);

        }
        map.put("users",users);

        return users;




    }

}
