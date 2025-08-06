package com.vanessa.librarymanagementapi.client.validator;

import com.vanessa.librarymanagementapi.client.model.Client;
import com.vanessa.librarymanagementapi.client.repository.ClientRepository;
import com.vanessa.librarymanagementapi.exceptions.DuplicateEntryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientValidator {
    private final ClientRepository repository;

    public void validate(Client client){
        if (existsByClientId(client)){
            throw new DuplicateEntryException("Client ID already registered.");
        }
    }

    private boolean existsByClientId(Client client){
        Client existing = repository.findByClientId(client.getClientId());
        if (existing == null) return false;
        return client.getId() == null || existing.getId().equals(client.getId());
    }
}
