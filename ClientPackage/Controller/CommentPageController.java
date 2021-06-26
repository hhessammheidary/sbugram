package ClientPackage.Controller;

import ClientPackage.Model.API;
import ClientPackage.Model.Main;
import ClientPackage.Model.PageLoader;
import Commen.Comment;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class CommentPageController {
    public TextField commentTextField;
    public Button commentButton;
    public Button backButton;
    public ListView<Comment> listView;

    ArrayList<Comment> comments=new ArrayList<>();
    public void initialize() throws IOException {
        getComments();
        listView.setItems(FXCollections.observableArrayList(comments));
        listView.setCellFactory(listView -> new CommentItem());
    }

    public void comment(ActionEvent actionEvent) throws IOException {
        if(!commentTextField.getText().isEmpty()){
            Comment comment=new Comment(Main.getUser() , commentTextField.getText());
            API.addComment(comment , Main.getCommentPost());
            commentTextField.setText("");
            initialize();
        }
    }

    public void backToTimeLine(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("TimeLine");
    }

    public void getComments(){
        comments = API.getComments(Main.commentPost);
    }
}
