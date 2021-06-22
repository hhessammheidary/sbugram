package ClientPackage.Controller;

import ClientPackage.Model.API;
import ClientPackage.Model.Main;
import ClientPackage.Model.PageLoader;
import Commen.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;

public class SearchPageController {
    public TextField searchUsernameTextField;
    public Button searchUsernameButton;
    public Button timeLineButton;
    public Button addPostButton;
    public Button searchPageButton;
    public Button profileButton;
    public ListView<User> listView;

    private ArrayList<User> users=new ArrayList<>();

    public void searchUsername(ActionEvent actionEvent) {
        if(!searchUsernameTextField.getText().isEmpty()){
            if(API.SearchUser(searchUsernameTextField.getText())!=null){
                users=API.SearchUser(searchUsernameTextField.getText());
            }
        }
    }

    public void initialize() throws IOException {
        listView.setItems(FXCollections.observableArrayList(users));
        listView.setCellFactory(listView -> new SearchedUserItem());
    }

    public void goToTimeLinePage(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("TimeLine");
    }

    public void goToAddPostPage(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("AddPostPage");
    }

    public void goToSearchPage(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("SearchPage");
    }

    public void goToProfilePage(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("MyProfilePage");
    }
}
