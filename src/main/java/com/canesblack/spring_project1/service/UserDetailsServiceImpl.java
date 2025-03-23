package com.canesblack.spring_project1.service;

import com.canesblack.spring_project1.entity.CustomUser;
import com.canesblack.spring_project1.entity.User;
import com.canesblack.spring_project1.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + " username not found");
        }
        //db에 username이 존재 할시

        return new CustomUser(user);


    }

}
