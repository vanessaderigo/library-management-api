package com.vanessa.librarymanagementapi.client.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "client_id", unique = true, nullable = false, length = 150)
    private String clientId;

    @Column(name = "client_secret", nullable = false, length = 400)
    private String clientSecret;

    @Column(name = "redirect_uri", nullable = false, length = 200)
    private String redirectURI;

    @Column(length = 50)
    private String scope;
}
