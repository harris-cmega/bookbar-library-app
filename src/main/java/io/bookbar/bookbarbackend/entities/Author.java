package io.bookbar.bookbarbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue
    private long author_id;

    @Column(nullable = false)
    private String author_name;

    @Column(nullable = false)
    private String author_bio;

    @Column(nullable = false)
    private String author_country;
}
