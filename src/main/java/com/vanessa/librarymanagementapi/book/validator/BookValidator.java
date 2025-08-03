package com.vanessa.librarymanagementapi.book.validator;

import com.vanessa.librarymanagementapi.book.model.Book;
import com.vanessa.librarymanagementapi.book.repository.BookRepository;
import com.vanessa.librarymanagementapi.exceptions.DuplicateEntryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookValidator {
    private final BookRepository repository;

    public void validate(Book book){
        if (existsByIsbn(book)){
            throw new DuplicateEntryException("Isbn already registered.");
        }
    }

    private boolean existsByIsbn(Book book) {
        List<Book> books = repository.findByIsbn(book.getIsbn());

        if (book.getId() == null) {
            return !books.isEmpty();
        }

        return books.stream().anyMatch(b -> !b.getId().equals(book.getId()));
    }
}
