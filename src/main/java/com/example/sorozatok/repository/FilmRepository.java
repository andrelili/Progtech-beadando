package com.example.sorozatok.repository;
import com.example.sorozatok.database.DatabaseManager;
import com.example.sorozatok.model.Film;
import com.example.sorozatok.model.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FilmRepository {
    public void save(Film film) throws SQLException {
        String sql = "INSERT INTO films (title, status, average_rating, rating_count) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, film.getTitle());
            stmt.setString(2, film.getStatus().name());
            stmt.setDouble(3, film.getAverageRating());
            stmt.setInt(4, film.getRatingCount());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                film.setId(generatedKeys.getInt(1));
            }
        }
    }

    public Optional<Film> findById(int id) throws SQLException {
        String sql = "SELECT * FROM films WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapRow(rs));
            }
        }
        return Optional.empty();
    }

    public List<Film> findAll() throws SQLException {
        List<Film> films = new ArrayList<>();
        String sql = "SELECT * FROM films";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                films.add(mapRow(rs));
            }
        }
        return films;
    }

    private Film mapRow(ResultSet rs) throws SQLException {
        return new Film(
                rs.getInt("id"),
                rs.getString("title"),
                Status.valueOf(rs.getString("status")),
                rs.getDouble("average_rating"),
                rs.getInt("rating_count"),
                rs.getInt("year") // <-- EZ HIÁNYZOTT
        );
    }

    public void update(Film film){
        String sql = "UPDATE films SET title = ?, status = ?, average_rating = ?, rating_count = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, film.getTitle());
            stmt.setString(2, film.getStatus().toString());
            stmt.setDouble(3, film.getAverageRating());
            stmt.setInt(4, film.getRatingCount());
            stmt.setInt(5, film.getId());
            stmt.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM films WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
