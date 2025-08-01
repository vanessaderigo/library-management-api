package com.vanessa.librarymanagementapi.author.dto;

import com.vanessa.librarymanagementapi.author.model.Author;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorDTO(UUID id,
                        String name,
                        LocalDate birthDate,
                        String nationality) {
    public Author mapToAuthor(){
        Author author = new Author();
        author.setName(this.name);
        author.setBirthDate(this.birthDate);
        author.setNationality(this.nationality);
        return author;
    }
}
