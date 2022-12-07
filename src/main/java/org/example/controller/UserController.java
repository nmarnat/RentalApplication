package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/users")
    public Iterable<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @GetMapping("/users/rental")
    public Iterable<User> getUsersWithRental() {
        return userService.findAllWithRental();
    }

    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User newUser, @PathVariable Integer id) {
        return userService.findById(id)
                .map(user -> {
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    return userService.save(user);
                })
                .orElseGet(() -> userService.save(newUser));
    }

    @DeleteMapping("/users")
    void deleteAllUsers() { userService.deleteAll(); }
    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Integer id) {
        userService.deleteById(id);
    }

//    @GetMapping(path="/users/{name}")
//    public Optional<User> getUserByLastName(@PathVariable String name) {
//        return userService.findByLastName(name);
//    }
}