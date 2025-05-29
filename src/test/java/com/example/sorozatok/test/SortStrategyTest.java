package com.example.sorozatok.test;

import com.example.sorozatok.model.Film;
import com.example.sorozatok.model.Genre;
import com.example.sorozatok.model.Status;
import com.example.sorozatok.strategy.SortByRating;
import com.example.sorozatok.strategy.SortByTitle;
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
    @Test
    public void testSortByTitleDescending(){
        List<Film> films = Arrays.asList(
                new Film("Film A", Status.PLANNED, Genre.ACTION, 4.5, 100, 2021),
                new Film("Film B", Status.WATCHED, Genre.COMEDY, 4.2,200,2019),
                new Film("Film C", Status.DROPPED, Genre.DRAMA, 2.1, 150, 2023)
        );
        SortStrategy sortStrategy = new SortByTitle(false);
        List<Film> sortedFilm = sortStrategy.sort(films);
        assertEquals("Film C", sortedFilm.get(0).getTitle());
        assertEquals("Film B", sortedFilm.get(1).getTitle());
        assertEquals("Film A", sortedFilm.get(2).getTitle());
    }
    @Test
    public void testSortByRatingAscending(){
        List<Film> films = Arrays.asList(
                new Film("Film A", Status.PLANNED, Genre.ACTION, 4.5, 100, 2021),
                new Film("Film B", Status.WATCHED, Genre.COMEDY, 4.2,200,2019),
                new Film("Film C", Status.DROPPED, Genre.DRAMA, 2.1, 150, 2023)
        );
        SortStrategy sortStrategy = new SortByRating(false);
        List<Film> sortedFilm = sortStrategy.sort(films);
        assertEquals(4.5, sortedFilm.get(0).getAverageRating());
        assertEquals(4.2, sortedFilm.get(1).getAverageRating());
        assertEquals(2.1, sortedFilm.get(2).getAverageRating());
    }
    @Test
    public void testSortByRatingDescending(){
        List<Film> films = Arrays.asList(
                new Film("Film A", Status.PLANNED, Genre.ACTION, 4.5, 100, 2021),
                new Film("Film B", Status.WATCHED, Genre.COMEDY, 4.2,200,2019),
                new Film("Film C", Status.DROPPED, Genre.DRAMA, 2.1, 150, 2023)
        );
        SortStrategy sortStrategy = new SortByRating(true);
        List<Film> sortedFilm = sortStrategy.sort(films);
        assertEquals(2.1, sortedFilm.get(0).getAverageRating());
        assertEquals(4.2, sortedFilm.get(1).getAverageRating());
        assertEquals(4.5, sortedFilm.get(2).getAverageRating());
    }


}
