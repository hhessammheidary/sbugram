package ClientPackage.Controller;

import ClientPackage.Model.PageLoader;
import Commen.User;
import javafx.scene.layout.AnchorPane;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class SearchedUserItemController {
    public User user;
    public TextArea usernameLabel;
    public ImageView profileImage;
    public AnchorPane anchorPane;
    public SearchedUserItemController(User user) throws IOException {
        new PageLoader().load("SearchedUserItem" , this);
        this.user=user;
    }

    public AnchorPane init() {
        usernameLabel.setText(user.getUsername());

        return anchorPane;
    }

    public void goToUsersProfile(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("OtherProfilePage");
    }
}
