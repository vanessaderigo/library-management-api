package com.vanessa.librarymanagementapi.author.mapper;

import com.vanessa.librarymanagementapi.author.dto.AuthorDTO;
import com.vanessa.librarymanagementapi.author.model.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toEntity(AuthorDTO dto);
    AuthorDTO toDTO(Author author);
}
