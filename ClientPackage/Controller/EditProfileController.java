package ClientPackage.Controller;

import ClientPackage.Model.API;
import ClientPackage.Model.Main;
import ClientPackage.Model.PageLoader;
import Commen.User;
import javafx.event.ActionEvent;
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

import static ClientPackage.Model.API.changePassword;

public class EditProfileController {
    public Button timeLineButton;
    public Button addPostButton;
    public Button searchPageButton;
    public Button profileButton;
    public Button backToProfilePageButton;
    public Button doneButton;
    public TextField firstnameField;
    public TextField lastnameField;
    public TextField phonenumberField;
    public TextField passwordVisible;
    public PasswordField passwordField;
    public CheckBox showPassword;
    public Label passwordWarning;
    public ImageView profileImage;
    public Button changeProfileButton;
    public byte[] profileImageByteArray;
    public User user = Main.getUser();

    public void goToTimeLinePage(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("TimeLine");
    }

    public void goToAddPostPage(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("AddPostPage");
    }

    public void goToSearchPage(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("SearchPage");
    }

    public void goToProfilePage(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("MyProfilePage");
    }

    public void backToProfilePage(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("MyProfilePage");
    }

    public void done(ActionEvent actionEvent) throws IOException {
        String password;
        if(passwordField.isVisible()){
            password=passwordField.getText();
        }
        else {
            password = passwordVisible.getText();
        }
        if(!password.isEmpty()){
            if(!validPassword(password)){
                passwordWarning.setVisible(true);
            }
            else {
                passwordWarning.setVisible(false);
                API.changePassword(Main.getUser().getUsername() , password);
            }
        }
        if(!firstnameField.getText().isEmpty()){
            API.changeFirstname(Main.getUser().getUsername() , firstnameField.getText());
        }
        if(!lastnameField.getText().isEmpty()){
            API.changeLastname(Main.getUser().getUsername() , lastnameField.getText());
        }
        if(!phonenumberField.getText().isEmpty()){
            API.changePhoneNumber(Main.getUser().getUsername() , phonenumberField.getText());
        }
        new PageLoader().load("MyProfilePage");
    }

    public void showPass(ActionEvent actionEvent) {
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

    private static final String passwordRegex="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    private static final Pattern pattern = Pattern.compile(passwordRegex);
    public boolean validPassword(String password){
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public void changeProfile(ActionEvent actionEvent) {
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
        profileImage.setImage(image);
    }
}
