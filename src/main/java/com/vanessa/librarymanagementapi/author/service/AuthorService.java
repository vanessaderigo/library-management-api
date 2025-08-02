package com.vanessa.librarymanagementapi.author.service;

import com.vanessa.librarymanagementapi.author.model.Author;
import com.vanessa.librarymanagementapi.author.repository.AuthorRepository;
import com.vanessa.librarymanagementapi.author.validator.AuthorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository repository;
    private final AuthorValidator validator;

    public Author save(Author author){
        validator.validate(author);
        return repository.save(author);
    }

    public Optional<Author> getById(UUID id){
        return repository.findById(id);
    }

    public List<Author> search(String name, String nationality){
        if (name != null && nationality != null){
            return repository.findByNameAndNationality(name, nationality);
        } else if (name != null) {
            return repository.findByName(name);
        } else if (nationality != null) {
            return repository.findByNationality(nationality);
        }
        return repository.findAll();
    }

    public void update(Author author){
        if (author.getId() == null){
            throw new IllegalArgumentException("Not found.");
        }
        validator.validate(author);
        repository.save(author);
    }

    public void delete(Author author){
        repository.delete(author);
    }
}
