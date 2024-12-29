package com.pattabhi.library.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "borrowing") // Maps to the "borrowing" table
public class BorrowingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrementing primary key
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // Many borrowings can reference one book
    @JoinColumn(name = "book_id", referencedColumnName = "id") // Maps to the book_id column
    private BookEntity book;

    @ManyToOne(fetch = FetchType.LAZY) // Many borrowings can reference one member
    @JoinColumn(name = "member_id", referencedColumnName = "id") // Maps to the member_id column
    private MemberEntity member;

    @Column(name = "borrowed_date") // Maps to borrowed_date column
    private LocalDate borrowedDate;

    @Column(name = "return_date") // Maps to return_date column
    private LocalDate returnDate;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public MemberEntity getMember() {
        return member;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    // Optional: toString, equals, and hashCode methods
    @Override
    public String toString() {
        return "BorrowingEntity{" +
                "id=" + id +
                ", book=" + book +
                ", member=" + member +
                ", borrowedDate=" + borrowedDate +
                ", returnDate=" + returnDate +
                '}';
    }
}