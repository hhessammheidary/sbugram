package ClientPackage.Controller;

import ClientPackage.Model.API;
import ClientPackage.Model.ClientToServer;
import ClientPackage.Model.Main;
import ClientPackage.Model.PageLoader;
import Commen.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.IOException;

public class LoginController {
    @FXML
    public Button loginButton;
    public TextField usernameField;
    public PasswordField passwordField;
    public Label wrongPasswordLabel;
    public Label userNotFoundLabel;
    public TextField passwordVisible;
    public CheckBox showPassword;
    public Button signUpButton;
    public Button forgetPassword;

    public void login(ActionEvent actionEvent) throws IOException {
        String username=usernameField.getText();
        String password = null;
        boolean correctId=false;
        boolean correctPass=false;

        if (!ClientToServer.isConnected()){
            System.out.println("not connected");;
            return;
        }
        if(passwordField.isVisible()){
            password=passwordField.getText();
        }
        else {
            password=passwordVisible.getText();
        }
        User user= API.login(username , password);
        if(username.isEmpty() || password.isEmpty() || user==null){
            userNotFoundLabel.setVisible(true);
            wrongPasswordLabel.setVisible(true);
        }
        else {
            userNotFoundLabel.setVisible(false);
            wrongPasswordLabel.setVisible(false);
            correctId=true;
            correctPass=true;
        }
        if(correctId && correctPass){
            Main.setUser(user);
            new PageLoader().load("TimeLine");
        }
    }

    public void showPassword(ActionEvent actionEvent) {
        if(!passwordVisible.isVisible()){
            passwordField.setVisible(false);
            passwordVisible.setVisible(true);
            passwordVisible.setText(passwordField.getText());
        }
        else {
            passwordVisible.setVisible(false);
            passwordField.setVisible(true);
            passwordField.setText(passwordVisible.getText());
        }
    }

    public void signup(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("Signup");
    }

    public void forgetPassword(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("ForgetPassword");
    }
}
