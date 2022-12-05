package org.example.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Rental {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "books")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Book> books;

    public Rental(String userName, Date startDate, Date endDate, List<Book> books) {
        this.userName = userName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.books = books;
    }

    public Rental() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
