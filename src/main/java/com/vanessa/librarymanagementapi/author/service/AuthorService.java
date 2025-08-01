package com.vanessa.librarymanagementapi.author.service;

import com.vanessa.librarymanagementapi.author.model.Author;
import com.vanessa.librarymanagementapi.author.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void delete(Author author){
        repository.delete(author);
    }
}
