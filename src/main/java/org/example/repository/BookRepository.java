package org.example.repository;

import org.example.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Integer> {
    Optional<Book> findByName(String name);

    Iterable<Book> findByRentalIsNotNull();
}
