package com.example.sorozatok;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class MovieFormController {

    @FXML
    private TextField titleField;
    @FXML
    private TextField yearField;
    @FXML
    private ComboBox<String> genreComboBox;

    private Movie movieToEdit;
    private boolean isEditMode = false;

    @FXML
    public void initialize() {
        genreComboBox.getItems().addAll("Akció", "Dráma", "Vígjáték", "Sci-fi", "Animáció", "Horror");
    }

    public void setMovie(Movie movie) {
        this.movieToEdit = movie;
        this.isEditMode = true;

        titleField.setText(movie.getTitle());
        yearField.setText(String.valueOf(movie.getYear()));
        genreComboBox.setValue(movie.getGenre());
    }

    @FXML
    private void onSave() {
        String title = titleField.getText();
        String yearText = yearField.getText();
        String genre = genreComboBox.getValue();

        if (title.isEmpty() || yearText.isEmpty() || genre == null) {
            showAlert("Minden mező kitöltése kötelező!");
            return;
        }

        int year;
        try {
            year = Integer.parseInt(yearText);
        } catch (NumberFormatException e) {
            showAlert("Az évnek számnak kell lennie!");
            return;
        }

        if (isEditMode) {
            movieToEdit.setTitle(title);
            movieToEdit.setYear(year);
            movieToEdit.setGenre(genre);
        } else {
            Movie newMovie = new Movie(title, year, genre);
            MainController.addMovie(newMovie); // statikus metódus a listához adáshoz
        }

        closeWindow();
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
