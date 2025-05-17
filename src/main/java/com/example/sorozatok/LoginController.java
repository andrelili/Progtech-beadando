package com.example.sorozatok;

import com.example.sorozatok.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;
    private final UserService userService= new UserService();

    @FXML
    private void onLogin(){
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/sorozatok/main-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Főoldal");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); // ← ez megmutatja, mi a hiba
        }

    }
    @FXML
    private void onRegister(){
       try{
           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("register-view.fxml")));
           Parent root = fxmlLoader.load();
           Stage stage = (Stage) usernameField.getScene().getWindow();
           stage.setScene(new Scene(root));
           stage.setTitle("Regisztráció");
           stage.show();
       }catch(IOException e){
           e.printStackTrace();
       }
    }
}
