package com.vanessa.librarymanagementapi.book.repository;

import com.vanessa.librarymanagementapi.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
}
