package org.example.service;

import org.example.exception.InvalidRentalException;
import org.example.model.Rental;

import java.util.Optional;

public interface RentalService {
    Rental save(Rental rental) throws InvalidRentalException;

    Rental saveOrUpdate(Rental rental);

    Iterable<Rental> findAll();

    Optional<Rental> findById(Integer id);

    void deleteAll();

    void deleteById(Integer id);

}
