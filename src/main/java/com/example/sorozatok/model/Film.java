package com.example.sorozatok.model;

import javafx.beans.property.*;


public class Film implements FilmComponent{
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty title = new SimpleStringProperty();
    private final ObjectProperty<Genre> genre = new SimpleObjectProperty<>();
    private final ObjectProperty<Status> status = new SimpleObjectProperty<>();
    private final DoubleProperty averageRating = new SimpleDoubleProperty();
    private final IntegerProperty ratingCount = new SimpleIntegerProperty();
    private final IntegerProperty year = new SimpleIntegerProperty();


    public Film(int id, String title, Status status, Genre genre, double averageRating, int ratingCount, int year) {
        this.id.set(id);
        this.title.set(title);
        this.genre.set(genre);
        this.status.set(status);
        this.averageRating.set(averageRating);
        this.ratingCount.set(ratingCount);
        this.year.set(year);
    }

    public Film(String title, Status status, Genre genre, double averageRating, int ratingCount, int year) {
        this(0, title, status, genre, averageRating, ratingCount, year);
    }


    // Property metódusok
    public StringProperty titleProperty() { return title; }
    public ObjectProperty<Status> statusProperty() { return status; }
    public ObjectProperty<Genre> genreProperty() { return genre; }

    // Getterek
    public int getId() { return id.get(); }
    public String getTitle() { return title.get(); }
    public Status getStatus() { return status.get(); }
    public Genre getGenre() { return genre.get(); }
    public double getAverageRating() { return averageRating.get(); }
    public int getRatingCount() { return ratingCount.get(); }
    public int getYear() { return year.get(); }
    // Setterek
    public void setId(int id) { this.id.set(id); }
    public void setTitle(String title) { this.title.set(title); }
    public void setStatus(Status status) { this.status.set(status); }
    public void setGenre(Genre genre) { this.genre.set(genre); }
    public void setAverageRating(double averageRating) { this.averageRating.set(averageRating); }
    public void setRatingCount(int ratingCount) { this.ratingCount.set(ratingCount); }
    public void setYear(int year) { this.year.set(year); }


}
