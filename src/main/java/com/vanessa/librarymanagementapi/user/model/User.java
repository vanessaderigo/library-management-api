package com.vanessa.librarymanagementapi.user.model;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String login;

    private String password;

    @Type(ListArrayType.class)
    @Column(columnDefinition = "varchar[]")
    private List<String> roles;
}
