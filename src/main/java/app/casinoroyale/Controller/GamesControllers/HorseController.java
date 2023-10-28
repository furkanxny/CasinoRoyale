package app.casinoroyale.Controller.GamesControllers;

import app.casinoroyale.Model.DataModels.GameModels.HorseModel.Horse;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

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

    private List<Horse> horses = new ArrayList<>();
    private Timeline checkFinishLine;
    private double balance = 1000;

    @FXML
    public void initialize() {
        populateHorses();
        updateComboBox();
        balanceLabel.setText("Balance: $" + balance);
    }

    @FXML
    public void startRaceAction(ActionEvent event) {
        resetHorses();

        for (Horse horse : horses) {
            horse.startRace(racePane.getPrefWidth() - horse.getImageView().getFitWidth());
        }

        startRace.setDisable(true);

        // Initializing the variable here
        Timeline checkFinishLine = new Timeline();

        checkFinishLine.getKeyFrames().add(new KeyFrame(Duration.millis(50), actionEvent -> {
            Horse winner = getWinner();
            if (winner != null) {
                if (winner.getName().equals(getSelectedHorseName())) {
                    resultLabel.setText("Congratulations! " + winner.getName() + " won! Your payout is $" + calculatePayout(winner));
                    balance += calculatePayout(winner); // Add to the balance
                } else {
                    resultLabel.setText("Sorry, " + winner.getName() + " won! You lost.");
                    balance -= Double.parseDouble(betAmountIn.getText()); // Subtract from the balance
                }
                updateBalanceLabel();
                startRace.setDisable(false);
                checkFinishLine.stop();
            }
        }));

        checkFinishLine.setCycleCount(Timeline.INDEFINITE);
        checkFinishLine.play();
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Balance: $" + balance);
    }

    private String getSelectedHorseName() {
        String selectedHorseDetails = horseSelect.getSelectionModel().getSelectedItem();
        return selectedHorseDetails.split(" ")[0];  // Extract horse name assuming the format is "Horse X (Y/1)"
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
        for (Horse horse : horses) {
            horseSelect.getItems().add(horse.getName() + " (" + horse.getOdds() + ")");
        }
    }

    private void resetHorses() {
        for (Horse horse : horses) {
            horse.getImageView().setLayoutX(0);
            horse.setOdds(); // Make sure this function updates the odds for the horse
            horse.setSpeed(horse.generateSpeed());
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