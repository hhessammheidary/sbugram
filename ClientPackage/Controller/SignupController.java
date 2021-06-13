package ClientPackage.Controller;

import ClientPackage.Model.API;
import ClientPackage.Model.Main;
import ClientPackage.Model.PageLoader;
import Commen.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupController {
    @FXML
    public TextField lastnameField;
    public TextField usernameField;
    public TextField nameField;
    public PasswordField passwordField;
    public TextField forgotPasswordField;
    public TextField phoneNumberField;
    public Button signinButton;
    public Button backToLoginButton;
    public Label usernameWarning;
    public Label passwordWarning;
    public ImageView profileImg;
    public Button addProfileButton;
    public CheckBox showPassword;
    public TextField passwordVisible;
    public String profilePath;

    public void backToLoginPage(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("Login");
    }

    public void goToTimeLinePage(ActionEvent actionEvent) throws IOException {
        String username=usernameField.getText();
        String password;
        if(passwordField.isVisible()){
            password=passwordField.getText();
        }
        else {
            password=passwordVisible.getText();
        }
        if(!validPassword(password)){
            passwordWarning.setVisible(true);
        }
        else {
            passwordWarning.setVisible(false);
        }
        if(!validUsername(username)){
            usernameWarning.setVisible(true);
        }
        else {
            usernameWarning.setVisible(false);
        }
        if(validPassword(password) && !validUsername(username)){
            User user=new User(username);
            user.setPassword(password);
            if(!nameField.getText().isEmpty()) {
                user.setName(nameField.getText());
            }
            if(!phoneNumberField.getText().isEmpty()) {
                user.setPhoneNumber(phoneNumberField.getText());
            }
            if(lastnameField.getText().isEmpty()) {
                user.setLastName(lastnameField.getText());
            }
            if(!profilePath.isEmpty()){
                user.setProfileImage(profilePath);
            }
            Main.setUser(user);
            API.signUp(user);
            new PageLoader().load("TimeLine");
        }
    }

    private static final String passwordRegex="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    private static final Pattern pattern = Pattern.compile(passwordRegex);
    public boolean validPassword(String password){
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean validUsername(String username){
        return API.isUserNameExists(username);
    }

    public void showingPassword(ActionEvent actionEvent) {
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

    final FileChooser fileChooser=new FileChooser();
    public void addProfile(ActionEvent actionEvent) {
        Stage stage=new Stage();
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("select profile");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG" , "*.png") ,
                new FileChooser.ExtensionFilter("JPG" , "*.jpg"));
        File file = fileChooser.showOpenDialog(stage);
        Image image=new Image(file.toURI().toString());
        profilePath=file.toURI().toString();
        profileImg.setImage(image);
    }

}
