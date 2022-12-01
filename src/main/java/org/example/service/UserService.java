package org.example.service;

import org.example.model.User;

import java.util.Optional;

public interface UserService {
    void save(User user);

    Iterable<User> findAll();

    Optional<User> findById(Integer id);
}
