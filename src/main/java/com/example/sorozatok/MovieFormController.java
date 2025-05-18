package com.example.sorozatok;

import com.example.sorozatok.model.Film;
import com.example.sorozatok.model.Status;
import com.example.sorozatok.repository.FilmRepository;
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
    private ComboBox<Status> genreComboBox;

    private Film filmToEdit;
    private boolean isEditMode = false;

    @FXML
    public void initialize() {
        genreComboBox.setItems(FXCollections.observableArrayList(Status.values()));

    }

    public void setMovie(Film film) {
        this.filmToEdit = film;
        this.isEditMode = true;

        titleField.setText(film.getTitle());
        ratingField.setText(String.valueOf(film.getAverageRating()));
        genreComboBox.setValue(film.getStatus());
    }

    @FXML
    private void onSave() {
        String title = titleField.getText();
        String ratingText = ratingField.getText();
        Status status = genreComboBox.getValue();



        if (title.isEmpty() || ratingText.isEmpty() || status == null) {
            showAlert("Minden mező kitöltése kötelező!");
            return;
        }

        double rating;
        try {
            rating = Double.parseDouble(ratingText);
        } catch (NumberFormatException e) {
            showAlert("Az értékelésnek számnak kell lennie!");
            return;
        }

        try {
            FilmRepository repo = new FilmRepository();

            if (isEditMode) {
                filmToEdit.setTitle(title);
                filmToEdit.setStatus(status);
                filmToEdit.setAverageRating(rating);
                repo.update(filmToEdit);
            } else {
                Film newFilm = new Film(title, status, rating, 1);
                repo.save(newFilm);
                MainController.addMovie(newFilm);
            }

            closeWindow();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Hiba történt mentés közben.");
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
