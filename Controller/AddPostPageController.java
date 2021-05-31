package Controller;

import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class AddPostPageController {
    public TextField titleField;
    public TextArea descriptionField;
    public ImageView postImage;
    public Button timeLineButton;
    public Button addPostButton;
    public Button profileButton;
    public Button addPostPhotoButton;
    public Button publishButton;
    final FileChooser fileChooser=new FileChooser();
    public Button directMessageButton;

    public void goToTimeLinePage(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("TimeLine");
    }

    public void goToAddPostPage(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("AddPostPage");
    }

    public void goToProfilePage(ActionEvent actionEvent) {
    }

    public void goToDirectMessage(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("x");
    }

    public void addPostPhoto(ActionEvent actionEvent) {
        Stage stage=new Stage();
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("select profile");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG" , "*.png") ,
                new FileChooser.ExtensionFilter("JPG" , "*.jpg"));
        File file = fileChooser.showOpenDialog(stage);
        Image image=new Image(file.toURI().toString());
        postImage.setImage(image);
    }

    public void publishPost(ActionEvent actionEvent) {
    }
}
