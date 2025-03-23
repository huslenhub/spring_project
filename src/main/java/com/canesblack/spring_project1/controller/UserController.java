package com.canesblack.spring_project1.controller;

import com.canesblack.spring_project1.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;

import com.canesblack.spring_project1.entity.User;
import com.canesblack.spring_project1.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
//이게 더 보안을 생각 한것 위에것보다 내용은 내용은 같다
//    public UserController(UserService userService) {
//        this.userService=userService;
//    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        String userPassword = user.getPassword();
        user.setRole(Role.MEMBER);
        String passwordEncoded = passwordEncoder.encode(userPassword);
        user.setPassword(passwordEncoded);
        userService.insertUser(user);
        return "redirect:/loginPage";
    }

}
