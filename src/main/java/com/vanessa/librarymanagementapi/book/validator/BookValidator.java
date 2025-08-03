package com.vanessa.librarymanagementapi.book.validator;

import com.vanessa.librarymanagementapi.book.model.Book;
import com.vanessa.librarymanagementapi.book.repository.BookRepository;
import com.vanessa.librarymanagementapi.exceptions.DuplicateEntryException;
import com.vanessa.librarymanagementapi.exceptions.InvalidFieldException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookValidator {
    private static final int YEAR = 2020;

    private final BookRepository repository;

    public void validate(Book book){
        if (existsByIsbn(book)){
            throw new DuplicateEntryException("Isbn already registered.");
        }
        if (isRequiredPriceNull(book)){
            throw new InvalidFieldException("price", "For books published in 2020 or later, the price is required.");
        }
    }

    private boolean existsByIsbn(Book book) {
        List<Book> books = repository.findByIsbn(book.getIsbn());

        if (book.getId() == null) {
            return !books.isEmpty();
        }

        return books.stream().anyMatch(b -> !b.getId().equals(book.getId()));
    }

    private boolean isRequiredPriceNull(Book book){
        return book.getPrice() == null &&
                book.getPublicationDate().getYear() >= YEAR;
    }
}
