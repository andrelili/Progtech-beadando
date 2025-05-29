package com.example.sorozatok.strategy;

import com.example.sorozatok.model.Film;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortByYear implements SortStrategy {
    private final boolean ascending;

    public SortByYear(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public List<Film> sort(List<Film> films) {
        Comparator<Film> comparator = Comparator.comparing(Film::getYear);
        return films.stream()
                .sorted(ascending ? comparator : comparator.reversed()) // Növekvő vagy csökkenő
                .collect(Collectors.toList());
    }
}
