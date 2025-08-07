package com.vanessa.librarymanagementapi.client.controller;

import com.vanessa.librarymanagementapi.client.dto.ClientDTO;
import com.vanessa.librarymanagementapi.client.mapper.ClientMapper;
import com.vanessa.librarymanagementapi.client.model.Client;
import com.vanessa.librarymanagementapi.client.service.ClientService;
import com.vanessa.librarymanagementapi.commom.GenericController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
@Tag(name = "Clients")
@RequiredArgsConstructor
public class ClientController implements GenericController {
    private final ClientService service;
    private final ClientMapper mapper;

    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Save", description = "Register a client.")
    @ApiResponse(responseCode = "201", description = "Registered successfully.")
    public ResponseEntity<Object> save(@RequestBody @Valid ClientDTO dto){
        Client client = mapper.toEntity(dto);
        service.save(client);
        var uri = headerLocation(client.getId());
        return ResponseEntity.created(uri).build();
    }
}
