package com.canesblack.spring_project1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class PageController {
    //페이지 이동 할때 get 으로 이동
    @GetMapping("/")
    public String returnHome() {
        return "index";
    }
//    @PostMapping
//    @PutMapping
//    @DeleteMapping
}
