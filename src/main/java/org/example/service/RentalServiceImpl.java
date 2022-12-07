package org.example.service;

import org.example.exception.InvalidRentalException;
import org.example.model.Rental;
import org.example.model.User;
import org.example.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;

    private final UserService userService;

    @Autowired
    public RentalServiceImpl(RentalRepository rentalRepository, UserService userService) {
        this.rentalRepository = rentalRepository;
        this.userService = userService;
    }

    @Override
    public Rental save(Rental rental) throws InvalidRentalException {
        Optional<User> user = userService.findById(rental.getUser().getId());
        if (user.isPresent() && user.get().getRental() != null) {
            throw new InvalidRentalException("User already has a rental");
        }
        else {
            return rentalRepository.save(rental);
        }
    }

    @Override
    public Rental saveOrUpdate(Rental rental) {
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
