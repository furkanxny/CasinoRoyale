package app.casinoroyale.Controller.GamesControllers;

import app.casinoroyale.Model.DataModels.GameModels.HorseModel.Horse;
import app.casinoroyale.Model.DataModels.GameModels.HorseModel.Race;
import app.casinoroyale.Model.DataModels.UserModels.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.util.Duration;
import app.casinoroyale.Controller.HomeController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HorseController {
    @FXML
    private ComboBox<String> horseSelect;
    @FXML
    private ImageView horse1, horse2, horse3, horse4, horse5;
    @FXML
    private Button startRace;
    @FXML
    private Label resultLabel;
    @FXML
    private TextField betAmountIn;
    @FXML
    private Pane racePane;

    @FXML
    private Label balanceLabel;

   private final app.casinoroyale.Controller.HomeController HomeController = new HomeController();




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
    private void homeDash(ActionEvent event) throws IOException {
        HomeController.homeDash(event);
    }


//    @FXML
//    private void launchBank(ActionEvent event) throws IOException {
//        HomeController.launchBank(event);
//    }



    private List<Horse> horses = new ArrayList<>();
    private Timeline checkFinishLine;
    private double balance = Player.getInstance().getAccountBalance();

    private Race race;

    @FXML
    public void initialize() {
        populateHorses();
        updateComboBox();
        balanceLabel.setText("Balance: $" + balance);
        checkFinishLine = new Timeline();
        race = new Race(racePane, horses);
    }

    @FXML
    private void showInstructions(ActionEvent event) {
        Alert instructionAlert = new Alert(Alert.AlertType.INFORMATION);
        instructionAlert.setTitle("How to Play");
        instructionAlert.setHeaderText("Instructions for Horse Racing Game");
        instructionAlert.setContentText(
                """
                        1. Select a horse from the dropdown menu.
                        2. Enter the amount you wish to bet in the text field.
                        3. Click 'Start Race' to begin the race.
                        4. Watch the horses race! If your horse wins, you will receive a payout based on the odds."""
        );

        instructionAlert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        instructionAlert.showAndWait();
    }

    @FXML
    public void startRaceAction(ActionEvent event) {
        double betAmount;
        try {
            betAmount = Double.parseDouble(betAmountIn.getText());
            if (betAmount < 1) {
                resultLabel.setText("Please enter a bet amount of at least 1.");
                return;
            }
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid bet amount entered.");
            return;
        }

        if (betAmount > balance) {
            resultLabel.setText("Insufficient balance for the bet.");
            return;
        }

        String selectedHorseName = getSelectedHorseName();
        if (selectedHorseName == null) {
            resultLabel.setText("Please select a horse to bet on.");
            return;
        }

       resetHorses();

        race.start();
        startRace.setDisable(true);

        checkFinishLine.getKeyFrames().clear();
        checkFinishLine.getKeyFrames().add(new KeyFrame(Duration.millis(50), actionEvent -> {
            if (race.isRaceFinished()) {
                Horse winner = race.getWinner();
                handleRaceFinish(winner, betAmount);
                checkFinishLine.stop();
            }
        }));

        checkFinishLine.setCycleCount(Timeline.INDEFINITE);
        checkFinishLine.playFromStart();
    }

    private void handleRaceFinish(Horse winner, double betAmount) {
        String selectedHorseName = getSelectedHorseName();

        if (winner.getName().equals(selectedHorseName)) {
            double payout = 0;
            payout = calculatePayout(winner);
            resultLabel.setText("Congratulations! " + winner.getName() + " won! Your payout is $" + payout);
            balance += payout;
            Player.getInstance().setAccountBalance(payout);
        } else {
            resultLabel.setText("Sorry, " + winner.getName() + " won! You lost.");
            balance -= betAmount;
            Player.getInstance().setAccountBalance(-betAmount);
        }

        updateBalanceLabel();
        startRace.setDisable(false);
        checkFinishLine.stop();

        race.resetHorses();// Reset horses and odds after the race is completed
        updateComboBox(); // Update the combo box with new odds after reset
    }





    private void updateBalanceLabel() {
        balanceLabel.setText("Balance: $" + Player.getInstance().getAccountBalance());
    }

    private String getSelectedHorseName() {
        String selectedHorseDetails = horseSelect.getSelectionModel().getSelectedItem();
        if (selectedHorseDetails != null) {
            String[] parts = selectedHorseDetails.split(" ");
            // This assumes that the horse's name is always two words: "Horse" followed by a number
            if (parts.length >= 2) {
                return parts[0] + " " + parts[1];
            }
        }
        return null; // Return null if no horse is selected or if the format is wrong
    }

    private void populateHorses() {
        horses.add(new Horse("Horse 1", horse1));
        horses.add(new Horse("Horse 2", horse2));
        horses.add(new Horse("Horse 3", horse3));
        horses.add(new Horse("Horse 4", horse4));
        horses.add(new Horse("Horse 5", horse5));
    }

    private Horse getSelectedHorse() {
        String selectedName = horseSelect.getSelectionModel().getSelectedItem().split(" ")[0]; // Assuming the format is "Horse X (Y/1)"
        for (Horse horse : horses) {
            if (horse.getName().equals(selectedName)) {
                return horse;
            }
        }
        return null;
    }

    private void updateComboBox() {
        horseSelect.getItems().clear(); // Clear the previous items
        for (Horse horse : horses) {
            horseSelect.getItems().add(horse.getName() + " (" + horse.getOdds() + ")");
        }
    }

    private void stopAllHorses() {
        for (Horse horse : horses) {
            horse.stopRace();
        }
    }

    private void resetHorses() {
        randomizeOdds(); // Randomize the odds for each horse
        randomizeSpeeds(); // Randomize the speeds for each horse
        resetPositions(); // Reset positions of the horse images
        updateComboBox(); // Update the combo box with the new odds
    }

    private void randomizeSpeeds() {
        for (Horse horse : horses) {
            double newSpeed = horse.generateSpeed();
            horse.setSpeed(newSpeed);
            //System.out.println("New speed for " + horse.getName() + " is " + newSpeed); // Debug statement
        }
    }

    private void resetPositions() {
        for (Horse horse : horses) {
            horse.getImageView().setLayoutX(0);
        }
    }

    private void randomizeOdds() {
        for (Horse horse : horses) {
            horse.setOdds();
        }
    }

    private Horse getWinner() {
        for (Horse horse : horses) {
            if (horse.getImageView().getLayoutX() >= racePane.getPrefWidth() - horse.getImageView().getFitWidth()) {
                return horse;
            }
        }
        return null;
    }

    private double calculatePayout(Horse winner) {
        double betAmount = Double.parseDouble(betAmountIn.getText());
        String[] oddsParts = winner.getOdds().split("/");
        double oddsValue = Double.parseDouble(oddsParts[0]);
        return betAmount * oddsValue;
    }
}