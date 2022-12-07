package org.example.service;

import org.example.model.Rental;
import org.example.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;

    @Autowired
    public RentalServiceImpl(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Override
    public Rental save(Rental rental) {
        return rentalRepository.save(rental);
    }

    @Override
    public Iterable<Rental> findAll() {
        return rentalRepository.findAll();
    }

    @Override
    public Optional<Rental> findById(Integer id) {
        return rentalRepository.findById(id);
    }

    @Override
    public void deleteAll() {
        rentalRepository.deleteAll();
    }

    @Override
    public void deleteById(Integer id) {
        rentalRepository.deleteById(id);
    }

}
