package com.example.sorozatok;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.collections.FXCollections;

import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class MainController {
    @FXML
    private TableView<Movie> movieTable;
    @FXML
    private TableColumn<Movie, String> titleColumn;
    @FXML
    private TableColumn<Movie, Integer> yearColumn;
    @FXML
    private TableColumn<Movie, String> genreColumn;
    @FXML
    private TextField filterField;

    private final ObservableList<Movie> movieList=FXCollections.observableArrayList();
    private static MainController instance;

    public static void addMovie(Movie movie) {
        if (instance != null) {
            instance.movieList.add(movie);
        }
    }

    @FXML
    public void initialize() {
        instance = this;

        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        yearColumn.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asObject());
        genreColumn.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
        movieTable.setItems(movieList);
    }

    @FXML
    private void onAdd(){
        System.out.println("Film hozzáadása");
    }
    @FXML
    private void onDelete(){
        Movie selected = movieTable.getSelectionModel().getSelectedItem();
        if(selected != null){
            movieList.remove(selected);
        }
    }
    @FXML
    private void onFilter(){
        String filter = filterField.getText().toLowerCase();
    }
    @FXML
    private void onEdit(){
        Movie  selected = movieTable.getSelectionModel().getSelectedItem();
        if(selected == null){
            System.out.println("Nincs kijelölt film.");
            return;
        }
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("movie-form.fxml"));
            Parent root = loader.load();
            MovieFormController controller = loader.getController();
            controller.setMovie(selected);
            Stage stage = new Stage();
            stage.setTitle("Film szerkesztése");
            stage.setScene(new Scene(root));
            stage.showAndWait();
            movieTable.refresh();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
