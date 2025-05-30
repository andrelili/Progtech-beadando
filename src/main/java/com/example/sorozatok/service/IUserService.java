package com.example.sorozatok.service;

public interface IUserService {
    boolean login(String username, String password);
    boolean register(String username, String password);
}
