package com.vanessa.librarymanagementapi.book.mapper;

import com.vanessa.librarymanagementapi.author.mapper.AuthorMapper;
import com.vanessa.librarymanagementapi.author.model.Author;
import com.vanessa.librarymanagementapi.author.repository.AuthorRepository;
import com.vanessa.librarymanagementapi.book.dto.BookDetailsDTO;
import com.vanessa.librarymanagementapi.book.dto.BookRegistrationDTO;
import com.vanessa.librarymanagementapi.book.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = AuthorMapper.class)
public abstract class BookMapper {
    @Autowired
    protected AuthorRepository authorRepository;

    @Mapping(target = "author", source = "authorID", qualifiedByName = "mapAuthorIDToAuthor")
    public abstract Book toEntity(BookRegistrationDTO dto);

    @Named("mapAuthorIDToAuthor")
    protected Author map(UUID authorID) {
        return authorRepository.findById(authorID).orElse(null);
    }

    public abstract BookDetailsDTO toDTO(Book entity);
}