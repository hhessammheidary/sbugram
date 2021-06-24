package ClientPackage.Controller;

import ClientPackage.Model.API;
import ClientPackage.Model.Main;
import ClientPackage.Model.PageLoader;
import Commen.Post;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class PostItemController {
    public Post post;
    public AnchorPane anchorPane;
    public Label usernameLabel;
    public Label titleLabel;
    public Label postDate;
    public Label likeNumberLabel;
    public Label repostNumberLabel;
    public Label commentNumberLabel;
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
        likeNumberLabel.setText(Integer.toString(post.likeNum()));
        if(post.getPostImageByteArray()!=null){
            postImage.setImage(new Image(new ByteArrayInputStream(post.getPostImageByteArray())));
        }
        LocalDate dateTime = post.getDate();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateAsString = dateFormat.format(dateTime);
        postDate.setText(dateAsString);
        if(post.getIsRepost()){
            repostIcon.setVisible(true);
        }
        else {
            repostIcon.setVisible(false);
        }
        return anchorPane;
    }

    public void likePost(ActionEvent actionEvent) {
        likeNumberLabel.setText(API.like(post.getWriter() , post).toString());
    }

    public void repostPost(ActionEvent actionEvent) {
    }

    public void comment(ActionEvent actionEvent) {
    }
}
