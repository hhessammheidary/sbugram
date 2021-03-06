package ClientPackage.Controller;

import ClientPackage.Model.API;
import ClientPackage.Model.ClientToServer;
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
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupController {
    @FXML
    public TextField lastnameField;
    public TextField usernameField;
    public TextField nameField;
    public PasswordField passwordField;
    public TextField favFoodField;
    public TextField phoneNumberField;
    public Button backToLoginButton;
    public Label usernameWarning;
    public Label passwordWarning;
    public ImageView profileImg;
    public Button addProfileButton;
    public CheckBox showPassword;
    public TextField passwordVisible;
    public Button signUpButton;
    public byte[] profileImageByteArray;


    public void backToLoginPage(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("Login");
    }

    public void goToTimeLinePage(ActionEvent actionEvent) throws IOException {
        if (!ClientToServer.isConnected()){
            System.out.println("not connected");
        }

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
        if(validPassword(password) && validUsername(username)){
            User user=new User(username);
            user.changePassword(password);
            if(!nameField.getText().isEmpty()) {
                user.setName(nameField.getText());
            }
            if(!phoneNumberField.getText().isEmpty()) {
                user.setPhoneNumber(phoneNumberField.getText());
            }
            if(!lastnameField.getText().isEmpty()) {
                user.setLastName(lastnameField.getText());
            }
            if(!favFoodField.getText().isEmpty()){
                user.setFavFood(favFoodField.getText());
            }
            if(profileImageByteArray!=null){
                user.setProfileImage(profileImageByteArray);
            }

            Main.setUser(user);
            API.signUp(user);
            new PageLoader().load("Login");
        }
    }

    private static final String passwordRegex="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    private static final Pattern pattern = Pattern.compile(passwordRegex);
    public boolean validPassword(String password){
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean validUsername(String username){
        return !API.isUserNameExists(username);
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

    public void addProfile(ActionEvent actionEvent) {
        Stage stage=new Stage();
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("select profile");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG" , "*.png") ,
                new FileChooser.ExtensionFilter("JPG" , "*.jpg"));
        File file = fileChooser.showOpenDialog(stage);
        Image image=new Image(file.toURI().toString());
        byte[] imageToByteArray;
        try {
            imageToByteArray= Files.readAllBytes(file.toPath());
            profileImageByteArray=imageToByteArray;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        profileImg.setImage(image);
    }

}
