package ClientPackage.Controller;

import Commen.Comment;
import Commen.User;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class CommentItem extends ListCell<Comment> {
    @Override
    protected void updateItem(Comment comment, boolean empty) {
        super.updateItem(comment, empty);
        if (comment != null) {
            try {
                setGraphic(new CommentItemController(comment).init());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
