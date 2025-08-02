package com.vanessa.librarymanagementapi.author.repository;

import com.vanessa.librarymanagementapi.author.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
    List<Author> findByName(String name);
    List<Author> findByNationality(String nationality);
    List<Author> findByNameAndNationality(String name, String nationality);
    Optional<Author> findByNameAndBirthDateAndNationality(String name, LocalDate birthDate, String nationality);
}
