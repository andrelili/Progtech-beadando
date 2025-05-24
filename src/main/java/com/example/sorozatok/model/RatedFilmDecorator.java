package com.example.sorozatok.model;

public class RatedFilmDecorator implements FilmComponent{
    private final FilmComponent baseFilm;
    private final double userRating; //A bejelentkezett felhasználó értékelése

    public RatedFilmDecorator(FilmComponent baseFilm, double userRating) {
        this.baseFilm = baseFilm;
        this.userRating = userRating;
    }


    @Override
    public int getId() {
        return baseFilm.getId();
    }

    @Override
    public String getTitle() {
        return baseFilm.getTitle();
    }

    @Override
    public Status getStatus() {
        return baseFilm.getStatus();
    }

    @Override
    public Genre getGenre() {return baseFilm.getGenre();}

    @Override
    public double getAverageRating() {
        return baseFilm.getAverageRating();
    }
    @Override
    public int getRatingCount() {
        return baseFilm.getRatingCount();
    }

    public FilmComponent getBaseFilm() {
        return baseFilm;
    }

    public void updateFilmRating() {
        if (baseFilm instanceof Film film) {
            double totalPoints = film.getAverageRating() * film.getRatingCount();
            int newCount = film.getRatingCount() + 1;
            double newAverage = (totalPoints + userRating) / newCount;
            film.setAverageRating(newAverage);
            film.setRatingCount(newCount);
        } else {
            throw new IllegalStateException("Only base Film instances can be updated!");
        }
    }
}
