package ClientPackage.Controller;

import ClientPackage.Model.API;
import ClientPackage.Model.Main;
import ClientPackage.Model.PageLoader;
import Commen.Post;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class AddPostPageController {
    public TextField titleField;
    public TextArea descriptionField;
    public ImageView postImage;
    public Button timeLineButton;
    public Button addPostButton;
    public Button profileButton;
    public Button addPostPhotoButton;
    public Button publishButton;
    public Label titleError;
    public byte[] postImageByteArray;

    public void goToTimeLinePage(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("TimeLine");
    }

    public void goToAddPostPage(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("AddPostPage");
    }

    public void goToProfilePage(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("ProfilePage");
    }

    public void addPostPhoto(ActionEvent actionEvent) {
        Stage stage=new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("select profile");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG" , "*.png") ,
                new FileChooser.ExtensionFilter("JPG" , "*.jpg"));
        File file = fileChooser.showOpenDialog(stage);
        Image image=new Image(file.toURI().toString());
        postImage.setImage(image);
        byte[] imageToByteArray = new byte[0];
        try {
            imageToByteArray= Files.readAllBytes(file.toPath());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        postImageByteArray=imageToByteArray;
        postImage.setImage(image);
    }

    public void publishPost(ActionEvent actionEvent) throws IOException {
        Post post=new Post();
        if(titleField.getText().isEmpty()){
            titleError.setVisible(true);
        }
        else{
            post.setTitle(titleField.getText());
            titleError.setVisible(false);
        }
        post.setWriter(Main.getUser().getUsername());
        if(!descriptionField.getText().isEmpty()){
            post.setDescription(descriptionField.getText());
        }
        if(postImageByteArray!=null){
            post.setPostImageByteArray(postImageByteArray);
        }
        API.addPost(Main.getUser().getUsername() , post);
        new PageLoader().load("TimeLine");
    }
}
