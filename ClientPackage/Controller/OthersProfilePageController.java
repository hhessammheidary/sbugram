package ClientPackage.Controller;

import ClientPackage.Model.API;
import ClientPackage.Model.Main;
import ClientPackage.Model.PageLoader;
import Commen.Post;
import Commen.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class OthersProfilePageController {
    public ImageView profileImage;
    public Label usernameLabel;
    public Label followerNumberLabel;
    public Label followingNumberLabel;
    public Label firstnameLabel;
    public Label lastnameLabel;
    public Button timeLineButton;
    public Button addPostButton;
    public Button searchPageButton;
    public Button profileButton;
    public Button followOrUnfollowButton;
    public ListView<Post> postList;

    User user;

    public static ArrayList<Post> posts=new ArrayList<>(Main.getUser().getPosts());

    public void initialize() throws IOException {
        Map<String , Object> received = API.getUser(Main.getUser().getUsername() , Main.otherUsernameToSearch.getUsername());
        user=(User) received.get("answer");
        loadPosts();
        boolean followOrUnfollow=(Boolean) received.get("followOrUnFollow");
        if (followOrUnfollow) {
            followOrUnfollowButton.setText("unfollow");
        }
        else {
            followOrUnfollowButton.setText("follow");
        }
        if(user.getProfileImage()!=null){
            profileImage.setImage(new Image(new ByteArrayInputStream(user.getProfileImage())));
        }
        usernameLabel.setText(user.getUsername());
        followerNumberLabel.setText(String.valueOf(API.getFollowerNumber(Main.getOtherUsernameToSearch().getUsername())));
        followingNumberLabel.setText(String.valueOf(API.getFollowingNumber(Main.getOtherUsernameToSearch().getUsername())));
        firstnameLabel.setText(user.getName());
        lastnameLabel.setText(user.getLastName());
        postList.setItems(FXCollections.observableArrayList(posts));
        postList.setCellFactory(postList -> new PostItem());
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
        posts = API.getUserPosts(user.getUsername());
    }

    public void followOrUnfollowUser(ActionEvent actionEvent) throws IOException {
        if(followOrUnfollowButton.getText().equals("follow")){
            API.followUser(Main.getUser().getUsername() , Main.getOtherUsernameToSearch().getUsername());
            followOrUnfollowButton.setText("unfollow");
        }
        else {
            API.UnfollowUser(Main.getUser().getUsername() , Main.getOtherUsernameToSearch().getUsername());
            followerNumberLabel.setText("follow");
        }
        initialize();
    }
}
