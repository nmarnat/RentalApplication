package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(path="/user")
    public User addUser (@RequestParam String firstName, @RequestParam String lastName) {
        User user = new User();
//        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        userService.save(user);
        return user;
    }

    @GetMapping(path="/users")
    public Iterable<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping(path="/user")
    public Optional<User> getUserById(@RequestParam Integer id) {
        return userService.findById(id);
    }
}