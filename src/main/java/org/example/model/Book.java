package org.example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "book_name")
    private String name;

    @JsonBackReference
    @ManyToOne
    private Rental rental;

    public Book(String name) {
        this.name = name;
    }

    public Book() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }
}
