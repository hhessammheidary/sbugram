package ClientPackage.Model;

import ClientPackage.Controller.PostItemController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.nio.file.Paths;

public class PageLoader {
    public static Stage stage;
    private static Scene scene;

    public static void initStage(Stage primaryStage){
        stage=primaryStage;
        stage.setTitle("sbugram");
        stage.setWidth(315);
        stage.setHeight(640);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.getIcons().add(new Image(Paths.get("C:\\Users\\HP\\Desktop\\projeAP\\87390.png").toUri().toString()));
    }

    public void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Stage getPrimaryStage() {
        return stage;
    }

    public Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/ClientPackage/View/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public void load(String url) throws IOException {
        scene = new Scene(new PageLoader().loadFXML(url));
        stage.setScene(scene);
        stage.show();
    }

    public void load(String fxml, Object postItemController) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/ClientPackage/View/" + fxml + ".fxml"));
        fxmlLoader.setController(postItemController);
        fxmlLoader.load();
    }
}
