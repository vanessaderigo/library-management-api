package com.vanessa.librarymanagementapi.book.mapper;

import com.vanessa.librarymanagementapi.author.model.Author;
import com.vanessa.librarymanagementapi.author.repository.AuthorRepository;
import com.vanessa.librarymanagementapi.book.dto.BookRegistrationDTO;
import com.vanessa.librarymanagementapi.book.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class BookMapper {
    @Autowired
    protected AuthorRepository authorRepository;

    @Mapping(target = "author", source = "authorID")
    public abstract Book toEntity(BookRegistrationDTO dto);

    protected Author map(UUID authorID) {
        return authorRepository.findById(authorID).orElse(null);
    }
}