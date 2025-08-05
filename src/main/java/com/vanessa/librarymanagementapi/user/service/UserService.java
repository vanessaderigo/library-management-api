package com.vanessa.librarymanagementapi.user.service;

import com.vanessa.librarymanagementapi.user.model.User;
import com.vanessa.librarymanagementapi.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public void save(User user){
        var password = user.getPassword();
        user.setPassword(encoder.encode(password));
        repository.save(user);
    }

    public User getByLogin(String login){
        return repository.findByLogin(login);
    }

    public User getByEmail(String email){
        return repository.findByEmail(email);
    }

    public Optional<User> getById(UUID id){
        return repository.findById(id);
    }

    public void update(User user){
        if (user.getId() == null){
            throw new IllegalArgumentException("Not found.");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        repository.save(user);
    }

    public void delete(User user){
        repository.delete(user);
    }
}
