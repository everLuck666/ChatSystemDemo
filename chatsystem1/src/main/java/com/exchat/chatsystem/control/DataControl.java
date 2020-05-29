package com.exchat.chatsystem.control;

import com.exchat.chatsystem.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static jdk.nashorn.internal.objects.NativeDebug.map;


@Controller
public class DataControl {
//    @Autowired
//    JdbcTemplate jdbcTemplate;
@Autowired
private UserService userService;
    @RequestMapping("/chatSuccess")
    public String returnString(@RequestParam("username") String username,
            Map map){
       //List list = jdbcTemplate.queryForList("select * from login");
      // map("tian", "tian");
        List list = this.userService.allUser();
        map.put("users",list);
        map.put("ids", username);
        List listData = this.userService.readAllPeopleChat();

        map.put("list",listData);
        //System.out.println(list.get(0));

        return "chatshow";

    }

    @RequestMapping("/successd")
    public String success(Map<String,Object>map){
        map.put("h","<h1>你好</h1>");
        map.put("tian", Arrays.asList("tian"));
        return "show";
    }
}
