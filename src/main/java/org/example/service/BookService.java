package org.example.service;

import org.example.model.Book;

import java.util.Optional;

public interface BookService {
    Book save(Book book);

    Iterable<Book> findAll();

    Optional<Book> findByName(String name);

    Optional<Book> findById(Integer id);

    void deleteAll();

    void deleteById(Integer id);
}
