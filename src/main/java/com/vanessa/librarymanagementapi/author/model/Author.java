package com.vanessa.librarymanagementapi.author.model;

import com.vanessa.librarymanagementapi.book.model.Book;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@Data
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false, length = 50)
    private String nationality;

    @OneToMany(mappedBy = "author")
    private List<Book> books;
}
