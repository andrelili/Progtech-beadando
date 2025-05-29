package com.example.sorozatok.service;

import com.example.sorozatok.model.Film;
import com.example.sorozatok.model.Genre;
import com.example.sorozatok.model.Status;
import com.example.sorozatok.repository.FilmRepository;

public class MovieService {
    private final FilmRepository filmRepository;

    public MovieService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public void saveFilm(String title, double rating, int year, Status status, Genre genre) throws Exception {
        validateFilm(title, rating, year, status, genre);
        Film newFilm = new Film(title, status, genre, rating, 1, year);
        filmRepository.save(newFilm);
    }

    public void updateFilm(Film film, String title, double rating, int year, Status status, Genre genre) throws Exception {
        validateFilm(title, rating, year, status, genre);
        film.setTitle(title);
        film.setStatus(status);
        film.setGenre(genre);
        film.setAverageRating(rating);
        film.setYear(year);
        filmRepository.update(film);
    }

    private void validateFilm(String title, double rating, int year, Status status, Genre genre) throws Exception {
        if (title == null || title.isEmpty()) {
            throw new Exception("Title cannot be empty");
        }
        if (rating < 0 || rating > 5) {
            throw new Exception("Rating must be between 0 and 5");
        }
        if (year < 1888 || year > 2100) {
            throw new Exception("Year must be a valid year");
        }
        if (status == null) {
            throw new Exception("Status must be selected");
        }
        if (genre == null) {
            throw new Exception("Genre must be selected");
        }
    }
}
