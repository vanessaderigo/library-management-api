package com.vanessa.librarymanagementapi.author.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

@Schema(name = "Author")
public record AuthorDTO(UUID id,
                        @NotBlank(message = "Required field.")
                        @Size(max = 150, message = "Invalid field length")
                        String name,
                        @NotNull(message = "Required field.")
                        @Past
                        LocalDate birthDate,
                        @NotBlank(message = "Required field.")
                        @Size(max = 50, message = "Invalid field length")
                        String nationality) {
}
