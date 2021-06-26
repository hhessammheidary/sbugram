package ClientPackage.Controller;

import Commen.Comment;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javafx.scene.control.TextArea;

import java.io.ByteArrayInputStream;

public class CommentItemController{
    public Comment comment;
    public AnchorPane anchorPane;
    public ImageView profileImage;
    public Label usernameLabel;
    public TextArea commentTextArea;

    public CommentItemController(Comment comment){
        this.comment=comment;
    }

    public AnchorPane init() {
        usernameLabel.setText(comment.getUserUsername());
        commentTextArea.setText(comment.getComment());
        if(comment.getProfileImage()!=null){
            profileImage.setImage(new Image(new ByteArrayInputStream(comment.getProfileImage())));
        }
        return anchorPane;
    }
}
