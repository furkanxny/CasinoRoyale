package app.casinoroyale.Controller;

import app.casinoroyale.Model.DataModels.GameModels.BlackJackModel.role.BlackJackPlayer;
import app.casinoroyale.Controller.GamesControllers.BlackJackController.PlayroomBetController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HomeController {


    private final BlackJackPlayer blackJackPlayer;
    private static Stage primaryStage;
    private Stage stage;

    public HomeController() {
        this.blackJackPlayer = new BlackJackPlayer();
    }


    private Stage stage = new Stage();

    @FXML
    private ImageView slotsImageView;
    @FXML
    private ImageView rouletteImageView;
    @FXML
    private ImageView horseraceImageView;
    @FXML
    private ImageView blackjackImageView;
    @FXML
    private ImageView casionoImageView;


    @FXML
    public void initialize() {
        initializeImages();
    }


    public HomeController(){
        this.stage.show();
    }


    public void initializeImages(){

        File roulette = new File("src/main/resources/app/Assets/HomePage/Games/roulette.png");
        File slots = new File("src/main/resources/app/Assets/HomePage/Games/slots.png");
        File horseRace = new File("src/main/resources/app/Assets/HomePage/Games/horseracing.png");
        File blackJack = new File("src/main/resources/app/Assets/HomePage/Games/blackjack.png");
        File casino = new File("src/main/resources/app/Assets/HomePage/Casino/casino.png");

        Image rouletteImage = new Image(roulette.toURI().toString());
        Image slotsImage = new Image(slots.toURI().toString());
        Image horseRaceImage = new Image(horseRace.toURI().toString());
        Image blackJackImage = new Image(blackJack.toURI().toString());
        Image casinoImage = new Image(casino.toURI().toString());

        rouletteImageView.setImage(rouletteImage);
        slotsImageView.setImage(slotsImage);
        horseraceImageView.setImage(horseRaceImage);
        blackjackImageView.setImage(blackJackImage);
        casionoImageView.setImage(casinoImage);
    }

    FXMLLoader homeFXML = new FXMLLoader(getClass().getResource("/app/casinoroyale/View/Dashboards/HomePage.fxml"));

    public void homeDash(ActionEvent actionEvent) throws IOException {
        Scene scene = new Scene(homeFXML.load(), 3200, 2400);
        stage.setTitle("Casino Royale");
        stage.setScene(scene);

    }

    public void playBlackJack(ActionEvent actionEvent) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/casinoroyale/View/Games/Playroom-bet-view.fxml"));
        Scene betViewScene = new Scene(fxmlLoader.load());
        PlayroomBetController betController = fxmlLoader.getController();
        betController.loadContents(blackJackPlayer);

        primaryStage = stage;
        stage.setTitle("Blackjack Game");
        stage.setScene(betViewScene);
        stage.show();

    }

    public void playRoulette(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/casinoroyale/View/Games/Roulette.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 3200, 2400);
        stage.setTitle("Roulette");
        stage.setScene(scene);
    }

    public void playHorseRacing(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/casinoroyale/View/Games/HorseRacing.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 3200, 2400);
        stage.setTitle("Horse Racing");
        stage.setScene(scene);
    }

    public void playSlots(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app/casinoroyale/View/Games/Slots.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 2600, 2400);
        stage.setTitle("Slots");
        stage.setScene(scene);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
