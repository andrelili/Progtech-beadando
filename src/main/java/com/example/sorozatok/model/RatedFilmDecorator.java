package com.example.sorozatok.model;

public class RatedFilmDecorator {
    private Film baseFilm;
    private double userRating; //A bejelentkezett felhasználó értékelése

    public RatedFilmDecorator(Film basefilm, double userRating) {
        this.baseFilm = basefilm;
        this.userRating = userRating;
    }

    public int getId() {
        return baseFilm.getId();
    }

    public String getTitle() {
        return baseFilm.getTitle();
    }

    public Status getStatus() {
        return baseFilm.getStatus();
    }

    public Film getBaseFilm() {
        return baseFilm;
    }

    public void updateFilmRating() {
        double currentTotal = baseFilm.getAverageRating() * baseFilm.getRatingCount(); // összes eddigi értékelés
        int newCount = baseFilm.getRatingCount() + 1;
        double newAverage = (currentTotal + userRating) / newCount;

        baseFilm.setAverageRating(newAverage);
        baseFilm.setRatingCount(newCount);
    }
}
