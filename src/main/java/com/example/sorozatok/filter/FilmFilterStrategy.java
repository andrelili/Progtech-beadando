package com.example.sorozatok.filter;

import com.example.sorozatok.model.Film;
import java.util.List;

public interface FilmFilterStrategy {
    List<Film> filter(List<Film> films);
}
