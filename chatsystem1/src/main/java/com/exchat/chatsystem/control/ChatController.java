package com.exchat.chatsystem.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping
public class ChatController {
    @GetMapping("/chat")
    public String chat(@RequestParam("id1") String id1,
                        @RequestParam("id2") String id2,
                        Map<String, Object> map){
        map.put("id1",id1);
        map.put("id2",id2);
        return "chat.html";
    }


}
