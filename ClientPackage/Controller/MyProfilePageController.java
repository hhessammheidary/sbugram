package ClientPackage.Controller;

import ClientPackage.Model.API;
import ClientPackage.Model.Main;
import ClientPackage.Model.PageLoader;
import Commen.Post;
import Commen.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class MyProfilePageController {
    public ImageView profileImage;
    public Label usernameLabel;
    public Label followerNumberLabel;
    public Label followingNumberLabel;
    public Label firstnameLabel;
    public Label lastnameLabel;
    public Button editProfileButton;
    public Button deleteAccountButton;
    public Button timeLineButton;
    public Button addPostButton;
    public Button searchPageButton;
    public Button profileButton;
    public Button logoutButton;

    public ListView<Post> postList;
    public static ArrayList<Post> posts=new ArrayList<>(Main.getUser().getPosts());

    public void initialize() throws IOException {
        loadPosts();
        User user=Main.getUser();
        if(user.getProfileImage()!=null){
            profileImage.setImage(new Image(new ByteArrayInputStream(user.getProfileImage())));
        }
        usernameLabel.setText(user.getUsername());
        followerNumberLabel.setText(Integer.toString(user.getFollowers().size()));
        followingNumberLabel.setText(Integer.toString(user.getFollowing().size()));
        firstnameLabel.setText(user.getName());
        lastnameLabel.setText(user.getLastName());
        postList.setItems(FXCollections.observableArrayList(posts));
        postList.setCellFactory(postList -> new PostItem());
    }

    public void editProfile(ActionEvent actionEvent) {

    }

    public void deleteAccount(ActionEvent actionEvent) {
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION , "are you sure to delete your account?!!");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isEmpty() || result.get() == ButtonType.OK){
            API.deleteAccount(Main.getUser().getUsername());
        }
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("Login");
    }

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

    public void loadPosts(){
        posts = API.getUserPosts(Main.getUser().getUsername());
    }
}
