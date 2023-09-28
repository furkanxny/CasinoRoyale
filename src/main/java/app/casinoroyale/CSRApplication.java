package app.casinoroyale;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox; // Import the type of your root element
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CSRApplication extends Application {
    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        // removes the top bar of the application window. (undecorated stage)
        stage.initStyle(StageStyle.TRANSPARENT);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/res.casinoroyale/View/Dashboards/HomePage.fxml"));
        VBox root = new VBox(); // Create an instance of your root element
        loader.setRoot(root); // Set the root before loading
        loader.load();

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
