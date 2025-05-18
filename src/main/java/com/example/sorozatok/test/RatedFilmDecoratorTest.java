package com.example.sorozatok.test;

import com.example.sorozatok.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RatedFilmDecoratorTest {
    @Test
    void testUpdateFilmRatingCorrectlyUpdatesAverageAndCount() {
        Film originalFilm = new Film("Test Movie", Status.WATCHED, Genre.ACTION, 8.0, 2, 2020);
        double newUserRating = 10.0;
        RatedFilmDecorator decorator = new RatedFilmDecorator((FilmComponent) originalFilm, newUserRating);

        decorator.updateFilmRating();

        // Assert
        assertEquals(3, originalFilm.getRatingCount());
        assertEquals((8.0 * 2 + 10.0) / 3, originalFilm.getAverageRating(), 0.001);
    }

    @Test
    void testUpdateFilmRatingThrowsExceptionForNonFilmComponent() {
        FilmComponent fakeComponent = new FilmComponent() {
            @Override public int getId() { return 0; }
            @Override public String getTitle() { return "Fake"; }
            @Override public Status getStatus() { return Status.WATCHED; }
            @Override public Genre getGenre() { return Genre.DRAMA; }
            @Override public double getAverageRating() { return 0; }
            @Override public int getRatingCount() { return 0; }
        };

        RatedFilmDecorator decorator = new RatedFilmDecorator(fakeComponent, 7.0);

        assertThrows(IllegalStateException.class, decorator::updateFilmRating);
    }
}
