package com.example.sorozatok.repository;

import com.example.sorozatok.model.User;
import java.util.Optional;

public interface IUserRepository {
    boolean save(User user);
    Optional<User> findByUsername(String username);
}