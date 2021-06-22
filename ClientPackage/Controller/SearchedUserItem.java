package ClientPackage.Controller;

import Commen.Post;
import Commen.User;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class SearchedUserItem extends ListCell<User> {

    @Override
    public void updateItem(User user, boolean empty) {
        super.updateItem(user , empty);
        if(user!=null){
            try {
                setGraphic(new SearchedUserItemController(user).init());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

}
