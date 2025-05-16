package com.example.sorozatok;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Movie {
    private final StringProperty title = new SimpleStringProperty();
    private final IntegerProperty year = new SimpleIntegerProperty();
    private final StringProperty genre = new SimpleStringProperty();

    public Movie(String title, int year, String genre){
        this.title.set(title);
        this.year.set(year);
        this.genre.set(genre);
    }
    public StringProperty titleProperty() {return title;}
    public IntegerProperty yearProperty() {return year;}
    public StringProperty genreProperty() {return genre;}

    public String getTitle() {return title.get();}
    public int getYear() {return year.get();}
    public String getGenre() {return genre.get();}

    public void setTitle(String title) {this.title.set(title);}
    public void setYear(int year) {this.year.set(year);}

    public void setGenre(String genre) {this.genre.set(genre);}

}
