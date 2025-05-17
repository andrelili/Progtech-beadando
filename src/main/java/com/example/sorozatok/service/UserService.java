package com.example.sorozatok.service;

import com.example.sorozatok.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

public class UserService {
    private final Map<String, User> users = new HashMap<>();
    public boolean register(String username, String password){
        if(users.containsKey(username)) return false;
        String hash = hashPassword(password);
        users.put(username,new User(username,hash));
        return true;
    }

    public boolean login(String username, String password){
        User user = users.get(username);
        if(user == null) return false;
        return user.getPasswordHash().equals(hashPassword(password));
    }
    private String hashPassword(String password){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes= digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(byte b : hashBytes) sb.append(String.format("%02x",b));
            return sb.toString();
        }catch (Exception e){
            throw new RuntimeException("Hashing error", e);
        }
    }
    public UserService(){
        register("admin","1234");
        register("teszt","jelszo");
    }
}
