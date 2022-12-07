package org.example.service;

import org.example.exception.InvalidRentalException;
import org.example.model.Book;
import org.example.model.Rental;
import org.example.model.User;
import org.example.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;

    private final UserService userService;
    private final BookService bookService;

    @Autowired
    public RentalServiceImpl(RentalRepository rentalRepository, UserService userService, BookService bookService) {
        this.rentalRepository = rentalRepository;
        this.userService = userService;
        this.bookService = bookService;
    }

    @Override
    public Rental save(Rental rental) throws InvalidRentalException {
        validate(rental);
        return rentalRepository.save(rental);
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

    public void validate(Rental rental) throws InvalidRentalException {
        Optional<User> user = userService.findById(rental.getUser().getId());
        if (user.isEmpty()) {
            throw new InvalidRentalException("User does not exist");
        }
        if (user.get().getRental() != null) {
            throw new InvalidRentalException("User already has a rental");
        }

        List<Book> books = rental.getBooks();
        for (Book b : books) {
            Optional<Book> book = bookService.findById(b.getId());
            if (book.isEmpty()) {
                throw new InvalidRentalException("Book " + b.getName() + " does not exist");
            }
            if (book.get().getRental() != null) {
                throw new InvalidRentalException("Book " + b.getName() + " is already rented");
            } else {
                book.get().setRental(rental);
            }
        };
    }
}
