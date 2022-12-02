package org.example.model;

import java.util.Date;
import java.util.List;


public class Rental {

    private User user;
    private Date startDate;
    private Date endDate;
    private List<Book> books;

    public Rental(User user, Date startDate, Date endDate, List<Book> books) {
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.books = books;
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
