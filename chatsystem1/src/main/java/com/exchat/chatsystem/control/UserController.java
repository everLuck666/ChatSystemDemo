package com.exchat.chatsystem.control;

import com.exchat.chatsystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.exchat.chatsystem.server.UserService;

import java.util.Map;
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user/save")
    public String save(@RequestParam("username")String username,
                     @RequestParam("password")String password,
                       Map<String, Object> map2){
        User user = new User();
        user.setUsername(username );
        user.setPassword(password);
       if(!userService.searchUser(username).equals("用户存在")){
           int result = this.userService.insert(user);
           map2.put("msg","注册成功");
           return "login";
       }else{
           map2.put("msg","用户已经存在");
           System.out.println("注册的用户存在");
           return "login";
       }

    }


}
