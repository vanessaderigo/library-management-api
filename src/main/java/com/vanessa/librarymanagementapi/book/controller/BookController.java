package com.vanessa.librarymanagementapi.book.controller;

import com.vanessa.librarymanagementapi.book.dto.BookDetailsDTO;
import com.vanessa.librarymanagementapi.book.dto.BookRegistrationDTO;
import com.vanessa.librarymanagementapi.book.mapper.BookMapper;
import com.vanessa.librarymanagementapi.book.model.Book;
import com.vanessa.librarymanagementapi.book.service.BookService;
import com.vanessa.librarymanagementapi.commom.GenericController;
import com.vanessa.librarymanagementapi.exceptions.DuplicateEntryException;
import com.vanessa.librarymanagementapi.exceptions.dto.ResponseError;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController implements GenericController {
    private final BookService service;
    private final BookMapper mapper;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid BookRegistrationDTO dto){
        try {
            Book book = mapper.toEntity(dto);
            service.save(book);
            var url = headerLocation(book.getId());
            return ResponseEntity.created(url).build();
        } catch (DuplicateEntryException e){
            var error = ResponseError.conflict(e.getMessage());
            return ResponseEntity.status(error.status()).body(error);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDetailsDTO> getDetails(@PathVariable String id){
        var bookId = UUID.fromString(id);
        return service.getById(bookId)
                .map(book -> {
                    BookDetailsDTO dto = mapper.toDTO(book);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
        var bookId = UUID.fromString(id);
        return service.getById(bookId).map(book -> {
            service.delete(book);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
