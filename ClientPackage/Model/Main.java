package ClientPackage.Model;

import Commen.Post;
import Commen.User;
import javafx.application.Application;

import javafx.stage.Stage;


//--module-path "C:\Program Files\javafx-sdk-12.0.1\lib" --add-modules javafx.controls,javafx.fxml
public class Main extends Application {
    public static User user;
    public static User otherUsernameToSearch;
    public static Post commentPost;
    @Override
    public void start(Stage primaryStage) throws Exception{
        PageLoader.initStage(primaryStage);
        new PageLoader().load("Login");
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Main.user = user;
    }

    public static User getOtherUsernameToSearch() {
        return otherUsernameToSearch;
    }

    public static void setOtherUsernameToSearch(User otherUsernameToSearch) {
        Main.otherUsernameToSearch = otherUsernameToSearch;
    }

    public static Post getCommentPost() {
        return commentPost;
    }

    public static void setCommentPost(Post commentPost) {
        Main.commentPost = commentPost;
    }

    public static void loadUser(){
        String username=user.getUsername();
        setUser(API.loadUser(username));
    }
}
