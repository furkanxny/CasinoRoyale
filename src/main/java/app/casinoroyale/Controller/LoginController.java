package app.casinoroyale.Controller;

import app.casinoroyale.CSRApplication;
import app.casinoroyale.Controller.FirebaseControllers.Person;
import app.casinoroyale.Controller.FirebaseControllers.PrimaryController;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class LoginController {
    @javafx.fxml.FXML
    private TextField emailTF;
    @javafx.fxml.FXML
    private TextField passwordTF;

    private app.casinoroyale.Controller.HomeController homeController;
    private app.casinoroyale.Controller.FirebaseControllers.PrimaryController primaryController;

    private Stage stage;
    public LoginController(){
        this.homeController = new HomeController();
        this.stage = new Stage();
        this.primaryController = new PrimaryController();
    }


    @FXML
    public void registerButtonHandler(ActionEvent event) throws IOException{
            homeController.registerDash(event);
    }


    public void signInButtonHandler(ActionEvent actionEvent) {
        primaryController.readFirebase();
    }
}
