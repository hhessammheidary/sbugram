package ClientPackage.Controller;

import ClientPackage.Model.PageLoader;
import Commen.Post;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class TimeLineController {
    public Button refreshButton;
    public Button timeLineButton;
    public Button addPostButton;
    public Button profileButton;

    public static ListView<Post> postlist=new ListView<>();
    public static ArrayList<Post> posts=new ArrayList<>();
    public Post currentPost=new Post();

    public void goToTimeLinePage(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("TimeLine");
    }

    public void goToAddPostPage(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("AddPostPage");
    }

    public void goToProfilePage(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("ProfilePage");
    }

    public void initialize(){
        postlist.setItems(FXCollections.observableArrayList(posts));
        postlist.setCellFactory(postlist -> new PostItem());
    }

    public void refreshPosts(ActionEvent actionEvent) {
    }
}
