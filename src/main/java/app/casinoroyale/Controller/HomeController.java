package app.casinoroyale.Controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    private Stage stage;

    public HomeController(){
        this.stage = stage;
    }

    public void playBlackJack(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app.casinoroyale/View/Games/BlackJack.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 3200, 2400);
        stage.setTitle("Roulette");
        stage.setScene(scene);
        stage.show();
    }
    public void playRoulette(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/app.casinoroyale/View/Games/Roulette.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 3200, 2400);
        stage.setTitle("Roulette");
        stage.setScene(scene);
        stage.show();
    }
}