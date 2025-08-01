package com.vanessa.librarymanagementapi.author.dto;

import com.vanessa.librarymanagementapi.author.model.Author;

import java.time.LocalDate;

public record AuthorDTO(String name,
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
