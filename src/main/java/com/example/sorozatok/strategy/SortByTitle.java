package com.example.sorozatok.strategy;

import com.example.sorozatok.model.Film;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortByTitle implements SortStrategy {
    private final boolean ascending;

    public SortByTitle(boolean ascending){
        this.ascending = ascending;
    }
    @Override
    public List<Film> sort(List<Film> films) {
        Comparator<Film> comparator = Comparator.comparing(Film::getTitle);
        return films.stream()
                .sorted(ascending ? comparator : comparator.reversed())
                .collect(Collectors.toList());
    }
}
