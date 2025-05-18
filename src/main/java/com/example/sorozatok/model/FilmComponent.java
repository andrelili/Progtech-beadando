package com.example.sorozatok.model;

public interface FilmComponent {
    int getId();
    String getTitle();
    Status getStatus();
    Genre getGenre();
    double getAverageRating();
    int getRatingCount();
}
