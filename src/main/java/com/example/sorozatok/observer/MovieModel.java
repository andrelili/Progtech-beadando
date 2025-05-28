package com.example.sorozatok.observer;

import com.example.sorozatok.Movie;
import com.example.sorozatok.observer.Observer;
import java.util.ArrayList;
import java.util.List;

public class MovieModel {
    private List<Movie> movieList = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.onMovieListChanged();
        }
    }

    public void addMovie(Movie movie) {
        movieList.add(movie);
        notifyObservers(); // ÉRTESÍTÉS
    }

    public void removeMovie(Movie movie) {
        movieList.remove(movie);
        notifyObservers(); // ÉRTESÍTÉS
    }

    public List<Movie> getAllMovies() {
        return movieList;
    }

    // stb...
}

