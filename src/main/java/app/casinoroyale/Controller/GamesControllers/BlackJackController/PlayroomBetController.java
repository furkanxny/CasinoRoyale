package app.casinoroyale.Controller.GamesControllers.BlackJackController;

import app.casinoroyale.Controller.HomeController;
import app.casinoroyale.Model.DataModels.GameModels.BlackJackModel.game.Chip;
import app.casinoroyale.Model.DataModels.GameModels.BlackJackModel.game.Game;
import app.casinoroyale.Model.DataModels.GameModels.BlackJackModel.role.BlackJackPlayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

import java.io.IOException;

public class PlayroomBetController {

    BlackJackPlayer blackJackPlayer;

    Game game;

    @FXML
    Text txtBetValue;
    @FXML
    Text txtBalance;
    @FXML
    Button btnDeal;
    @FXML
    Button btnCancel;

    /**
     * Initialize a new game for the player.
     *
     * @param blackJackPlayer player to been shown on the UI as well as who plays the game
     */

    @FXML
    private void showInstructions(ActionEvent event) {
        Alert instructionAlert = new Alert(Alert.AlertType.INFORMATION);
        instructionAlert.setTitle("How to Play");
        instructionAlert.setHeaderText("Instructions for BlackJack Game");
        instructionAlert.setContentText(
                """
             1. Choose a starting bet amount for your blackjack hand.
             2. Click 'Deal' to receive your initial two cards.
             3. Decide whether to 'Hit' to get another card or 'Stand' to keep your current hand.
             4. Utilize 'Double Down' if you're feeling confident.
             5. Continue making strategic decisions until you decide to 'Stand' or until you exceed a total of 21 points, resulting in a bust.
             6. If you win the round, your payout will be added to your balance.
             7. Enjoy the excitement of playing blackjack and aim to beat the dealer!"""


        );

        instructionAlert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        instructionAlert.showAndWait();
    }
    public void loadContents(BlackJackPlayer blackJackPlayer) {
        this.blackJackPlayer = blackJackPlayer;
        this.game = new Game(blackJackPlayer);
        updateScene();
    }

    /**
     * Player adds a $10 value chip to the bet.
     */
    @FXML
    protected void onBet10ChipClicked() {
        System.out.println("Add a $10 chip to the bet");
        game.increaseBet(new Chip(10));
        updateScene();
    }

    /**
     * Player adds a $20 value chip to the bet.
     */
    @FXML
    protected void onBet20ChipClicked() {
        System.out.println("Add a $20 chip to the bet");
        game.increaseBet(new Chip(20));
        updateScene();
    }

    /**
     * Player adds a $50 value chip to the bet.
     */
    @FXML
    protected void onBet50ChipClicked() {
        System.out.println("Add a $50 chip to the bet");
        game.increaseBet(new Chip(50));
        updateScene();
    }

    /**
     * Player adds a $100 value chip to the bet.
     */
    @FXML
    protected void onBet100ChipClicked() {
        System.out.println("Add a $100 chip to the bet");
        game.increaseBet(new Chip(100));
        updateScene();
    }

    /**
     * Cancel the bet and return all chips.
     */
    @FXML
    protected void onCancelButtonClick() {
        System.out.println("Bet Canceled");
        game.clearBet();
        updateScene();
    }

    /**
     * Deal the bet (chips) and start the game.
     * Switch to game scene and update balance text in UI.
     */
    @FXML
    protected void onDealButtonClick() {
        System.out.println("Bet Dealt Game Start");
        blackJackPlayer.deal(game.getChips());
        game.start();
        switchToGameScene();
    }

    /**
     * Update player's balance in the scene.
     */
    private void updateScene() {
        if (game.getCurrentBetValue() == 0) {
            btnCancel.setDisable(true);
            btnDeal.setDisable(true);
        } else {
            btnCancel.setDisable(false);
            btnDeal.setDisable(false);
        }
        txtBetValue.setText(game.getCurrentBetValueFormatted());
        txtBalance.setText(blackJackPlayer.getBalanceFormatted());
    }

    private void switchToGameScene() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/casinoroyale/View/Games/playroom-game-view.fxml"));
            Parent root = fxmlLoader.load();

            // Assuming HomeController is properly initialized and has a reference to the primaryStage
            HomeController.getPrimaryStage().getScene().setRoot(root);

            PlayroomGameController gameController = fxmlLoader.getController();
            gameController.loadContents(blackJackPlayer, game);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

