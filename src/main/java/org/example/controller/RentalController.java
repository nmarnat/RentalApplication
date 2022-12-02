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
    public Rental addRental (@RequestParam User user, @RequestParam Date startDate, @RequestParam Date endDate,
                         @RequestParam List<Book> books) {
        Rental rental = new Rental(user, startDate, endDate, books);

        rentalService.save(rental);
        return rental;
    }

    @GetMapping(path="/rentals")
    public Iterable<Rental> getAllRentals() {
        return rentalService.findAll();
    }
}
