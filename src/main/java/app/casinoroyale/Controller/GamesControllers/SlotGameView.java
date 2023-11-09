package app.casinoroyale.Controller.GamesControllers;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SlotGameView {

    //Randomize method that will return a value between 0-12.
    private static int randomize() {
        int max = 13;
        int min = 0;
        new Random();
        int a = min + (int) (Math.random() * (double) (max - min + 1));
        return a;
    }


    //Put the outcome to the final String pngsOrder, so it can be used to print the current outcome if needed.
    public static void printResults(ArrayList<String> resultArrList, Label historyTF) {
        String pngsOrder = resultArrList.get(0) + " " + resultArrList.get(1) + " " + resultArrList.get(2) +
                "\n" + resultArrList.get(3) + " " + resultArrList.get(4) + " " + resultArrList.get(5) +
                "\n" + resultArrList.get(6) + " " + resultArrList.get(7) + " " + resultArrList.get(8) + "\n";
        System.out.println("");
        System.out.println(pngsOrder);
        System.out.println("");
        historyTF.setText(pngsOrder);

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
        alert.setTitle("Winning Chances");
        alert.setHeaderText("Winning Chances \n \n " +
                "In order to Win at least 2 of the icon match is  required.");
        alert.setContentText(
                " Here is the Winning amounts for matching 2 same icons \n\n" +
                        "                     Watermelon - $2 \n                     Gonca - $2 \n                     Bar - $2  " +
                        "\n                     King - $4 \n                     Horse Shoe - $4 \n                     7 - $4 " +
                        "\n                     Diomand - $6 \n " +
                        "\n Here is the Winning amounts for matching 3 same icons  " +
                        "\n\n                     Watermelon - $6 \n                     Gonca - $6 \n                     Bar - $6  " +
                        "\n                     King - $9 \n                     Horse Shoe - $9 \n                     7 - $9 " +
                        "\n                     Diomand - $15");
        alert.showAndWait();
    }
}
