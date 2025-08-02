package com.vanessa.librarymanagementapi.author.repository;

import com.vanessa.librarymanagementapi.author.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
    Optional<Author> findByNameAndBirthDateAndNationality(String name, LocalDate birthDate, String nationality);
}
