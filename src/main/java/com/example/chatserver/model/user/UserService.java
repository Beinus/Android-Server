package com.example.chatserver.model.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User save(User user) {
        user.setStatus(Status.ONLINE);
        return repository.save(user);
    }

    public void disconnect(User user) {
        var storedUser = repository.findById(user.getUserID())
                .orElse(null);
        if (storedUser != null) {
           storedUser.setStatus(Status.OFFLINE);
           repository.save(storedUser);
        }
    }

    public List<User> findConnectedUsers() {
        return repository.findAllByStatus(Status.ONLINE);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(users::add);
        return users;
    }
    // Or take a look at Guava dependency for making this function
    public void delete(User user) {
        repository.delete(user);
    }
}
