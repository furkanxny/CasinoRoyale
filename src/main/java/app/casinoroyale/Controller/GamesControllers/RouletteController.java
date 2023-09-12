package app.casinoroyale.Controller.GamesControllers;

import app.casinoroyale.Controller.HomeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class RouletteController {

    private app.casinoroyale.Controller.HomeController HomeController;

    public RouletteController(){
    }
    @FXML
    private void playBlackJack(ActionEvent event) throws IOException {
        HomeController.playBlackJack(event);
    }
    @FXML
    private void homeDash(ActionEvent event) throws IOException {
        HomeController.homeDash(event);
    }
}
