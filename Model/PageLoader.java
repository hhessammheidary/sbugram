package Model;

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
        stage.setWidth(300);
        stage.setHeight(640);
        stage.initStyle(StageStyle.DECORATED);
        stage.setResizable(false);
        stage.getIcons().add(new Image(Paths.get("C:\\Users\\HP\\Desktop\\projeAP\\87390.png").toUri().toString()));
    }

    public Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/View/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public void load(String url) throws IOException {
        scene = new Scene(new PageLoader().loadFXML(url));
        stage.setScene(scene);
        stage.show();
    }

}
