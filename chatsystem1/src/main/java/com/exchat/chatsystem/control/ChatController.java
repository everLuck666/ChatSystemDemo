package com.exchat.chatsystem.control;

import com.exchat.chatsystem.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping
public class ChatController {
    @Autowired
    UserService userService;
    @GetMapping("/chat")
    public String chat(@RequestParam("id1") String id1,
                        @RequestParam("id2") String id2,
                        Map<String, Object> map){
        map.put("id1",id1);
        map.put("id2",id2);
        List list  = userService.aloneMessage(id1,id2);

        List listData = new ArrayList();
        for(int i = 0;i < list.size();i++){
            try{
                Map map1 = (Map)list.get(i);
                String username = map1.get("name").toString();
                String message = map1.get("message").toString();
                listData.add(username+":"+message);
            }catch (Exception e){
                System.out.println("出现强制异常");
            }


        }
        map.put("list",listData);

        return "onechat.html";
    }


}
