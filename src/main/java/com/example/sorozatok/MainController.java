package com.example.sorozatok;

import com.example.sorozatok.model.Film;
import com.example.sorozatok.model.Status;
import com.example.sorozatok.model.Genre;
import com.example.sorozatok.service.MovieManager;
import com.example.sorozatok.strategy.SortByRating;
import com.example.sorozatok.strategy.SortByTitle;
import com.example.sorozatok.strategy.SortByYear;
import com.example.sorozatok.strategy.SortStrategy;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import com.example.sorozatok.repository.FilmRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;
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
    private TableColumn<Film, Genre> genreColumn;
    @FXML
    private TableColumn<Film, Double> ratingColumn;
    @FXML
    private TableColumn<Film, Status> statusColumn;

    @FXML
    private TextField filterField;

    private final ObservableList<Film> movieList = FXCollections.observableArrayList();
    private static MainController instance;
    private MovieManager movieManager;
    boolean sortByYearAscending = true;
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
        movieManager = new MovieManager(new FilmRepository());
        movieList.setAll(movieManager.loadFilms());
        movieTable.setItems(movieList);
        loadFilmsFromDatabase();
    }

    private void loadFilmsFromDatabase() {
        FilmRepository repo = new FilmRepository();
        try {
            List<Film> films = repo.findAll();
            movieList.setAll(films);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            try {
                new FilmRepository().delete(selected.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void onFilter() {
        String filter = filterField.getText().toLowerCase();

        if (filter.isEmpty()) {
            movieTable.setItems(movieList);
            return;
        }

        ObservableList<Film> filteredList = FXCollections.observableArrayList();
        for (Film film : movieList) {
            if (film.getTitle().toLowerCase().contains(filter)) {
                filteredList.add(film);
            }
        }

        movieTable.setItems(filteredList);
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
    @FXML
    private void onSortByYear() {
        SortStrategy strategy = new SortByYear(sortByYearAscending);
        List<Film> sorted = movieManager.sort(movieList, strategy);
        movieList.setAll(sorted);
        sortByYearAscending = !sortByYearAscending;
        movieTable.refresh();
    }


    @FXML
    private void onSortByTitle() {
        SortStrategy strategy = new SortByTitle(sortByYearAscending);
        List<Film> sorted = movieManager.sort(movieList,strategy);
        movieList.setAll(sorted);
        sortByYearAscending = !sortByYearAscending;
        movieTable.refresh();
    }

    @FXML
    private void onSortByAvarageRating() {
        SortStrategy strategy = new SortByRating(sortByYearAscending);
        List<Film> sorted = movieManager.sort(movieList,strategy);
        movieList.setAll(sorted);
        sortByYearAscending = !sortByYearAscending;
        movieTable.refresh();
    }



}
