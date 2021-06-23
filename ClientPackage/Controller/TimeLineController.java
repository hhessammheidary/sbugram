package ClientPackage.Controller;

import ClientPackage.Model.API;
import ClientPackage.Model.Main;
import ClientPackage.Model.PageLoader;
import Commen.Post;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;

public class TimeLineController {
    public Button refreshButton;
    public Button timeLineButton;
    public Button addPostButton;
    public Button profileButton;
    public ListView<Post> postList;
    public Button searchPageButton;

    public static ArrayList<Post> posts=new ArrayList<>();

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

    public void initialize() throws IOException {
        loadPosts();
        postList.setItems(FXCollections.observableArrayList(posts));
        postList.setCellFactory(postList -> new PostItem());
    }

    public void refreshPosts(ActionEvent actionEvent) throws IOException {
        loadPosts();
    }

    public void loadPosts() throws IOException {
        posts=API.timeLine(Main.getUser().getUsername());
    }
}
