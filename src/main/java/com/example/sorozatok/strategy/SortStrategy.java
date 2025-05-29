package com.example.sorozatok.strategy;

import com.example.sorozatok.model.Film;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public interface SortStrategy {
    List<Film> sort(List<Film> films);
}

