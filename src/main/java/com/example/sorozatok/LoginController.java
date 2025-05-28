package com.example.sorozatok;

import com.example.sorozatok.service.UserService;
import com.example.sorozatok.repository.UserRepository;
import com.example.sorozatok.utils.LoggerUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;
    private final UserService userService = AppContext.getUserService();


    @FXML
    private void onLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (userService.login(username, password)) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/sorozatok/main-view.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = (Stage) usernameField.getScene().getWindow();
                Scene scene = new Scene(root, 1024, 800);
                stage.setScene(scene);
                stage.setTitle("Főoldal");
                stage.show();
                LoggerUtil.info("Successfully logged in user \n\t - " + username);
            } catch (IOException e) {
                LoggerUtil.error("Error logging in user... \n\t - " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Bejelentkezés sikertelen");
            alert.setHeaderText(null);
            alert.setContentText("Hibás felhasználónév vagy jelszó!");
            alert.showAndWait();

            LoggerUtil.warning("Username or password is incorrect");
        }
    }

    @FXML
    private void onRegister(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) usernameField.getScene().getWindow();
            Scene scene = new Scene(root, 1024, 800); // csak egyszer hozzuk létre
            stage.setScene(scene);
            stage.setTitle("Regisztráció");
            stage.show();

            LoggerUtil.info("Successfully registered user \n\t - " + usernameField.getText());
        } catch(IOException e){
            LoggerUtil.error("Error registering user... \n\t - " + e.getMessage());
            e.printStackTrace();
        }
    }

}
