package com.example.chatserver.controller;

import com.example.chatserver.model.user.User;
import com.example.chatserver.model.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserDao userDao;

    @GetMapping("/user/get-all")
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @PostMapping("/user/save")
    public User save(@RequestBody User user) {
        return userDao.save(user);
    }
}
