package app.casinoroyale.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

interface regex{
        String regexUserName = "\\b[A-Z][a-zA-Z]+";
        String regexEmail = "[a-z0-9]+@[a-z0-9]+.[0-z]{2,6}";
        String regexPassword =
                "  ^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$\n";

}
public class RegisterController {

    @FXML
    private TextField userNameTF;
    @FXML
    private TextField emailTF;
    @FXML
    private TextField password1TF;
    @FXML
    private TextField password2TF;
    @FXML
    private RadioButton terms;

    private String userName;
    private String email;
    private String password;
    private String passwordConfirmation;


    public void registerButtonHandler(ActionEvent event) {
    }

    public void signInButtonHandler(ActionEvent event) {
    }
}