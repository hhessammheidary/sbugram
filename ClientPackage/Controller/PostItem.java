package ClientPackage.Controller;

import Commen.Post;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class PostItem extends ListCell<Post> {

    @Override
    public void updateItem(Post post, boolean empty) {
        super.updateItem(post, empty);
        if(post!=null){
            try {
                setGraphic(new PostItemController(post).init());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
