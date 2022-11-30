package org.example.controller;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/user")
    public User addNewUser (@RequestParam Integer id, @RequestParam String firstName, @RequestParam String lastName) {
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        userRepository.save(user);
        return user;
    }

    @GetMapping(path="/users")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}