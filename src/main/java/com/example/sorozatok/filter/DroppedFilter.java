package com.example.sorozatok.filter;

import com.example.sorozatok.model.Film;
import com.example.sorozatok.model.Status;

import java.util.List;
import java.util.stream.Collectors;

public class DroppedFilter implements FilmFilterStrategy {
    @Override
    public List<Film> filter(List<Film> films) {
        return films.stream()
                .filter(film -> film.getStatus().equals(Status.DROPPED.name()))
                .collect(Collectors.toList());
    }
}
