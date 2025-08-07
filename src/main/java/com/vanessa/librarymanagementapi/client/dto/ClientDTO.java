package com.vanessa.librarymanagementapi.client.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "Client")
public record ClientDTO(@NotBlank(message = "Required field") String clientId,
                        @NotBlank(message = "Required field") String clientSecret,
                        @NotBlank(message = "Required field") String redirectURI,
                        String scope) {
}
