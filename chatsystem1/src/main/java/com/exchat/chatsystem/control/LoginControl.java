package com.exchat.chatsystem.control;

import com.exchat.chatsystem.entity.User;
import com.exchat.chatsystem.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class LoginControl {
    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session) {
        if (!StringUtils.isEmpty(username)) {
            session.setAttribute("loginUser", username);
            //为了防止表单重复提交，采用重定向的方式
            // return "success";
//             这个/表示当前目录
            map.put("ids", username);
            List list = this.userService.allUser();
            map.put("users",list);
            int  password2 = this.userService.getByName(username);
            System.out.println(password2+"lalal");
            if(Integer.parseInt(password)==password2){
                return "show";
            }else {
                map.put("msg", "登陆错误");
                return "login";
            }

//            if (password.equals(user.getPassword())) {
//                return "show";
//            } else {
//                map.put("msg", "登陆错误");
//                return "login";
//            }
            //return "redirect:/main";

        }
        map.put("msg", "登陆错误");
        return "login";
    }
}

