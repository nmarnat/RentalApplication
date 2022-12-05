package org.example.controller;

import org.example.model.Rental;
import org.example.service.BookService;
import org.example.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class RentalController {

    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/rentals")
    public Rental addRental(@RequestBody Rental rental) {
        return rentalService.save(rental);
    }

    @GetMapping("/rentals")
    public Iterable<Rental> getAllRentals() {
        return rentalService.findAll();
    }

    @GetMapping("/rentals/{id}")
    public Optional<Rental> getRentalById(@PathVariable Integer id) {
        return rentalService.findById(id);
    }

    @PutMapping("/rentals/{id}")
    public Rental updateRental(@RequestBody Rental newRental, @PathVariable Integer id) {
        return rentalService.findById(id)
                .map(rental -> {
                    rental.setUserName(newRental.getUserName());
                    rental.setStartDate(newRental.getStartDate());
                    rental.setEndDate(newRental.getEndDate());
                    rental.setBooks(newRental.getBooks());
                    return rentalService.save(rental);
                })
                .orElseGet(() -> rentalService.save(newRental));
    }

    @DeleteMapping("/rentals/{id}")
    void deleteRental(@PathVariable Integer id) {
        rentalService.deleteById(id);
    }
}
