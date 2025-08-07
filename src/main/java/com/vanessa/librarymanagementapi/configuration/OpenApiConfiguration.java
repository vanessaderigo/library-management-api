package com.vanessa.librarymanagementapi.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "library-management-api",
        version = "v1",
        contact = @Contact(name = "Vanessa Derigo", email = "vderigoesteves@gmail.com")))
public class OpenApiConfiguration {
}
