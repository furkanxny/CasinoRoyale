package app.casinoroyale;

import app.casinoroyale.Controller.HomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class CSRApplication extends Application {
    private Stage stage;
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.stage = primaryStage;
        VBox root = new VBox();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app.casinoroyale/View/Dashboards/HomePage.fxml"));
        fxmlLoader.setRoot(root);
        fxmlLoader.load();

        HomeController controller = fxmlLoader.getController();
        controller.setStage(primaryStage);

        Scene scene = new Scene(root, 3200, 2400);
        primaryStage.setTitle("Casino Royale");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch();
    }

}