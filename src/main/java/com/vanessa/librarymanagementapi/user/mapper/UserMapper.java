package com.vanessa.librarymanagementapi.user.mapper;

import com.vanessa.librarymanagementapi.user.dto.UserDTO;
import com.vanessa.librarymanagementapi.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserDTO dto);
}
