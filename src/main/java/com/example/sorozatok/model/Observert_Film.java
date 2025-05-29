package com.example.sorozatok.model;

import com.example.sorozatok.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class Observert_Film {
    private List<Film> movieList = new ArrayList<>();
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

    public void addMovie(Film movie) {
        movieList.add(movie);
        notifyObservers(); // ÉRTESÍTÉS
    }

    public void removeMovie(Film movie) {
        movieList.remove(movie);
        notifyObservers(); // ÉRTESÍTÉS
    }

    public List<Film> getAllMovies() {
        return movieList;
    }

    // stb...
}

