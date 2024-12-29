package com.pattabhi.library.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity // Marks this class as a persistent entity
@Table(name = "book") // Maps the entity to the "book" table
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private Long id;

    @Column(nullable = false, length = 150) // Maps to title column, with NOT NULL and length limit
    private String title;

    @Column(nullable = false, length = 100) // Maps to author column, with NOT NULL and length limit
    private String author;

    @Column(unique = true, nullable = false, length = 20) // Maps to isbn column, unique and NOT NULL
    private String isbn;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // Optional: toString, equals, and hashCode methods
    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}