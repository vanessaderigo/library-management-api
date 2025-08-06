package com.vanessa.librarymanagementapi.client.repository;

import com.vanessa.librarymanagementapi.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    Client findByClientId(String clientId);
}
