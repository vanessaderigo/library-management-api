package com.vanessa.librarymanagementapi.author.controller;

import com.vanessa.librarymanagementapi.author.dto.AuthorDTO;
import com.vanessa.librarymanagementapi.author.model.Author;
import com.vanessa.librarymanagementapi.author.service.AuthorService;
import com.vanessa.librarymanagementapi.exceptions.DuplicateEntryException;
import com.vanessa.librarymanagementapi.exceptions.dto.ResponseError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService service;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody AuthorDTO authorDTO){
        try {
            Author author = authorDTO.mapToAuthor();
            service.save(author);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("{id}")
                    .buildAndExpand(author.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        } catch (DuplicateEntryException e) {
            var error = ResponseError.conflict(e.getMessage());
            return ResponseEntity.status(error.status()).body(error);
        }
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

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> search(@RequestParam(required = false) String name,
                                                  @RequestParam(required = false) String nationality){
        List<Author> result = service.search(name, nationality);
        List<AuthorDTO> authors = result.stream().map(author -> new AuthorDTO(author.getId(), author.getName(),
                author.getBirthDate(), author.getNationality())).collect(Collectors.toList());
        return ResponseEntity.ok(authors);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody AuthorDTO dto){
        try {
            var authorId = UUID.fromString(id);
            Optional<Author> optional = service.getById(authorId);
            if (optional.isEmpty()){
                ResponseEntity.notFound().build();
            }
            var author = optional.get();
            author.setName(dto.name());
            author.setBirthDate(dto.birthDate());
            author.setNationality(dto.nationality());
            service.update(author);
            return ResponseEntity.noContent().build();
        } catch (DuplicateEntryException e){
            var error = ResponseError.conflict(e.getMessage());
            return ResponseEntity.status(error.status()).body(error);
        }
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
