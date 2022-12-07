package org.example.service;

import org.example.model.Rental;

import java.util.Optional;
import java.util.stream.DoubleStream;

public interface RentalService {
    Rental save(Rental rental);

    Iterable<Rental> findAll();

    Optional<Rental> findById(Integer id);

    void deleteAll();

    void deleteById(Integer id);


}
