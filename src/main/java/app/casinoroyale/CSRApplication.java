package app.casinoroyale;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class CSRApplication extends Application {
    private Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        VBox root = new VBox(); // Assuming VBox is the root type you want

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app.casinoroyale/View/Dashboards/HomePage.fxml"));
        fxmlLoader.setRoot(root); // Set the root before loading
        fxmlLoader.load();
        Scene scene = new Scene(root, 3200, 2400);
        stage.setTitle("Casino Royale");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}