package ClientPackage.Model;

import Commen.User;
import javafx.application.Application;

import javafx.stage.Stage;
//--module-path "C:\Program Files\javafx-sdk-12.0.1\lib" --add-modules javafx.controls,javafx.fxml
public class Main extends Application {
    public static User user;
    @Override
    public void start(Stage primaryStage) throws Exception{
        PageLoader.initStage(primaryStage);
        new PageLoader().load("Login");
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static User getUser() {
        user=API.getUser(user.getUsername());
        return user;
    }

    public static void setUser(User user) {
        Main.user = user;
    }
}
