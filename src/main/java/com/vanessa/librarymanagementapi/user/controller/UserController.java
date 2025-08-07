package com.vanessa.librarymanagementapi.user.controller;

import com.vanessa.librarymanagementapi.commom.GenericController;
import com.vanessa.librarymanagementapi.user.dto.UserDTO;
import com.vanessa.librarymanagementapi.user.mapper.UserMapper;
import com.vanessa.librarymanagementapi.user.model.User;
import com.vanessa.librarymanagementapi.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@Tag(name = "Users")
@RequiredArgsConstructor
public class UserController implements GenericController {
    private final UserService service;
    private final UserMapper mapper;

    @PostMapping
    @Operation(summary = "Save", description = "Register a user.")
    @ApiResponse(responseCode = "201", description = "Registered successfully.")
    public ResponseEntity<Object> save(@RequestBody UserDTO dto){
        User user = mapper.toEntity(dto);
        service.save(user);
        var url = headerLocation(user.getId());
        return ResponseEntity.created(url).build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('OPERATOR', 'MANAGER')")
    @Operation(summary = "Update", description = "Update an existing user.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Successfully updated."),
            @ApiResponse(responseCode = "404", description = "User not found.")})
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody UserDTO dto){
        var userId = UUID.fromString(id);
        return service.getById(userId).map(user -> {
            User second = mapper.toEntity(dto);
            user.setLogin(second.getLogin());
            user.setEmail(second.getEmail());
            user.setPassword(second.getPassword());
            user.setRoles(second.getRoles());
            service.update(user);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "Delete", description = "Deletes user by id.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Successfully deleted."),
            @ApiResponse(responseCode = "404", description = "User not found.")})
    public ResponseEntity<Object> delete(@PathVariable String id){
        var userId = UUID.fromString(id);
        return service.getById(userId).map(user -> {
            service.delete(user);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
