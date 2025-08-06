package com.vanessa.librarymanagementapi.client.service;

import com.vanessa.librarymanagementapi.client.model.Client;
import com.vanessa.librarymanagementapi.client.repository.ClientRepository;
import com.vanessa.librarymanagementapi.client.validator.ClientValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository repository;
    private final ClientValidator validator;
    private final PasswordEncoder passwordEncoder;

    public Client save(Client client){
        validator.validate(client);
        var password = passwordEncoder.encode(client.getClientSecret());
        client.setClientSecret(password);
        return repository.save(client);
    }

    public Client getByClientId(String clientId){
        return repository.findByClientId(clientId);
    }
}
