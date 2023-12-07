package app.casinoroyale.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @javafx.fxml.FXML
    private TextField emailTF;
    @javafx.fxml.FXML
    private TextField passwordTF;

    private app.casinoroyale.Controller.HomeController homeController;

    private Stage stage;
    public LoginController(){
        this.homeController = new HomeController();
        this.stage = new Stage();
    }
    public void signInButtonHandler(ActionEvent event) {
//        String email = emailTF.getText();
//        String password = passwordTF.getText();
//
//        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        //Switch to HomePage.fxml.
//                    } else {
//
//                    }
//                });
    }

    @FXML
    public void registerButtonHandler(ActionEvent event) throws IOException{
            homeController.registerDash(event);
    }
}
