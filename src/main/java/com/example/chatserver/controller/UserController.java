package com.example.chatserver.controller;

import com.example.chatserver.model.user.User;
import com.example.chatserver.model.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    @MessageMapping("/user.addUser")
    @SendTo("/user/topic")
    public User addUser(
            @Payload User user
    ) {
        service.save(user);
        return user;    // return the user info to inform that the user is connected through @SendTo.
    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/topic")
    public User disconnect(
            @Payload User user
    ) {
        service.disconnect(user);
        return user;    // return the user info to inform that the user is disconnected through @SendTo.
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findConnectedUsers() {
        return ResponseEntity.ok(service.findConnectedUsers());
    }


    // Previous
    @GetMapping("/user/get-all")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping("/user/save")
    public User save(@RequestBody User user) {
        return service.save(user);
    }
}
