package org.example.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Rental {

    @Id
    @GeneratedValue
    private Integer id;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user")
    private User user;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;

    // TODO autoriser delete rental
    @Column(name = "books")
    @OneToMany(cascade = CascadeType.MERGE)
    private List<Book> books;

    public Rental(User user, Date startDate, Date endDate, List<Book> books) {
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.books = books;
    }

    public Rental() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
