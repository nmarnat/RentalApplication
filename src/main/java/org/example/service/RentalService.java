package org.example.service;

import org.example.model.Rental;

public interface RentalService {
    Rental save(Rental rental);

    Iterable<Rental> findAll();
}
