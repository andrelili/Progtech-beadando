package com.example.sorozatok.repository;

import com.example.sorozatok.model.User;
import com.example.sorozatok.repository.IUserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MockUserRepository extends UserRepository implements IUserRepository {
    private final Map<String, User> users = new HashMap<>();

    @Override
    public boolean save(User user) {
        if (users.containsKey(user.getUsername())) {
            return false;
        }
        users.put(user.getUsername(), user);
        return true;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        User user = users.get(username);
        return Optional.ofNullable(user);
    }

    public void clear() {
        users.clear();
    }
}