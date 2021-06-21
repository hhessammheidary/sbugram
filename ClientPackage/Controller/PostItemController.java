package ClientPackage.Controller;

import ClientPackage.Model.PageLoader;
import Commen.Post;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.nio.file.Paths;

public class PostItemController {
    public Post post;
    public AnchorPane anchorPane;
    public Label usernameLabel;
    public ImageView profileImage;
    public Label titleLabel;
    public Button likePostButton;
    public Button repostButton;
    public Button commentButton;

    public PostItemController(Post post) throws IOException {
        this.post=post;
        new PageLoader().load("PostItem" , this);
    }

    public AnchorPane init() {
        usernameLabel.setText(post.getWriter());
        titleLabel.setText(post.getTitle());
        return anchorPane;
    }

    public void likePost(ActionEvent actionEvent) {
    }

    public void repostPost(ActionEvent actionEvent) {
    }

    public void comment(ActionEvent actionEvent) {
    }
}
