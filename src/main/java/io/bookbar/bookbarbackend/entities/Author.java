package io.bookbar.bookbarbackend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Blob;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue
    private int id;

    @Column(name= "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "image")
    private Blob image;

    @Column(name = "country")
    private String country;
}