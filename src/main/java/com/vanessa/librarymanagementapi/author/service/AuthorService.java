package com.vanessa.librarymanagementapi.author.service;

import com.vanessa.librarymanagementapi.author.model.Author;
import com.vanessa.librarymanagementapi.author.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository repository;

    public Author save(Author author){
        return repository.save(author);
    }

    public Optional<Author> getById(UUID id){
        return repository.findById(id);
    }
}
