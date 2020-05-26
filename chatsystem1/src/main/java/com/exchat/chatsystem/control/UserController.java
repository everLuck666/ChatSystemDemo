package com.exchat.chatsystem.control;

import com.exchat.chatsystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.exchat.chatsystem.server.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/save")
    @ResponseBody
    public User save(@RequestParam("username")String username,
                     @RequestParam("password")String password) {
        User user = new User();
        user.setUsername(username );
        user.setPassword(password);

        int result = this.userService.insert(user);
        System.out.println(result);
        return user;
    }


}
