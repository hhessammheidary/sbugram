package ClientPackage.Controller;

import ClientPackage.Model.API;
import ClientPackage.Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgetPasswordController {
    public PasswordField passwordField;
    public TextField passwordVisible;
    public TextField usernameField;
    public TextField favFoodField;
    public Button checkButton;
    public Label userNotFoundLabel;
    public Label wrongAnswerFavFoodLabel;
    public Label enterNewPass;
    public Button doneButton;
    public CheckBox showPassword;
    public Label passwordWarning;
    public Button backButton;
    
    public void checkAnswer(ActionEvent actionEvent) throws IOException {
        String username=usernameField.getText();
        String answer=favFoodField.getText();
        if(!validUsername(username)){
            userNotFoundLabel.setVisible(true);
        }
        else{
            userNotFoundLabel.setVisible(false);
        }
        if(!validAnswer(username , answer)){
            wrongAnswerFavFoodLabel.setVisible(true);
        }
        else {
            wrongAnswerFavFoodLabel.setVisible(true);
        }
        if(validAnswer(username , answer) && validUsername(username)){
            wrongAnswerFavFoodLabel.setVisible(false);
            userNotFoundLabel.setVisible(false);
            passwordField.setVisible(true);
            enterNewPass.setVisible(true);
            showPassword.setVisible(true);
            doneButton.setVisible(true);
        }
    }

    public void doneChangePass(ActionEvent actionEvent) throws IOException {
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
            changePassword(usernameField.getText() , password);
            new PageLoader().load("Login");
        }
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

    public void backToLogin(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("Login");
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

    public boolean validAnswer(String username , String answer){
        return API.forgetPassword(username , answer);
    }

    public void changePassword(String username , String newPassword){
        API.changePassword(username , newPassword);
    }
}
