package com.vanessa.librarymanagementapi.book.controller;

import com.vanessa.librarymanagementapi.book.dto.BookRegistrationDTO;
import com.vanessa.librarymanagementapi.book.mapper.BookMapper;
import com.vanessa.librarymanagementapi.book.model.Book;
import com.vanessa.librarymanagementapi.book.service.BookService;
import com.vanessa.librarymanagementapi.exceptions.DuplicateEntryException;
import com.vanessa.librarymanagementapi.exceptions.dto.ResponseError;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService service;
    private final BookMapper mapper;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid BookRegistrationDTO dto){
        try {
            Book book = mapper.toEntity(dto);
            service.save(book);
            return ResponseEntity.ok(book);
        } catch (DuplicateEntryException e){
            var error = ResponseError.conflict(e.getMessage());
            return ResponseEntity.status(error.status()).body(error);
        }
    }
}
