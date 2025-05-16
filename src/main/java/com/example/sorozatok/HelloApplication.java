package com.example.sorozatok;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("main-view.fxml")); //ha tesztelni akarod akkor a login-,main-, stb használd
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Főképernyő");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}