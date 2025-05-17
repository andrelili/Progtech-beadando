package com.example.sorozatok.model;

public class Film {
    private int id;
    private String title;
    private Status status;
    private double averageRating;
    private int ratingCount;

    public Film(int id, String title, Status status, double averageRating, int ratingCount) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.averageRating = averageRating;
        this.ratingCount = ratingCount;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public double getAverageRating() {
        return averageRating;
    }
    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getRatingCount() {
        return ratingCount;
    }
    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }
}
