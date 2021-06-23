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
    public TextField passwordVisible;
    public Label userNotFoundLabel;
    public CheckBox showPassword;
    public Button signUpButton;
    public Button forgetPassword;

    boolean isServerUp=ClientToServer.connectToServer();

    public void login(ActionEvent actionEvent) throws IOException {
        String username=usernameField.getText();
        String password;
        if(passwordField.isVisible()){
            password = passwordField.getText();
        }
        else {
            password = passwordVisible.getText();
        }

        if(!validUsername(username)){
            userNotFoundLabel.setVisible(true);
        }
        else {
            User user=API.login(username , password);
            if(user == null){
                wrongPasswordLabel.setVisible(true);
            }
            else{
                wrongPasswordLabel.setVisible(false);
                userNotFoundLabel.setVisible(false);
                Main.setUser(user);
                new PageLoader().load("TimeLine");
            }
        }
    }

    public void showPassword(ActionEvent actionEvent) {
        if(passwordField.isVisible()){
            passwordField.setVisible(false);
            passwordVisible.setVisible(true);
            passwordVisible.setText(passwordField.getText());
        }
        else {
            passwordField.setVisible(true);
            passwordVisible.setVisible(false);
            passwordField.setText(passwordVisible.getText());
        }
    }

    public void signup(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("Signup");
    }

    public void forgetPassword(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("ForgetPassword");
    }

    public boolean validUsername(String username){
        return API.isUserNameExists(username);
    }
}
