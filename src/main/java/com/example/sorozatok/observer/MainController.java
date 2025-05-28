package com.example.sorozatok.observer;

import com.example.sorozatok.observer.MovieModel;
import com.example.sorozatok.observer.Observer;

public class MainController implements Observer {
    private final MovieModel movieModel;

    public MainController(MovieModel movieModel) {
        this.movieModel = movieModel;
        movieModel.addObserver(this); // FELIRATKOZÁS
    }

    @Override
    public void onMovieListChanged() {
        // UI vagy logika frissítése
        System.out.println("A film lista megváltozott!"); // vagy frissítsd a listanézetet
    }

    // további metódusok...
}
