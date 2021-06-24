package ClientPackage.Controller;

import ClientPackage.Model.PageLoader;
import Commen.User;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class SearchedUserItemController {
    public User user;
    public Label usernameLabel;
    public ImageView profileImage;
    public AnchorPane anchorPane;
    public SearchedUserItemController(User user) throws IOException {
        new PageLoader().load("SearchedUserItem" , this);
        this.user=user;
    }

    public AnchorPane init() {
        usernameLabel.setText(user.getUsername());
        if(user.getProfileImage()!=null){
            profileImage.setImage(new Image(new ByteArrayInputStream(user.getProfileImage())));
        }
        return anchorPane;
    }
}
