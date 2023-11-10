package app.casinoroyale.Model.DataModels.GameModels.SlotsModel;

import app.casinoroyale.Model.DataModels.UserModels.Player;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class SlotGame {
    static String[] icons;
    static String[] imagesToString = new String[14];   //String array that will hold the toString value of pngs for comparing.
    static String[] imageBlocksToString = new String[9];   //String array that will hold the toString value of Images that is set to the ImagesViews for comparing.

    static int multiplier = 0;
    static String req = "";
     static double freq = 0;
    private static double balanceAmount = 100.0;    //Balance variable to keep track of balance after each spinn.
    private static double winAmount;                    //WinAmount variable that is used to display win amount after each spinn.

    private static int iconValue = 0;

    public int getIconValue(){
        return iconValue;
    }

    public String getReq(){
        return req;
    }

    public  double getFreq(){
        return freq;
    }

    public int getMultiplier(){
        return multiplier;
    }

    public void SlotGame(){

    }

    public static void setWinnings() {
            balanceAmount = 0;
        winAmount = 0;
        if (freq == 3) {    //if the most repeated png count is 3, multiply it by 2.
            iconValue = 2;
            balanceAmount += multiplier * iconValue;
            winAmount = multiplier * iconValue;
        }
        else if (freq == 4) {    //if the most repeated png count is 4, multiply it by 3.
            iconValue =4;
            balanceAmount += multiplier * iconValue;
            winAmount = multiplier * iconValue;
        }
        else if (freq == 5) {     //if the most repeated png count is 5, multiply it by 7.
            iconValue =10;
            balanceAmount += multiplier * iconValue;
            winAmount = multiplier * iconValue;
        }
       else if (freq == 6) {     //if the most repeated png count is 6, multiply it by 9.
            iconValue =25;
            balanceAmount += multiplier * iconValue;
            winAmount = multiplier * iconValue;
        }
       else if (freq == 7) {     //if the most repeated png count is 7, multiply it by 15.
            iconValue =35;
            balanceAmount += multiplier * iconValue;
            winAmount = multiplier * iconValue;
        }
        else if (freq == 8) {     //if the most repeated png count is 7, multiply it by 15.
            iconValue =70;
            balanceAmount += multiplier * iconValue;
            winAmount = multiplier * iconValue;
        }
        else if (freq == 9) {     //if the most repeated png count is 7, multiply it by 15.
            iconValue =150;
            balanceAmount += multiplier * iconValue;
            winAmount = multiplier * iconValue;
        } else {
            balanceAmount = balanceAmount;
            winAmount = 0;
        }
        Player.getInstance().setAccountBalance(winAmount);
        System.out.println("BALANCE: " + balanceAmount);
        System.out.println("");
        System.out.println("WIN AMOUNT: " + winAmount);
        System.out.println("");
    }


    public static void setMultiplier() {
        multiplier =0;
        if (req == "WATERMELON" || req == "SHAMROCK" || req == "BAR" || req == "LEMON") {    //If most repeated png is one of the given string multiplier is set to 1.
            multiplier = 1;
        }
        if (req == "HORSESHOE" || req == "STAR" || req == "GOLD") {    //If most repeated png is one of the given string multiplier is set to 3.
            multiplier = 2;
        }

        if (req == "DIOMAND" || req == "PUMPKIN" || req == "GUN") {     //If most repeated png is one of the given string multiplier is set to 5.
            multiplier = 4;
        }

        if (req == "DRAGON" || req == "HOTDOG" || req == "KING") {      //If most repeated png is one of the given string multiplier is set to 7.
            multiplier = 5;
        }
        if (req == "SEVEN") {                                         //If most repeated png is one of the given string multiplier is set to 10.
            multiplier = 20;
        }
        System.out.println("MULTIPLIER: " + multiplier);
        System.out.println("");
    }

    public static void displayWinAmount(Label aTextLabel) {
        aTextLabel.setText("$"+winAmount + "");
    }
    public static void displayBalanceAmount(Label textLabel) {
        textLabel.setText("$"+Player.getInstance().getAccountBalance() + "");
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
        icons = new String[]{"WATERMELON", "SHAMROCK", "BAR", "LEMON", "HORSESHOE", "STAR", "GOLD", "SEVEN",
                "PUMPKIN", "GUN", "DRAGON", "HOTDOG", "KING", "DIOMAND"};

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

