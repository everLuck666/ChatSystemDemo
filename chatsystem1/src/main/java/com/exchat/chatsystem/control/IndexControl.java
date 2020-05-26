package com.exchat.chatsystem.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexControl {
    @GetMapping("/logine")
    public String index(){
        return "show";
    }
}
