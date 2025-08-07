package com.vanessa.librarymanagementapi.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "User")
public record UserDTO(String login, String email, String password, List<String> roles){
}
