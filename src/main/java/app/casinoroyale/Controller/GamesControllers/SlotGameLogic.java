package app.casinoroyale.Controller.GamesControllers;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class SlotGameLogic {
    static String[] icons;
    static String[] imagesToString = new String[14];   //String array that will hold the toString value of pngs for comparing.
    static String[] imageBlocksToString = new String[9];   //String array that will hold the toString value of Images that is set to the ImagesViews for comparing.

    static int multiplier = 0;
    static String req = "";
     static double freq = 0;
    private static double balanceAmount = 100.0;    //Balance variable to keep track of balance after each spinn.
    private static double winAmount;                    //WinAmount variable that is used to display win amount after each spinn.

    public static void setWinnings() {
            balanceAmount = balanceAmount-3;
        winAmount = 0;
        if (freq == 3) {    //if the most repeated png count is 3, multiply it by 2.
            balanceAmount += multiplier * 2;
            winAmount = multiplier * 2;
        }
        else if (freq == 4) {    //if the most repeated png count is 4, multiply it by 2.
            balanceAmount += multiplier * 3;
            winAmount = multiplier * 3;
        }
        else if (freq == 5) {     //if the most repeated png count is 5, multiply it by 2.
            balanceAmount += multiplier * 5;
            winAmount = multiplier * 5;
        }
       else if (freq == 6) {     //if the most repeated png count is 6, multiply it by 2.
            balanceAmount += multiplier * 7;
            winAmount = multiplier * 5;
        }
       else if (freq == 7) {     //if the most repeated png count is 7, multiply it by 9.
            balanceAmount += multiplier * 9;
            winAmount = multiplier * 9;
        } else {
            balanceAmount = balanceAmount;
            winAmount = 0;
        }
        System.out.println("BALANCE: " + balanceAmount);
        System.out.println("");
        System.out.println("WIN AMOUNT: " + winAmount);
        System.out.println("");
    }


    public static void setMultiplier() {
        multiplier =0;
        if (req == "WATERMELON" || req == " SHAMROCK " || req == "   BAR   " || req == "   LEMON   ") {    //If most repeated png is one of the given string multiplier is set to 1.
            multiplier = 1;
        }
        if (req == "HORSESHOE" || req == "   STAR   " || req == "   GOLD   ") {    //If most repeated png is one of the given string multiplier is set to 3.
            multiplier = 3;
        }

        if (req == "   SEVEN   " || req == "  PUMPKIN  " || req == "   GUN   ") {     //If most repeated png is one of the given string multiplier is set to 5.
            multiplier = 5;
        }

        if (req == "  DRAGON  " || req == "  HOTDOG  " || req == "   KING   ") {      //If most repeated png is one of the given string multiplier is set to 7.
            multiplier = 7;
        }
        if (req == " DIOMAND ") {                                         //If most repeated png is one of the given string multiplier is set to 10.
            multiplier = 10;
        }
        System.out.println("MULTIPLIER: " + multiplier);
        System.out.println("");
    }

    public static void displayWinAmount(Label aTextLabel) {
        aTextLabel.setText(winAmount + "");
    }
    public static void displayBalanceAmount(Label textLabel) {
        textLabel.setText(balanceAmount + "");
    }

    public static void setMostRepeated(ArrayList<String> resultArrList) {
        freq = 0;
        req = "";
        for (int i = 0; i < resultArrList.size(); i++) {
            int counter = 0;
            for (int j = 1 + i; j < resultArrList.size(); j++) {
                if (resultArrList.get(i).equals(resultArrList.get(j))) {
                    counter++;
                }
            }
            if (counter + 1 >= freq) {
                freq = counter + 1;
                req = resultArrList.get(i);
            }
        }
        System.out.println("REQ: " + req);
        System.out.println("");
        System.out.println("FREQ: " + freq);
        System.out.println("");
    }

    //printPlayHistoryAmount method that will set the String list for an outcome of the pngs.
    public static void setResults(ArrayList<String> resultsArrList, Image[] pngsArry, ImageView[] imageBlocks) {
        icons = new String[]{"WATERMELON", " SHAMROCK ", "   BAR   ", "   LEMON   ", "HORSESHOE", "   STAR   ", "   GOLD   ", "   SEVEN   ",
                "  PUMPKIN  ", "   GUN   ", "  DRAGON  ", "  HOTDOG  ", "   KING   ", " DIOMAND "};

        for (int i = 0; i < 14; i++) {
            imagesToString[i] = pngsArry[i].toString();
        }

        for (int i = 0; i < 9; i++) {
            imageBlocksToString[i] = imageBlocks[i].getImage().toString();
        }
        resultsArrList.clear();  // Clear the resultArrList so past an outcome of pngs will be deleted.

        for (int i = 0; i < imageBlocksToString.length; i++) {   //Load resultArrList with an outcome.
            for (int j = 0; j < 14; j++) {
                if (imageBlocksToString[i].equals(imagesToString[j])) {
                    resultsArrList.add(icons[j]);
                }
            }
        }


    }


}

