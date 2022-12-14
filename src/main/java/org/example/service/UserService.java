package org.example.service;

import org.example.model.User;

import java.util.Optional;

public interface UserService {
    User save(User user);

    Iterable<User> findAll();

    Optional<User> findById(Integer id);

    Optional<User> findByLastName(String lastName);

    void deleteAll();

    void deleteById(Integer id);

    Iterable<User> findAllWithRental();
}
