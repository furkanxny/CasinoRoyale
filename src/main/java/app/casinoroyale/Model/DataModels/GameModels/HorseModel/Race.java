package app.casinoroyale.Model.DataModels.GameModels.HorseModel;

import javafx.scene.layout.Pane;

import java.util.List;

public class Race {
    private Pane racePane;
    private List<Horse> horses;
    private double finishLine;
    private Horse winner;

    public Race(Pane racePane, List<Horse> horses) {
        this.racePane = racePane;
        this.horses = horses;
        this.finishLine = racePane.getPrefWidth();
        this.winner = null;
    }

    public void start() {
        for (Horse horse : horses) {
            horse.startRace(finishLine - horse.getImageView().getFitWidth());
        }
    }

    public void stop() {
        for (Horse horse : horses) {
            horse.stopRace();
        }
    }

    public boolean isRaceFinished() {
        for (Horse horse : horses) {
            if (horse.getImageView().getLayoutX() >= finishLine - horse.getImageView().getFitWidth()) {
                winner = horse;
                stop(); // Stop all horses once a winner is determined
                return true;
            }
        }
        return false;
    }

    public Horse getWinner() {
        return winner;
    }

    // Resets the position of all horses to start a new race
    public void resetHorses() {
        for (Horse horse : horses) {
            horse.getImageView().setLayoutX(0);
        }
    }
}