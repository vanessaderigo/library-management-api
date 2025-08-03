package com.vanessa.librarymanagementapi.book.repository;

import com.vanessa.librarymanagementapi.author.model.Author;
import com.vanessa.librarymanagementapi.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID>, JpaSpecificationExecutor<Book> {
    boolean existsByAuthor(Author author);
    List<Book> findByIsbn(String isbn);
}