package io.bookbar.bookbarbackend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_ID", referencedColumnName = "author_ID")
    private Author author;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false, unique = true)
    private String ISBN;

    // konstruktore pa arguments
    public Books() {
    }


    public Books(Long id, String title, Author author, String publisher, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.ISBN = ISBN;
    }
}