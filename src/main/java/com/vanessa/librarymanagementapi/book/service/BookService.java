package com.vanessa.librarymanagementapi.book.service;

import com.vanessa.librarymanagementapi.book.model.Book;
import com.vanessa.librarymanagementapi.book.model.BookGenre;
import com.vanessa.librarymanagementapi.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.vanessa.librarymanagementapi.book.repository.specification.BookSpecifications.*;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;

   public Book save(Book book){
       return repository.save(book);
   }

   public Optional<Book> getById(UUID id){
       return repository.findById(id);
   }

   public List<Book> search(String isbn, String title, String authorName, BookGenre genre, Integer publicationYear){
       Specification<Book> specs = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

       if (isbn != null){
           specs = specs.and(isbnEqual(isbn));
       }
       if (title != null){
           specs = specs.and(titleLike(title));
       }
       if (authorName != null){
           specs = specs.and(authorNameLike(authorName));
       }
       if (genre != null){
           specs = specs.and(genreEqual(genre));
       }
       if (publicationYear != null){
           specs = specs.and(publicationYearEqual(publicationYear));
       }

       return repository.findAll(specs);
   }

   public void delete(Book book){
       repository.delete(book);
   }
}
