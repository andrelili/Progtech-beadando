package com.example.sorozatok;

import com.example.sorozatok.model.Film;
import com.example.sorozatok.model.Genre;
import com.example.sorozatok.model.Status;
import com.example.sorozatok.repository.FilmRepository;
import com.example.sorozatok.service.MovieService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class MovieFormController {

    @FXML
    private TextField titleField;
    @FXML
    private TextField ratingField;

    @FXML
    private TextField yearField;

    @FXML
    private ComboBox<Status> statusComboBox;

    @FXML
    private ComboBox<Genre> genreComboBox;

    private Film filmToEdit;
    private boolean isEditMode = false;
    private final MovieService movieService = new MovieService(new FilmRepository());

    @FXML
    public void initialize() {
        statusComboBox.setItems(FXCollections.observableArrayList(Status.values()));
        genreComboBox.setItems(FXCollections.observableArrayList(Genre.values()));
    }

    public void setMovie(Film film) {
        this.filmToEdit = film;
        this.isEditMode = true;

        titleField.setText(film.getTitle());
        ratingField.setText(String.valueOf(film.getAverageRating()));
        yearField.setText(String.valueOf(film.getYear()));
        statusComboBox.setValue(film.getStatus());
        genreComboBox.setValue(film.getGenre());
    }


    @FXML
    private void onSave() {
        String title = titleField.getText();
        String ratingText = ratingField.getText();
        String yearText = yearField.getText();
        Status status = statusComboBox.getValue();
        Genre genre = genreComboBox.getValue();

        if (title.isEmpty() || ratingText.isEmpty() || yearText.isEmpty() || status == null || genre == null) {
            showAlert("Minden mező kitöltése kötelező!");
            return;
        }

        double rating;
        int year;
        try {
            rating = Double.parseDouble(ratingText);
        } catch (NumberFormatException e) {
            showAlert("Az értékelésnek számnak kell lennie!");
            return;
        }

        try {
            year = Integer.parseInt(yearText);
        } catch (NumberFormatException e) {
            showAlert("Az évnek számnak kell lennie!");
            return;
        }

        try {
            if (isEditMode) {
                movieService.updateFilm(filmToEdit, title, rating, year, status, genre);
            } else {
                movieService.saveFilm(title, rating, year, status, genre);
                MainController.addMovie(new Film(title, status, genre, rating, 1, year));
            }

            closeWindow();
        } catch (Exception e) {
            showAlert(e.getMessage());
        }
    }



    @FXML
    private void onCancel() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) titleField.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Hiba");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
