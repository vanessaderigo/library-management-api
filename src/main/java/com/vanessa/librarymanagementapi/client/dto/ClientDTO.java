package com.vanessa.librarymanagementapi.client.dto;

import jakarta.validation.constraints.NotBlank;

public record ClientDTO(@NotBlank(message = "Required field") String clientId,
                        @NotBlank(message = "Required field") String clientSecret,
                        @NotBlank(message = "Required field") String redirectURI,
                        String scope) {
}
