package com.example.sorozatok;

import com.example.sorozatok.model.Film;
import com.example.sorozatok.model.Status;
import com.example.sorozatok.model.Genre;
import com.example.sorozatok.repository.FilmRepository;
import com.example.sorozatok.service.MovieManager;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MainController {
    @FXML
    private TableView<Film> movieTable;
    @FXML
    private TableColumn<Film, String> titleColumn;
    @FXML
    private TableColumn<Film, Integer> yearColumn;
    @FXML
    TableColumn<Film, Genre> genreColumn;
    @FXML
    private TableColumn<Film, Double> ratingColumn;
    @FXML
    private TableColumn<Film, Status> statusColumn;

    @FXML
    private TextField filterField;

    private final ObservableList<Film> movieList = FXCollections.observableArrayList();
    private static MainController instance;
    private final MovieManager movieManager = new MovieManager(new FilmRepository());

    public static void addMovie(Film film) {
        if (instance != null) {
            instance.movieList.add(film);
        }
    }

    @FXML
    public void initialize() {
        instance = this;

        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        yearColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getYear()).asObject());
        genreColumn.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
        ratingColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getAverageRating()).asObject());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        movieTable.setItems(movieList);
        loadFilmsFromDatabase();
    }

    private void loadFilmsFromDatabase() {
        List<Film> films = movieManager.loadFilms();
        movieList.setAll(films);
    }

    @FXML
    private void onAdd() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("movie-form.fxml"));
            Scene scene = new Scene(loader.load());

            MovieFormController controller = loader.getController();
            Stage stage = new Stage();
            stage.setTitle("Új film hozzáadása");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            movieTable.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onDelete() {
        Film selected = movieTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            movieList.remove(selected);
            movieManager.deleteFilm(selected.getId());
        }
    }

    @FXML
    private void onFilter() {
        String filter = filterField.getText();
        List<Film> filtered = movieManager.filterFilms(new ArrayList<>(movieList), filter);
        movieTable.setItems(FXCollections.observableArrayList(filtered));
    }

    @FXML
    private void onEdit() {
        Film selectedFilm = movieTable.getSelectionModel().getSelectedItem();
        if (selectedFilm == null) {
            showAlert("Nincs kiválasztva film a szerkesztéshez.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("movie-form.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 1024, 800);
            MovieFormController controller = loader.getController();
            controller.setMovie(selectedFilm);

            Stage stage = new Stage();
            stage.setTitle("Film szerkesztése");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            movieTable.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Figyelem");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
