package app.casinoroyale.Model.DataModels.GameModels.SlotsModel;

import app.casinoroyale.Model.DataModels.UserModels.Player;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Objects;

public class SlotGame {
    static int jackpotCounter = 0;
    static String[] icons;
    static String[] imagesToString = new String[14];
    static String[] imageBlocksToString = new String[9];
    static int multiplier = 0;
    static String req = "";
    static double freq = 0;
    private static double winAmount;
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

    public static void setCounter(){
        if(freq == 9 || freq == 8){
            jackpotCounter++;
        }
        System.out.println("\n" +
                "JACKPOT: " + jackpotCounter);
        System.out.println("\n" +
                "freg: " + freq);
    }

    public static void setWinnings() {
        double balanceAmount = 0;
        if (freq == 3) {
            iconValue = 2;
            balanceAmount += multiplier * iconValue;
            winAmount = multiplier * iconValue;
        }
        else if (freq == 4) {
            iconValue =4;
            balanceAmount += multiplier * iconValue;
            winAmount = multiplier * iconValue;
        }
        else if (freq == 5) {
            iconValue =10;
            balanceAmount += multiplier * iconValue;
            winAmount = multiplier * iconValue;
        }
        else if (freq == 6) {
            iconValue =25;
            balanceAmount += multiplier * iconValue;
            winAmount = multiplier * iconValue;
        }
        else if (freq == 7) {
            iconValue =35;
            balanceAmount += multiplier * iconValue;
            winAmount = multiplier * iconValue;
        }
        else if (freq == 8) {
            iconValue =70;
            balanceAmount += multiplier * iconValue;
            winAmount = multiplier * iconValue;
        }
        else if (freq == 9) {
            iconValue =150;
            balanceAmount += multiplier * iconValue;
            winAmount = multiplier * iconValue;
        } else {
            winAmount = 0;
        }
        Player.getInstance().setAccountBalance(winAmount);
        System.out.println("BALANCE: " + balanceAmount);
        System.out.println();
        System.out.println("WIN AMOUNT: " + winAmount);
        System.out.println();
    }


    public static void setMultiplier() {
        multiplier =0;
        if (Objects.equals(req, "WATERMELON") || Objects.equals(req, "SHAMROCK") || Objects.equals(req, "BAR") || Objects.equals(req, "LEMON")) {
            multiplier = 1;
        }
        if (Objects.equals(req, "HORSESHOE") || Objects.equals(req, "STAR") || Objects.equals(req, "GOLD")) {
            multiplier = 2;
        }

        if (Objects.equals(req, "DIAMOND") || Objects.equals(req, "PUMPKIN") || Objects.equals(req, "GUN")) {
            multiplier = 4;
        }

        if (Objects.equals(req, "DRAGON") || Objects.equals(req, "HOTDOG") || Objects.equals(req, "KING")) {
            multiplier = 5;
        }
        if (Objects.equals(req, "SEVEN")) {
            multiplier = 20;
        }
        System.out.println("MULTIPLIER: " + multiplier);
        System.out.println();
    }

    public static void displayWinAmount(Label aTextLabel) {
        aTextLabel.setText("$"+winAmount);
    }
    public static void displayBalanceAmount(Label textLabel) {
        textLabel.setText("$"+Player.getInstance().getAccountBalance());
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
        System.out.println();
        System.out.println("FREQ: " + freq);
        System.out.println();
    }

    public static void setResults(ArrayList<String> resultsArrList, Image[] pngsArry, ImageView[] imageBlocks) {
        icons = new String[]{"WATERMELON", "SHAMROCK", "BAR", "LEMON", "HORSESHOE", "STAR", "GOLD", "SEVEN",
                "PUMPKIN", "GUN", "DRAGON", "HOTDOG", "KING", "DIAMOND"};

        for (int i = 0; i < 14; i++) {
            imagesToString[i] = pngsArry[i].toString();
        }

        for (int i = 0; i < 9; i++) {
            imageBlocksToString[i] = imageBlocks[i].getImage().toString();
        }
        resultsArrList.clear();

        for (String s : imageBlocksToString) {
            for (int j = 0; j < 14; j++) {
                if (s.equals(imagesToString[j])) {
                    resultsArrList.add(icons[j]);
                }
            }
        }
    }
}

