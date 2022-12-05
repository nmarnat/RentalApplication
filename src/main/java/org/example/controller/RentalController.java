package org.example.controller;

import org.example.model.Book;
import org.example.model.Rental;
import org.example.service.BookService;
import org.example.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RentalController {

    private final RentalService rentalService;

    private BookService bookService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping(path = "/rental")
    public Rental addRental(@RequestParam String userName, @RequestParam Integer startDate, @RequestParam Integer endDate,
                            @RequestParam List<String> books) {

        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
        Date startdate = new Date();
        Date enddate = new Date();
        try {
            startdate = originalFormat.parse(startDate.toString());
            enddate = originalFormat.parse(endDate.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // TODO corriger code pour récupérer livre s'il existe
//        List<Book> listBooks = books.stream().map(Book::new).collect(Collectors.toList());
        List<Book> listBooks = books.stream().filter(b -> bookService.findByName(b).isPresent())
                .map(b -> bookService.findByName(b).get()).collect(Collectors.toList());
        Rental rental = new Rental(userName, startdate, enddate, listBooks);

        rentalService.save(rental);
        return rental;
    }

    @GetMapping(path = "/rentals")
    public Iterable<Rental> getAllRentals() {
        return rentalService.findAll();
    }
}
