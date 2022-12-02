package org.example.controller;

import org.example.model.Book;
import org.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(path="/book")
    public Book addBook (@RequestParam String name) {
        Book book = new Book(name);

        bookService.save(book);
        return book;
    }

    @GetMapping(path="/books")
    public Iterable<Book> getAllBooks() {
        return bookService.findAll();
    }
}
