package com.vanessa.librarymanagementapi.client.mapper;

import com.vanessa.librarymanagementapi.client.dto.ClientDTO;
import com.vanessa.librarymanagementapi.client.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client toEntity(ClientDTO dto);
    
}
