package com.example.sorozatok.service;
import com.example.sorozatok.model.Film;
import com.example.sorozatok.observer.Observable;
import com.example.sorozatok.observer.Observer;
import com.example.sorozatok.repository.FilmRepository;
import com.example.sorozatok.strategy.SortStrategy;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
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
    public List<Film> sort(List<Film> films, SortStrategy strategy) {
        return strategy.sort(new ArrayList<>(films));
    }

}
