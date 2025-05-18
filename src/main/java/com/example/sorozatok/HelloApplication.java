package com.example.sorozatok;

import com.example.sorozatok.model.Film;
import com.example.sorozatok.model.Status;
import com.example.sorozatok.repository.FilmRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("login-view.fxml")); //ha tesztelni akarod akkor a login-,main-, stb használd
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Bejelentkezés"); //Használjátok:felhaszn: admin, jelszó: 1234, vagy teszt, jelszó: jelszo
        stage.setScene(scene); //de a regisztráció is működik.
        stage.show();
    }

    public static void main(String[] args) {
        //Adatbázis tesztelése:
        //try {
        //    FilmRepository repo   = new FilmRepository();

        //    Film film = new Film(0, "The Matrix", Status.WATCHED, 4.7, 150);
        //    repo.save(film);

        //     System.out.println("Mentve film id: " + film.getId());

        //     repo.findAll().forEach(f -> System.out.println(f.getTitle()));

        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }
        launch(args);
    }
}