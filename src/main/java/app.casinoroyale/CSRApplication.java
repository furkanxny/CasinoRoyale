package app.casinoroyale;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CSRApplication extends Application {
    private Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app.casinoroyale/View/Dashboards/SceneBuilder.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 3200, 2400);
        stage.setTitle("Chris Mariana");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}