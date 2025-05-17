package com.example.sorozatok.model;

public class RatedFilmDecorator implements FilmComponent{
    private final FilmComponent baseFilm;
    private final double userRating; //A bejelentkezett felhasználó értékelése

    public RatedFilmDecorator(Film basefilm, double userRating) {
        this.baseFilm = basefilm;
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
        if (!(baseFilm instanceof Film)) {
            throw new IllegalStateException("Only base Film instances can be updated!");
        }

        Film film = (Film) baseFilm;
        double currentTotal = film.getAverageRating() * film.getRatingCount(); // összes eddigi értékelés
        int newCount = film.getRatingCount() + 1;
        double newAverage = (currentTotal + userRating) / newCount;

        film.setAverageRating(newAverage);
        film.setRatingCount(newCount);
    }
}
