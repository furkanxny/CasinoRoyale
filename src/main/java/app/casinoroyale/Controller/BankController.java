package app.casinoroyale.Controller;

import app.casinoroyale.Model.DataModels.UserModels.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;

import java.io.IOException;
import java.util.Optional;

public class BankController {

    @FXML
    private Button withdraw;
    @FXML
    private Button deposit;


    private app.casinoroyale.Controller.HomeController HomeController;
    @FXML
    private void playBlackJack(ActionEvent event) throws IOException {
        HomeController.playBlackJack(event);
    } @FXML
    private void playRoulette(ActionEvent event) throws IOException {
        HomeController.playRoulette(event);
    }@FXML
    private void playSlots(ActionEvent event) throws IOException {
        HomeController.playSlots(event);
    }
    @FXML
    private void playHorseRacing(ActionEvent event) throws IOException {
        HomeController.playHorseRacing(event);
    }
    @FXML
    private void homeDash(ActionEvent event) throws IOException {
        HomeController.homeDash(event);
    }

    @FXML
    private void showDepositDialog(ActionEvent event) {
        TextInputDialog depositDialog = new TextInputDialog();
        depositDialog.setTitle("Deposit Money");
        depositDialog.setHeaderText("Enter the amount to deposit:");
        Optional<String> depositResult = depositDialog.showAndWait();
        depositResult.ifPresent(amount -> {
            // TODO: Implement the actual deposit logic here
            Alert depositAlert = new Alert(Alert.AlertType.INFORMATION);
            depositAlert.setTitle("Deposit Successful");
            depositAlert.setHeaderText(null);
            depositAlert.setContentText("Successfully deposited: " + amount);
            depositAlert.showAndWait();
        });
    }

//    @FXML
//    private void showWithdrawDialog(ActionEvent event) {
//        TextInputDialog withdrawDialog = new TextInputDialog();
//        withdrawDialog.setTitle("Withdraw Money");
//        withdrawDialog.setHeaderText("Enter the amount to withdraw:");
//        Optional<String> withdrawResult = withdrawDialog.showAndWait();
//        withdrawResult.ifPresent(amount -> {
//            try {
//                double amountDouble = Double.parseDouble(amount);
//                if (Player.getInstance().canWithdraw(amountDouble)) {
//                    Player.getInstance().withdraw(amountDouble);
//                    Alert withdrawAlert = new Alert(Alert.AlertType.INFORMATION);
//                    withdrawAlert.setTitle("Withdrawal Successful");
//                    withdrawAlert.setHeaderText(null);
//                    withdrawAlert.setContentText("Successfully withdrawn: " + amountDouble);
//                    withdrawAlert.showAndWait();
//                } else {
//                    showErrorMessage("Insufficient funds or invalid amount");
//                }
//            } catch (NumberFormatException e) {
//                showErrorMessage("Invalid input. Please enter a valid number.");
//            }
//        });
//    }

    private void showErrorMessage(String message) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText(null);
        errorAlert.setContentText(message);
        errorAlert.showAndWait();
    }
    }











