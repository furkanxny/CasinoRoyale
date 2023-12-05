package app.casinoroyale.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

interface regexMatcher {
    String regexUserName = "\\b[A-Z][a-zA-Z]+";
    String regexEmail = "[a-z0-9]+@[a-z0-9]+.[0-z]{2,6}";
    String regexPassword =
            "  ^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$\n";
}
public class RegisterController implements regexMatcher {
    @FXML
    private TextField emailTF;
    @FXML
    private TextField password1TF;
    @FXML
    private TextField password2TF;

    public void registerButtonHandler(ActionEvent event) {
//        String email = emailTF.getText();
//        String password = password1TF.getText();
//        String confirmPassword = password2TF.getText();
//
//        if (!password.matches(regexEmail) || !password.matches(regexUserName) || !confirmPassword.matches(regexPassword)) {
//            Alert requirementsAlert = new Alert(Alert.AlertType.ERROR);
//            requirementsAlert.setTitle("REGISTER ERROR");
//            requirementsAlert.setHeaderText("REGISTER ERROR");
//            requirementsAlert.setContentText("You are missing at least one of the requirements");
//            return;
//        }
//
//        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        //Switch to LoginPage.fxml if user created an account successfully.
//                    } else {
//                        Alert firebaseAlert = new Alert(Alert.AlertType.ERROR);
//                        firebaseAlert.setTitle("FIREBASE ERROR");
//                        firebaseAlert.setHeaderText("FIREBASE ERROR");
//                        firebaseAlert.setContentText("There is a firebase registration ERROR");
//                    }
//                });
    }

    public void signInButtonHandler(ActionEvent event) {
        //switch to LoginPage.fxml if the user already has an account.
    }
}