package com.vanessa.librarymanagementapi.author.service;

import com.vanessa.librarymanagementapi.author.model.Author;
import com.vanessa.librarymanagementapi.author.repository.AuthorRepository;
import com.vanessa.librarymanagementapi.author.validator.AuthorValidator;
import com.vanessa.librarymanagementapi.book.repository.BookRepository;
import com.vanessa.librarymanagementapi.exceptions.OperationNotAllowedException;
import com.vanessa.librarymanagementapi.security.AuthenticatedUserProvider;
import com.vanessa.librarymanagementapi.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository repository;
    private final AuthorValidator validator;
    private final BookRepository bookRepository;
    private final AuthenticatedUserProvider userProvider;

    public Author save(Author author){
        validator.validate(author);
        User user = userProvider.getLoggedUser();
        author.setUserId(user);
        return repository.save(author);
    }

    public Optional<Author> getById(UUID id){
        return repository.findById(id);
    }

    public List<Author> search(String name, String nationality){
        Author author = new Author();
        author.setName(name);
        author.setNationality(nationality);
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Author> authorExample = Example.of(author, matcher);
        return repository.findAll(authorExample);
    }

    public void update(Author author){
        if (author.getId() == null){
            throw new IllegalArgumentException("Not found.");
        }
        validator.validate(author);
        repository.save(author);
    }

    public void delete(Author author){
        if (hasBooks(author)){
            throw new OperationNotAllowedException("This author can't be deleted because they have books in our system.");
        }
        repository.delete(author);
    }

    public boolean hasBooks(Author author){
        return bookRepository.existsByAuthor(author);
    }
}
