package com.example.sorozatok.repository;

import com.example.sorozatok.model.User;
import com.example.sorozatok.database.DatabaseManager;
import com.example.sorozatok.utils.LoggerUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class UserRepository implements IUserRepository{
    public boolean save(User user) {
        String sql = "INSERT INTO users (username, password_hash) VALUES (?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPasswordHash());
            stmt.executeUpdate();
            LoggerUtil.info("User saved");
            return true;

        } catch (SQLIntegrityConstraintViolationException e) {
            LoggerUtil.error("Error saving user... Username already exists");
            // felhasználónév már létezik
            return false;
        } catch (SQLException e) {
            LoggerUtil.error("Error saving user... ");
            e.printStackTrace();
            return false;
        }
    }

    public Optional<User> findByUsername(String username) {
        String sql = "SELECT username, password_hash FROM users WHERE username = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String uname = rs.getString("username");
                String hash = rs.getString("password_hash");
                LoggerUtil.info("Username found: " + uname);
                return Optional.of(new User(uname, hash));
            } else {
                LoggerUtil.error("Username not found \n\t - " + username);
                return Optional.empty();
            }

        } catch (SQLException e) {
            LoggerUtil.error("Error fetching user...\n\t - " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
