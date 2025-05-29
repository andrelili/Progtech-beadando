package com.example.sorozatok.service;
import com.example.sorozatok.model.Film;
import com.example.sorozatok.observer.Observable;
import com.example.sorozatok.observer.Observer;
import com.example.sorozatok.repository.FilmRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieManager extends Observable<Observer> {
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
            Optional<Film> filmToDelete = filmRepository.findById(filmId);
            if (filmToDelete.isPresent()) {
                filmRepository.delete(filmId);
                notifyObservers(observer -> observer.onMovieDeleted(filmToDelete));
            }
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
