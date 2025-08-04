package com.vanessa.librarymanagementapi.user.repository;

import com.vanessa.librarymanagementapi.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByLogin(String login);
}
