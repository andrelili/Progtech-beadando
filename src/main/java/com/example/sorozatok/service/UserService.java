package com.example.sorozatok.service;

import com.example.sorozatok.model.User;
import com.example.sorozatok.repository.UserRepository;
import com.example.sorozatok.utils.LoggerUtil;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public boolean register(String username, String password){
        // Ha már van ilyen nevű felhasználó, ne engedjük
        if (repo.findByUsername(username).isPresent()) {
            LoggerUtil.error("Username already exists \n\t - " + username);
            return false;
        }
        String hash = hashPassword(password);
        LoggerUtil.info("Successfully registered user \n\t - " + username);
        return repo.save(new User(username, hash));
    }

    public boolean login(String username, String password){
        Optional<User> userOpt = repo.findByUsername(username);
        if (userOpt.isEmpty()) return false;
        String hash = hashPassword(password);
        LoggerUtil.info("Successfully logged in user \n\t - " + username);
        return userOpt.get().getPasswordHash().equals(hash);
    }

    private String hashPassword(String password){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) sb.append(String.format("%02x", b));
            LoggerUtil.info("Hashed");
            return sb.toString();
        } catch (Exception e) {
            LoggerUtil.error("Error hashing password... \n\t - " + e.getMessage());
            throw new RuntimeException("Hashing error", e);
        }
    }
}