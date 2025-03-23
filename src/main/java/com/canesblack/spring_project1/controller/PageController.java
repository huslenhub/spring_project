package com.canesblack.spring_project1.controller;

import com.canesblack.spring_project1.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PageController {

    @Autowired
    private UserService userService;

    //페이지 이동 할때 get 으로 이동
    @GetMapping("/")
    public String Home() {
        return "index";
    }
    @GetMapping("/registerPage")
    public String registerPage(HttpServletRequest request, org.springframework.ui.Model model, org.springframework.security.web.csrf.CsrfToken csrfToken) {
        CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        model.addAttribute("_csrf", csrfToken);
        return "register/index";
    }
    @GetMapping("/loginPage")
    public String loginpage(HttpServletRequest request, org.springframework.ui.Model model, org.springframework.security.web.csrf.CsrfToken csrfToken) {
        CsrfToken token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        model.addAttribute("_csrf", csrfToken);
        return "login/index";
    }

    @GetMapping("/noticeAddPage")
    public String noticeAddPage(Model model, Authentication authentication) {
        String writer = userService.findWriter(authentication.getName());
        model.addAttribute("writer",writer);
        return "noticeAdd/index";
    }

//    @PostMapping
//    @PutMapping
//    @DeleteMapping
}
