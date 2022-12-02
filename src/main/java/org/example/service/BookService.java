package org.example.service;

import org.example.model.Book;

public interface BookService {
    Book save(Book book);

    Iterable<Book> findAll();
}
