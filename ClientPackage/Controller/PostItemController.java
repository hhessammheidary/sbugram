package ClientPackage.Controller;

import ClientPackage.Model.PageLoader;
import Commen.Post;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PostItemController {
    public Post post;
    public AnchorPane anchorPane;
    public Label usernameLabel;
    public Label titleLabel;
    public Label postDate;
    public TextArea description;
    public ImageView postImage;
    public ImageView profileImage;
    public ImageView repostIcon;
    public Button likePostButton;
    public Button repostButton;
    public Button commentButton;

    public PostItemController(Post post) throws IOException {
        new PageLoader().load("PostItem" , this);
        this.post=post;
    }

    public AnchorPane init() {
        usernameLabel.setText(post.getWriter());
        titleLabel.setText(post.getTitle());
        description.setText(post.getDescription());
        if(post.getPostImageByteArray()!=null){
            postImage.setImage(new Image(new ByteArrayInputStream(post.getPostImageByteArray())));
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh");
        String dateAsString = dateFormat.format(post.getDateWithTime());
        postDate.setText(dateAsString);
        if(post.isRepost()){
            repostIcon.setVisible(true);
        }
        else {
            repostIcon.setVisible(false);
        }
        return anchorPane;
    }

    public void likePost(ActionEvent actionEvent) {
    }

    public void repostPost(ActionEvent actionEvent) {
    }

    public void comment(ActionEvent actionEvent) {
    }
}
