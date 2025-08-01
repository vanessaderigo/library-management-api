package com.vanessa.librarymanagementapi.author.controller;

import com.vanessa.librarymanagementapi.author.dto.AuthorDTO;
import com.vanessa.librarymanagementapi.author.model.Author;
import com.vanessa.librarymanagementapi.author.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService service;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody AuthorDTO authorDTO){
        Author author = authorDTO.mapToAuthor();
        service.save(author);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(author.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
