package com.example.sorozatok;

import com.example.sorozatok.model.Film;
import com.example.sorozatok.model.Status;
import com.example.sorozatok.repository.FilmRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public class MainController {
    @FXML
    private TableView<Film> movieTable;
    @FXML
    private TableColumn<Film, String> titleColumn;
    @FXML
    private TableColumn<Film, Status> genreColumn;
    @FXML
    private TextField filterField;

    private final ObservableList<Film> movieList = FXCollections.observableArrayList();
    private static MainController instance;

    public static void addMovie(Film film) {
        if (instance != null) {
            instance.movieList.add(film);
        }
    }

    @FXML
    public void initialize() {
        instance = this;

        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        genreColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("movie-view.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("movie-view.fxml"));
            Scene scene = new Scene(loader.load());

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
