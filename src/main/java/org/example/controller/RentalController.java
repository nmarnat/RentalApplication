package org.example.controller;

import org.example.model.Book;
import org.example.model.Rental;
import org.example.model.User;
import org.example.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class RentalController {

    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping(path="/rental")
    public Rental addRental (@RequestParam String userName, @RequestParam Integer startDate, @RequestParam Integer endDate,
                         @RequestParam List<String> books) throws ParseException {

        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyyMMdd");
        Date startdate = originalFormat.parse(startDate.toString());
        Date enddate = originalFormat.parse(endDate.toString());

        // TODO corriger ce code pour récupérer livre s'il existe
        Book b1 = new Book(books.get(0));
        List<Book> listBooks = List.of(b1);
        Rental rental = new Rental(userName, startdate, enddate, listBooks);

        rentalService.save(rental);
        return rental;
    }

    @GetMapping(path="/rentals")
    public Iterable<Rental> getAllRentals() {
        return rentalService.findAll();
    }
}
