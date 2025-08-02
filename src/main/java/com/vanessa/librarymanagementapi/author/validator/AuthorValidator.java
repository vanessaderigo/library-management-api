package com.vanessa.librarymanagementapi.author.validator;

import com.vanessa.librarymanagementapi.author.model.Author;
import com.vanessa.librarymanagementapi.author.repository.AuthorRepository;
import com.vanessa.librarymanagementapi.exceptions.DuplicateEntryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorValidator {
    private final AuthorRepository repository;

    public void validate(Author author){
        if (isAuthorAlreadyRegistered(author)){
            throw new DuplicateEntryException("Author already exists.");
        }
    }

    private boolean  isAuthorAlreadyRegistered(Author author){
        Optional<Author> optional = repository.findByNameAndBirthDateAndNationality(author.getName(), author.getBirthDate(), author.getNationality());
        if (author.getId() == null){
            return optional.isPresent();
        }
        return !author.getId().equals(optional.get().getId()) && optional.isPresent();
    }
}
