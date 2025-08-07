package com.vanessa.librarymanagementapi.book.dto;

import com.vanessa.librarymanagementapi.author.dto.AuthorDTO;
import com.vanessa.librarymanagementapi.book.model.BookGenre;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Schema(name = "Book")
public record BookDetailsDTO(UUID id,
                             String isbn,
                             String title,
                             LocalDate publicationDate,
                             BookGenre genre,
                             BigDecimal price,
                             AuthorDTO author) {
}
