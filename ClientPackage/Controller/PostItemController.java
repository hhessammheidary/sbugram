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
    public Button showProfileButton;

    public PostItemController(Post post) throws IOException {
        new PageLoader().load("PostItem" , this);
        this.post=post;
    }

    public AnchorPane init() {
        usernameLabel.setText(post.getWriter());
        titleLabel.setText(post.getTitle());
        description.setText(post.getDescription());
        likeNumberLabel.setText(String.valueOf(API.getLikeNumber(post.getWriter() , post)));
        repostNumberLabel.setText(String.valueOf(API.getRepostNumber(post.getWriter() , post)));
        commentNumberLabel.setText(String.valueOf(API.getCommentNumber(post.getWriter() , post)));
        if(post.getPostImageByteArray()!=null){
            postImage.setImage(new Image(new ByteArrayInputStream(post.getPostImageByteArray())));
        }
        if(API.getUserProfile(post.getWriter())!=null){
            profileImage.setImage(new Image(new ByteArrayInputStream(API.getUserProfile(post.getWriter()))));
        }

        /*LocalDate dateTime = post.getDate();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateAsString = dateFormat.format(dateTime);
        postDate.setText(dateAsString);*/
        if(post.getIsRepost()){
            repostIcon.setVisible(true);
        }
        else {
            repostIcon.setVisible(false);
        }
        return anchorPane;
    }

    public void likePost(ActionEvent actionEvent) {
        API.like(Main.getUser().getUsername() , post);
        likeNumberLabel.setText(String.valueOf(API.getLikeNumber(post.getWriter() , post)));
    }

    public void repostPost(ActionEvent actionEvent) {
        API.repost(Main.getUser().getUsername() , post);
        repostNumberLabel.setText(String.valueOf(API.getRepostNumber(post.getWriter() , post)));
    }

    public void comment(ActionEvent actionEvent) throws IOException {
        Main.setCommentPost(post);
        new PageLoader().load("CommentPage");
    }

    public void showProfile(ActionEvent actionEvent) throws IOException{
        Main.setOtherUsernameToSearch(API.getUserWithUsername(post.getWriter()));
        new PageLoader().load("OthersProfilePage");
    }
}
