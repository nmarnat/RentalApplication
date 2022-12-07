package org.example.controller;

import org.example.model.Book;
import org.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @GetMapping("/books")
    public Iterable<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/books/rental")
    public Iterable<Book> getBooksWithRental() {
        return bookService.findAllWithRental();
    }

    @GetMapping(path = "/books/{id}")
    public Optional<Book> getBookById(@PathVariable Integer id) {
        return bookService.findById(id);
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@RequestBody Book newBook, @PathVariable Integer id) {
        return bookService.findById(id)
                .map(book -> {
                    book.setName(newBook.getName());
                    return bookService.save(book);
                })
                .orElseGet(() -> bookService.save(newBook));
    }

    @DeleteMapping("/books")
    void deleteAllBooks() { bookService.deleteAll(); }

    @DeleteMapping("/books/{id}")
    void deleteBook(@PathVariable Integer id) {
        bookService.deleteById(id);
    }
//    @GetMapping(path= "/books/{name}")
//    public Optional<Book> getBookByName(@PathVariable String name) {
//        return bookService.findByName(name);
//    }
}
