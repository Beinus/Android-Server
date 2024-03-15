package com.example.chatserver.model.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDao {
    @Autowired
    private UserRepository repository;

    public User save(User user) {
        return repository.save(user);
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
