package app.casinoroyale;

import app.casinoroyale.Controller.HomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class CSRApplication extends Application {
    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        // removes the top bar of the application window. (undecorated stage)
        stage.initStyle(StageStyle.TRANSPARENT);

        Parent root = FXMLLoader.load(getClass().getResource("/app.casinoroyale/View/Games/Roulette.fxml"));

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        stage.setTitle("Casino");
        stage.setScene(scene);
        stage.show();


    }


    public static void main(String[] args) {
        launch();
    }

    public static Stage getStage() {
        return stage;
    }

}