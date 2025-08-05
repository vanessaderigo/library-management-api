package com.vanessa.librarymanagementapi.user.dto;

import java.util.List;

public record UserDTO(String login, String email, String password, List<String> roles){
}
