package app.casinoroyale;

import app.casinoroyale.Controller.FirebaseControllers.FirestoreContext;
import app.casinoroyale.Controller.HomeController;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class CSRApplication extends Application {
    public static Scene scene;
    public static Stage stage;

    public static Firestore fstore;
    public static FirebaseAuth fauth;
    private final FirestoreContext contxtFirebase = new FirestoreContext();


    @Override
    public void start(Stage primaryStage) throws IOException {

        URL resource = getClass().getResource("/app/casinoroyale/View/Dashboards/HomePage.fxml");


        if (resource == null) {
            throw new IOException("Cannot load resource: /app/casinoroyale/View/Dashboards/LoginPage.fxml");
        }

        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        AnchorPane root = fxmlLoader.load();

        HomeController controller = fxmlLoader.getController();
        controller.setStage(primaryStage);


        javafx.geometry.Rectangle2D screenSize = Screen.getPrimary().getBounds();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();

        Scene scene = new Scene(root, screenWidth * 0.8, screenHeight * 0.8);
        primaryStage.setTitle("Casino Royale");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static Stage getStage() {
        return stage;
    }
    public static void main(String[] args) {
        launch();
    }
}