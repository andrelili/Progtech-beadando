package com.example.sorozatok.service;
import com.example.sorozatok.model.Film;
import com.example.sorozatok.repository.FilmRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieManager {
    private final FilmRepository filmRepository;

    public MovieManager(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> loadFilms() {
        try {
            return filmRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void deleteFilm(int filmId) {
        try {
            filmRepository.delete(filmId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Film> filterFilms(List<Film> films, String filterText) {
        if (filterText == null || filterText.isEmpty()) {
            return films;
        }

        List<Film> filtered = new ArrayList<>();
        for (Film film : films) {
            if (film.getTitle().toLowerCase().contains(filterText.toLowerCase())) {
                filtered.add(film);
            }
        }
        return filtered;
    }
}
