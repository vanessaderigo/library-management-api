package com.vanessa.librarymanagementapi.author.repository;

import com.vanessa.librarymanagementapi.author.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
}
