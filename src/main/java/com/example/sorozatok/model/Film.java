package com.example.sorozatok.model;

import javafx.beans.property.*;

public class Film {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty title = new SimpleStringProperty();
    private final ObjectProperty<Status> status = new SimpleObjectProperty<>();
    private final DoubleProperty averageRating = new SimpleDoubleProperty();
    private final IntegerProperty ratingCount = new SimpleIntegerProperty();

    public Film(int id, String title, Status status, double averageRating, int ratingCount) {
        this.id.set(id);
        this.title.set(title);
        this.status.set(status);
        this.averageRating.set(averageRating);
        this.ratingCount.set(ratingCount);
    }

    public Film(String title, Status status, double averageRating, int ratingCount) {
        this(0, title, status, averageRating, ratingCount);
    }

    // Property metódusok
    public IntegerProperty idProperty() { return id; }
    public StringProperty titleProperty() { return title; }
    public ObjectProperty<Status> statusProperty() { return status; }
    public DoubleProperty averageRatingProperty() { return averageRating; }
    public IntegerProperty ratingCountProperty() { return ratingCount; }

    // Getterek
    public int getId() { return id.get(); }
    public String getTitle() { return title.get(); }
    public Status getStatus() { return status.get(); }
    public double getAverageRating() { return averageRating.get(); }
    public int getRatingCount() { return ratingCount.get(); }

    // Setterek
    public void setId(int id) { this.id.set(id); }
    public void setTitle(String title) { this.title.set(title); }
    public void setStatus(Status status) { this.status.set(status); }
    public void setAverageRating(double averageRating) { this.averageRating.set(averageRating); }
    public void setRatingCount(int ratingCount) { this.ratingCount.set(ratingCount); }
}
