package app.casinoroyale;

import app.casinoroyale.Controller.GamesControllers.HorseController;
import app.casinoroyale.Controller.HomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Launches the Horse Racing Game.
 */
public class CSRApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/app/casinoroyale/View/Games/HorseRacing.fxml"));
        primaryStage.setTitle("Horse Racing Game");
        primaryStage.setScene(new Scene(root, 1102, 754));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}