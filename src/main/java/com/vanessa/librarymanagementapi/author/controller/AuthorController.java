package com.vanessa.librarymanagementapi.author.controller;

import com.vanessa.librarymanagementapi.author.dto.AuthorDTO;
import com.vanessa.librarymanagementapi.author.model.Author;
import com.vanessa.librarymanagementapi.author.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping("{id}")
    public ResponseEntity<AuthorDTO> getDetails(@PathVariable String id){
        var authorId = UUID.fromString(id);
        Optional<Author> optional = service.getById(authorId);
        if (optional.isPresent()) {
            Author author = optional.get();
            AuthorDTO dto = new AuthorDTO(author.getId(), author.getName(), author.getBirthDate(), author.getNationality());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        var authorId = UUID.fromString(id);
        Optional<Author> optional = service.getById(authorId);
        if (optional.isEmpty()){
            ResponseEntity.notFound().build();
        }
        service.delete(optional.get());
        return ResponseEntity.noContent().build();
    }
}
