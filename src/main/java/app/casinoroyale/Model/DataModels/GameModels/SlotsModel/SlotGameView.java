package app.casinoroyale.Model.DataModels.GameModels.SlotsModel;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SlotGameView {
    private int count = 0;
    private final int maxCount = 5;
    private Timer timer;

    static SlotGame SG = new SlotGame();

    //Randomize method that will return a value between 0-12.
    private static int randomize() {
        int max = 13;
        int min = 0;
        new Random();
        int a = min + (int) (Math.random() * (double) (max - min + 1));
        return a;
    }


    //Put the outcome to the final String pngsOrder, so it can be used to print the current outcome if needed.
    public static void printResults(ArrayList<String> resultArrList, Label historyTF, Label winText) {
//        String pngsOrder = resultArrList.get(0) + " " + resultArrList.get(1) + " " + resultArrList.get(2) +
//                "\n" + resultArrList.get(3) + " " + resultArrList.get(4) + " " + resultArrList.get(5) +
//                "\n" + resultArrList.get(6) + " " + resultArrList.get(7) + " " + resultArrList.get(8) + "\n";

        StringBuilder pngsOrder = new StringBuilder();
        StringBuilder winOrder = new StringBuilder();
        int columnWidth = 15;
        for (int i = 0; i < 3; i++) {
            pngsOrder.append(String.format("%-" + columnWidth + "s", resultArrList.get(i)));
        }
        pngsOrder.append("\n");
        for (int i = 3; i < 6; i++) {
            pngsOrder.append(String.format("%-" + columnWidth + "s", resultArrList.get(i)));
        }
        pngsOrder.append("\n");
        for (int i = 6; i < 9; i++) {
            pngsOrder.append(String.format("%-" + columnWidth + "s", resultArrList.get(i)));
        }
        System.out.println("");
        System.out.println(pngsOrder);
        System.out.println("");
        historyTF.setText(String.valueOf(pngsOrder));

        Platform.runLater(()->{
        winOrder.setLength(0);
        if(SG.getFreq() >= 3) {

                winOrder.append(SG.getReq() + "  ");


            winOrder.append("\n\n"+"Value - "+ SG.getMultiplier()+"\n"+ "Multiplier - "+ SG.getIconValue() );


            winText.setText(String.valueOf(winOrder));
        }
        else winText.setText("");

    });
    }

    public void start() {
        timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                count++;
                if (count <= maxCount) {
                    // updateSlotsImages();
                } else {
                    timer.cancel(); // Stop the timer after 5 times
                }
            }
        };

        // Schedule the task to run every 200 milliseconds (0.2 seconds)
        timer.scheduleAtFixedRate(task, 0, 200);
    }




    //updateSlotsImages method that will assign the ImageView objects with randomize method and set new pngs to imageBlocks randomly.
    public static void updateSlotsImages(ImageView[] imageViewArry, Image[] imageArry) {
        for (int i = 0; i < 3; ++i) {
            imageViewArry[i].setImage(imageArry[randomize()]);
        }
        for (int i = 3; i < 6; ++i) {
            imageViewArry[i].setImage(imageArry[randomize()]);
        }
        for (int i = 6; i < 9; ++i) {
            imageViewArry[i].setImage(imageArry[randomize()]);
        }
    }


    //infoButton method that will create an Alert object and let it display the rules for the slot machine game when it's called.
    public static void infoButton() {
        String winningInfo = null;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("WINNING CHANCES");
        alert.setHeaderText("IN ORDER TO WIN AT LEAST 3 OF THE ICON MATCH IS REQUIRED.");
        alert.setContentText(
                "      HERE IS THE MULTIPLIER FOR MATCHING DIFFERENT NUMBER OF SAME ICONS \n" +
                        "\n             3 ICONS: x2         4 ICONS: x3         5 ICONS: x7         6 ICONS: x9     7 ICONS: x15"+
                        "\n\n$$$$$$$$$$$$$$$$$$ HERE IS THE VALUE OF EACH ICON $$$$$$$$$$$$$$$$$$$$" +
                        "\n\n $1           WATERMELON     SHAMROCK     BAR     LEMON"+
                        "\n\n $3           HORSESHOE     STAR    GOLD"+
                        "\n\n $5           SEVEN      PUMPKIN    GUN"+
                        "\n\n $7           DRAGON     HOTDOGS   KING"+
                        "\n\n $10          DIOMAND");
        alert.showAndWait();
    }


    public static void exitButton(){
        Platform.exit();
    }
}
