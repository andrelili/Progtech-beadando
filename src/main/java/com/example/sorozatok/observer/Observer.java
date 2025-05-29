package com.example.sorozatok.observer;

import com.example.sorozatok.model.Film;

import java.util.Optional;

public interface Observer {
    void onMovieAdded(Film movie);

    void onMovieDeleted(Optional<Film> filmToDelete);

    void onMovieListChanged();
}
