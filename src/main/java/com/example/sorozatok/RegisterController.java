package com.example.sorozatok;

import com.example.sorozatok.model.User;
import com.example.sorozatok.service.IUserService;
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

import javax.imageio.IIOException;
import java.io.IOException;


public class RegisterController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    private final IUserService userService = AppContext.getUserService();
    @FXML
    private void onRegister(){
        String username = usernameField.getText();
        String password = passwordField.getText();



        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Minden mezőt ki kell tölteni!");
            LoggerUtil.error("All fields must be filled!");
            return;
        }
        if (password.length() < 6) {
            showAlert("A jelszónak legalább 6 karakter hosszúnak kell lennie!");
            LoggerUtil.error("Password must be at least 6 characters!");
            return;
        }

        if(userService.register(username, password)){
            showAlert("Sikeres regisztráció!");
            LoggerUtil.info("Successfully registered!");
            onBack();
        } else {
            showAlert("Ez a felhasználónév már foglalt!");
            LoggerUtil.warning("THis username is already taken!" + username);
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Regisztráció");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void onBack(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) usernameField.getScene().getWindow();
            Scene scene = new Scene(root, 1024, 800);
            stage.setScene(scene);
            stage.setTitle("Bejelentkezés");
            stage.show();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

}
