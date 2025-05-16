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

        if(userService.login(username,password)){
            System.out.println("Sikeres bejelentkezés!");
        }
        else{
            System.out.println("Hibás felhasználónév vagy jelszó.");
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
