package com.vanessa.librarymanagementapi.author.controller;

import com.vanessa.librarymanagementapi.author.dto.AuthorDTO;
import com.vanessa.librarymanagementapi.author.mapper.AuthorMapper;
import com.vanessa.librarymanagementapi.author.model.Author;
import com.vanessa.librarymanagementapi.author.service.AuthorService;
import com.vanessa.librarymanagementapi.commom.GenericController;
import com.vanessa.librarymanagementapi.exceptions.DuplicateEntryException;
import com.vanessa.librarymanagementapi.exceptions.dto.ResponseError;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController implements GenericController {
    private final AuthorService service;
    private final AuthorMapper mapper;

    @PostMapping
    @PreAuthorize("hasAnyRole('OPERATOR', 'MANAGER')")
    public ResponseEntity<Object> save(@RequestBody @Valid AuthorDTO authorDTO){
        try {
            Author author = mapper.toEntity(authorDTO);
            service.save(author);
            URI location = headerLocation(author.getId());
            return ResponseEntity.created(location).build();
        } catch (DuplicateEntryException e) {
            var error = ResponseError.conflict(e.getMessage());
            return ResponseEntity.status(error.status()).body(error);
        }
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('OPERATOR', 'MANAGER')")
    public ResponseEntity<AuthorDTO> getDetails(@PathVariable String id){
        var authorId = UUID.fromString(id);
        return service.getById(authorId)
                .map(author -> {
                    AuthorDTO dto = mapper.toDTO(author);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('OPERATOR', 'MANAGER')")
    public ResponseEntity<List<AuthorDTO>> search(@RequestParam(required = false) String name,
                                                  @RequestParam(required = false) String nationality){
        List<Author> result = service.search(name, nationality);
        List<AuthorDTO> authors = result
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(authors);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('OPERATOR', 'MANAGER')")
    public ResponseEntity<Object> update(@PathVariable @Valid String id, @RequestBody AuthorDTO dto){
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
    @PreAuthorize("hasRole('MANAGER')")
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
