package com.example.sorozatok.test;

import com.example.sorozatok.repository.UserRepository;
import com.example.sorozatok.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserServiceTest {
    private UserService service;
    private UserRepository repo;

    @BeforeEach
    public void setup() {
        repo = new UserRepository();
        service = new UserService(repo);
    }

    @Test
    public void testRegisterSuccess() {
        assertTrue(service.register("test", "password"));
    }

    @Test
    public void testRegisterDuplicate() {
        service.register("test", "password");
        assertFalse(service.register("test", "password2"));
    }

    @Test
    public void testLoginSuccess() {
        service.register("test", "password");
        assertTrue(service.login("test", "password"));
    }

    @Test
    public void testLoginFail() {
        assertFalse(service.login("user", "wrongpass"));
    }
}

