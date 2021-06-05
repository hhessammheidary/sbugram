package Controller;

import Model.PageLoader;
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
    public Button signInButton;
    public Button forgetPassword;


    public void login(ActionEvent actionEvent) throws IOException {
        String username=usernameField.getText();
        String password;
        boolean correctId=false;
        boolean correctPass=false;
        if(passwordField.isVisible()){
            password=passwordField.getText();
        }
        else {
            password=passwordVisible.getText();
        }
        if(!username.equals("hessam")){
            userNotFoundLabel.setVisible(true);
        }
        else {
            userNotFoundLabel.setVisible(false);
            correctId=true;
        }
        if(!password.equals("1234")){
            wrongPasswordLabel.setVisible(true);
        }
        else {
            wrongPasswordLabel.setVisible(false);
            correctPass=true;
        }
        if(correctId && correctPass){
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

    public void signIn(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("Signin");
    }

    public void forgetPassword(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("ForgetPassword");
    }
}
