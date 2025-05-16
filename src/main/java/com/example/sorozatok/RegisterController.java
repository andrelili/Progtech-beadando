package com.example.sorozatok;

import com.example.sorozatok.model.User;
import com.example.sorozatok.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    private final UserService userService = new UserService();
    @FXML
    private void onRegister(){
        String username = usernameField.getText();
        String password = passwordField.getText();

        if(userService.register(username,password)){
            System.out.println("Sikeres regisztráció!");
        }else{
            System.out.println("Ez a felhasználónév már foglalt!");
        }
    }
    @FXML
    private void onBack(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Bejelentkezés");
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
