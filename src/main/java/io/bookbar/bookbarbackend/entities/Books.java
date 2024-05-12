package io.bookbar.bookbarbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;

@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ISBN;

    @Column(name = "title")
    private String title;

    @Column(name = "language")
    private String language;

    @Column(name = "publication_date")
    private String publicationDate;

    @Lob
    @Column(name = "image")
    private Blob image;

    @Column(name = "page_number")
    private int pageNumber;

    @Column(name = "price")
    private double price;

    @Column(name = "descrition")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_ID")
    private Author authorID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_ID")
    private Library libraryID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_ID")
    private Publisher publisherID;

}