package com.vanessa.librarymanagementapi.book.service;

import com.vanessa.librarymanagementapi.book.model.Book;
import com.vanessa.librarymanagementapi.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;

   public Book save(Book book){
       return repository.save(book);
   }
}
