package com.example.sorozatok.test;

import com.example.sorozatok.model.Film;
import com.example.sorozatok.model.Genre;
import com.example.sorozatok.model.Status;
import com.example.sorozatok.strategy.SortByYear;
import com.example.sorozatok.strategy.SortStrategy;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortStrategyTest {
    @Test
    public void testSortByYearAscending(){
        List<Film> films = Arrays.asList(
                new Film("Film A", Status.PLANNED, Genre.ACTION, 4.5, 100, 2021),
                new Film("Film B", Status.WATCHED, Genre.COMEDY, 4.2,200,2019),
                new Film("Film C", Status.DROPPED, Genre.DRAMA, 2.1, 150, 2023)
        );
        SortStrategy sortStrategy = new SortByYear(true);
        List<Film> sortedFilms = sortStrategy.sort(films);

        assertEquals(2019, sortedFilms.get(0).getYear());
        assertEquals(2021, sortedFilms.get(1).getYear());
        assertEquals(2023, sortedFilms.get(2).getYear());
    }
    @Test
    public void testSortByYearDescending(){
        List<Film> films = Arrays.asList(
                new Film("Film A", Status.PLANNED, Genre.ACTION, 4.5, 100, 2021),
                new Film("Film B", Status.WATCHED, Genre.COMEDY, 4.2,200,2019),
                new Film("Film C", Status.DROPPED, Genre.DRAMA, 2.1, 150, 2023)
        );
        SortStrategy sortStrategy = new SortByYear(false);
        List<Film> sortedFilms = sortStrategy.sort(films);
        assertEquals(2023, sortedFilms.get(0).getYear());
        assertEquals(2021, sortedFilms.get(1).getYear());
        assertEquals(2019, sortedFilms.get(2).getYear());
    }
}
