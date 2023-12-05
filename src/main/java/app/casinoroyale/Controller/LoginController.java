package app.casinoroyale.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class LoginController {
    @javafx.fxml.FXML
    private TextField emailTF;
    @javafx.fxml.FXML
    private TextField passwordTF;

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

    public void registerButtonHandler(ActionEvent event) {
        //switch to RegisterPage.fxml if user doesn't have an account yet.
    }
}
