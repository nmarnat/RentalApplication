package org.example.controller;

import org.example.exception.InvalidRentalException;
import org.example.model.Rental;
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
        try {
            return rentalService.save(rental);
        } catch (InvalidRentalException e) {
            e.printStackTrace();
        }
        return rental;
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
                    rental.setUser(newRental.getUser());
                    rental.setStartDate(newRental.getStartDate());
                    rental.setEndDate(newRental.getEndDate());
                    rental.setBooks(newRental.getBooks());
                    return rentalService.saveOrUpdate(rental);
                })
                .orElseGet(() -> rentalService.saveOrUpdate(newRental));
    }

    @DeleteMapping("/rentals")
    void deleteAllRentals() { rentalService.deleteAll(); }

    @DeleteMapping("/rentals/{id}")
    void deleteRental(@PathVariable Integer id) {
        rentalService.deleteById(id);
    }
}
