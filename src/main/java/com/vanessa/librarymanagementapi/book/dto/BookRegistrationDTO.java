package com.vanessa.librarymanagementapi.book.dto;

import com.vanessa.librarymanagementapi.book.model.BookGenre;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record BookRegistrationDTO(@NotBlank(message = "Required field")
                                  String isbn,
                                  @NotBlank(message = "Required field")
                                  String title,
                                  @NotNull(message = "Required field")
                                  @Past
                                  LocalDate publicationDate,
                                  BookGenre genre,
                                  BigDecimal price,
                                  @NotNull(message = "Required field")
                                  UUID authorID) {
}
