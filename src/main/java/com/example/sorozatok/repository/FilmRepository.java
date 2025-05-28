package com.example.sorozatok.repository;
import com.example.sorozatok.database.DatabaseManager;
import com.example.sorozatok.model.Film;
import com.example.sorozatok.model.Genre;
import com.example.sorozatok.model.Status;
import com.example.sorozatok.utils.LoggerUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class FilmRepository {
    public void save(Film film) throws SQLException {
        String sql = "INSERT INTO films (title, status, genre, average_rating, rating_count, year) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, film.getTitle());
            stmt.setString(2, film.getStatus().name());
            stmt.setString(3, film.getGenre().name());
            stmt.setDouble(4, film.getAverageRating());
            stmt.setInt(5, film.getRatingCount());
            stmt.setInt(6, film.getYear());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                film.setId(generatedKeys.getInt(1));
            }
            LoggerUtil.info("Film successfully saved!");
        }
        catch (SQLException e) {
            LoggerUtil.error("Error saving film... \n\t - " + e.getMessage());
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
            LoggerUtil.info("Film requested with id!");
        }
        catch (SQLException e) {
            LoggerUtil.error("Error fetching film... \n\t - " + e.getMessage());
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
            LoggerUtil.info("Films found!");
        }
        catch (SQLException e) {
            LoggerUtil.error("Error fetching films... \n\t - " + e.getMessage());
        }
        return films;
    }

    private Film mapRow(ResultSet rs) throws SQLException {
        LoggerUtil.info("Film found!");
        return new Film(
                rs.getInt("id"),
                rs.getString("title"),
                Status.valueOf(rs.getString("status")),
                Genre.valueOf(rs.getString("genre")),
                rs.getDouble("average_rating"),
                rs.getInt("rating_count"),
                rs.getInt("year")
        );
    }

    public void update(Film film){
        String sql = "UPDATE films SET title = ?, status = ?, genre = ?, average_rating = ?, rating_count = ?, year = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, film.getTitle());
            stmt.setString(2, film.getStatus().toString());
            stmt.setString(3, film.getGenre().toString());
            stmt.setDouble(4, film.getAverageRating());
            stmt.setInt(5, film.getRatingCount());
            stmt.setInt(6, film.getYear());
            stmt.setInt(7, film.getId());
            stmt.executeUpdate();
            LoggerUtil.info("Film successfully updated!");
        }catch (SQLException e) {
            LoggerUtil.error("Error updating film...\n\t - " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM films WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            LoggerUtil.info("Film successfully deleted!");
        }
        catch (SQLException e) {
            LoggerUtil.error("Error deleting film...\n\t - " + e.getMessage());
        }
    }
}
