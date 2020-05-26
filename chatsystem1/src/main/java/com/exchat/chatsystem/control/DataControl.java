package com.exchat.chatsystem.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static jdk.nashorn.internal.objects.NativeDebug.map;


@Controller
@RequestMapping
public class DataControl {
//    @Autowired
//    JdbcTemplate jdbcTemplate;

    @RequestMapping("/successde")
    public String returnString(Map map){
       //List list = jdbcTemplate.queryForList("select * from login");
      // map("tian", "tian");
        map.put("tian", Arrays.asList("tian"));
        //System.out.println(list.get(0));

        return "show";

    }

    @RequestMapping("/successd")
    public String success(Map<String,Object>map){
        map.put("h","<h1>你好</h1>");
        map.put("tian", Arrays.asList("tian"));
        return "show";
    }
}
