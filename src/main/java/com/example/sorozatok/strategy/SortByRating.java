package com.example.sorozatok.strategy;

import com.example.sorozatok.model.Film;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortByRating implements SortStrategy {
    private final boolean ascending;

    public SortByRating(boolean ascending){
        this.ascending = ascending;
    }
    @Override
    public List<Film> sort(List<Film> films) {
        Comparator<Film> comparator = Comparator.comparing(Film::getAverageRating);
        return films.stream()
                .sorted(ascending ? comparator : comparator.reversed())
                .collect(Collectors.toList());
    }
}
