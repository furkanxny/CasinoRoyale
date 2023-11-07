package app.casinoroyale.Controller.GamesControllers;

import app.casinoroyale.Controller.HomeController;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;


public class SlotsController {
    @FXML
    private ImageView imageBlock1;
    @FXML
    private ImageView imageBlock2;
    @FXML
    private ImageView imageBlock3;
    @FXML
    private ImageView imageBlock4;
    @FXML
    private ImageView imageBlock5;
    @FXML
    private ImageView imageBlock6;
    @FXML
    private ImageView imageBlock7;
    @FXML
    private ImageView imageBlock8;
    @FXML
    private ImageView imageBlock9;
    @FXML
    private ImageView buttonImageView1;
    @FXML
    private ImageView buttonImageView2;
    @FXML
    private ImageView buttonImageView3;
    @FXML
    private ImageView infoButtonImage;
    @FXML
    private ImageView button1ImageView;
    @FXML
    private Text balanceText;
    @FXML
    private Label aTextLabel;
    @FXML
    private Label textLabel;
    @FXML
    private MenuItem homeDash;
    @FXML
    private Button button1$;

    private ImageView[] imageBlocksArry;    //an ImageView array that holds the 9 ImageView(ImageBlock) objects.
    Image[] pngsArry = new Image[14];       //an Image array that holds all the png images.
    File[] fileArry = new File[13];         //an File array that holds the location of Images.

    String req = "";    //most repeated png string.
    int freq = 0;       //most repeated png count.
    private static double balanceAmount = 100.0;    //Balance variable to keep track of balance after each spinn.
    private static double winAmount = 5;                    //WinAmount variable that is used to display win amount after each spinn.
    private static int multiplier = 0;                      //multiplier variable that holds the multiplier value according to outcome of the pngs.
    private HomeController HomeController;
    ArrayList<String> resultArrList = new ArrayList<String>();  //resultArrList that hold the String list for outcome of the pngs.


    @FXML
    public void initialize() {
        this.imageBlocksArry = new ImageView[]{this.imageBlock1, this.imageBlock2, this.imageBlock3, this.imageBlock4,
                this.imageBlock5, this.imageBlock6, this.imageBlock7, this.imageBlock8, this.imageBlock9};
        this.initializeImages();
        initailizeBetHistoryTF();
        this.InitializeSetImages();
        balanceInitialize();
        InitializeButtonImages();
    }

    private void initializeImages() {   //Initialize Images and Files.
        int i;
        for (i = 0; i < 13; ++i) {
            this.fileArry[i] = new File("src/main/resources/app/Assets/Slots/icons/" + i + ".png");
        }
        for (i = 0; i < 13; ++i) {
            this.pngsArry[i] = new Image(this.fileArry[i].toURI().toString());
        }
    }

    private void InitializeSetImages() {    //InitializeSetImages that sets the first load of pngs in the beginning of the game.
        for (int i = 0; i < 3; ++i) {
            this.imageBlocksArry[i].setImage(this.pngsArry[12]);
        }
        for (int i = 3; i < 6; ++i) {
            this.imageBlocksArry[i].setImage(this.pngsArry[9]);
        }
        for (int i = 6; i < 9; ++i) {
            this.imageBlocksArry[i].setImage(this.pngsArry[8]);
        }
    }

    private void InitializeButtonImages() {     //Initialize pngs and files for the style of the buttons.
        File[] dollarPngFileArry = new File[3];
        dollarPngFileArry[0] = new File("/Users/ozen/Desktop/CASINO/src/main/resources/app/Assets/Slots/css/1dollar.png");
        dollarPngFileArry[1] = new File("/Users/ozen/Desktop/CASINO/src/main/resources/app/Assets/Slots/css/spin.png");
        dollarPngFileArry[2] = new File("/Users/ozen/Desktop/CASINO/src/main/resources/app/Assets/Slots/css/infoButton.png");

        Image[] buttonImageArry = new Image[3];
        for (int i = 0; i < 3; i++) {
            buttonImageArry[i] = new Image(dollarPngFileArry[i].toURI().toString());
        }

        ImageView[] buttonImageViewArry = new ImageView[3];
        buttonImageViewArry[0] = buttonImageView1;
        buttonImageViewArry[1] = buttonImageView2;
        buttonImageViewArry[2] = buttonImageView3;

        for (int i = 0; i < 3; i++) {
            buttonImageViewArry[i].setImage(buttonImageArry[1]);
        }
        this.button1ImageView.setImage(buttonImageArry[0]);
        this.infoButtonImage.setImage(buttonImageArry[2]);

    }

    public void initailizeBetHistoryTF() {  //Initailize the betHistoryTF to beginning prompt.
        this.textLabel.setText("Welcome to the Slote Mchine by CASINOROYALE.\nPlease press one of the buttons to spin the Slot Machine!");
    }

    public void balanceInitialize() { //Initailize the beginning balance and display it.
        this.balanceText.setText(String.valueOf("$ " + balanceAmount));
    }

    public void displayBalance() {//Initailize the beginning balance and display it.
        this.balanceText.setText(String.valueOf("$ " + balanceAmount));
    }

    public void displayMult() { //Display multiplier and winAmount.
        this.aTextLabel.setText("Multiplier: " + multiplier + "\n\n" + "You Won: " + winAmount);
    }

    public void displayTextArea() { //Display balance and display it.
        this.textLabel.setText("");
        this.textLabel.setText("Remaining Balance is: $" + balanceAmount);
    }

    private static int randomize() { //Randomize method that will return a value between 0-12.
        int max = 12;
        int min = 0;
        new Random();
        int a = min + (int) (Math.random() * (double) (max - min + 1));
        return a;
    }


    @FXML
    public void spinButton1Handler(ActionEvent actionEvent) {
        if (balanceAmount >= 3.0) {
            this.spin1();
            this.printPlayHistoryAmount();
            this.setMostRepeated();
            this.getMultiplier();
            this.win();
            //this.setWinAmount();
            this.displayBalance();
            this.displayTextArea();
            this.displayMult();

        } else {
            this.balanceText.setText("You Don't have enough credit!");
        }
    }

    @FXML
    public void spinButton2Handler(ActionEvent actionEvent) {
    }

    @FXML
    public void spinButton3Handler(ActionEvent actionEvent) {
    }


    public void setMostRepeated(){
        req = ""; freq = 0;
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



    }
    public void printPlayHistoryAmount() {  //printPlayHistoryAmount method that will set the String list for outcome of the pngs.
        String[] icons = {"WATERMELON", "GONCA", "BAR", "KING", "HORSESHOE", "SEVEN",
                "GOLD", "DRAGON", "PUMPKIN", "GUN", "KHALESI", "HOTDOG", "DIOMAND"};


        String[] imgStr1 = new String[13];  //String array that will hold the toString value of pngs for comparing.
        for (int i = 0; i < 13; i++) {
            imgStr1[i] = pngsArry[i].toString();
        }

        String[] imageBlocksCompare = new String[9];    //String array that will hold the toString value of Images that is set to the ImagesViews for comparing.
        for (int i = 0; i < 9; i++) {
            imageBlocksCompare[i] = this.imageBlocksArry[i].getImage().toString();
        }

        resultArrList.clear();  // Clear the resultArrList so past outcome of pngs will be deleted.
        for (int i = 0; i < imageBlocksCompare.length; i++) {   //Load resultArrList with outcome.
            for (int j = 0; j < 13; j++) {
                if (imageBlocksCompare[i].equals(imgStr1[j])) {
                    resultArrList.add(icons[j]);
                }
            }
        }
        //Put the outcome to the final String pngsOrder, so it can be used to print the current outcome if needed.
        String pngsOrder = resultArrList.get(0) + " " + resultArrList.get(1) + " " + resultArrList.get(2) +
                "\n" + resultArrList.get(3) + " " + resultArrList.get(4) + " " + resultArrList.get(5) +
                "\n" + resultArrList.get(6) + " " + resultArrList.get(7) + " " + resultArrList.get(8) + "\n";
        System.out.println(pngsOrder);
    }

    public void getMultiplier(){
        if (req == "WATERMELON" || req == "GONCA" || req == "BAR") {    //If most repeated png is one of the given string multiplier is set to 1.
            multiplier = 1;
        }
        if (req == "KING" || req == "HORSESHOE" || req == "SEVEN") {    //If most repeated png is one of the given string multiplier is set to 3.
            multiplier = 3;
        }

        if (req == "GOLD" || req == "DRAGON" || req == "PUMPKIN") {     //If most repeated png is one of the given string multiplier is set to 5.
            multiplier = 5;
        }

        if (req == "GUN" || req == "KHALESI" || req == "HOTDOG") {      //If most repeated png is one of the given string multiplier is set to 7.
            multiplier = 7;
        }
        if (req == "DIOMAND") {                                         //If most repeated png is one of the given string multiplier is set to 10.
            multiplier = 10;
        }

    }

    public void setWinAmount(){
        if (freq == 3) {    //if most repeated png count is 3, multiply it by 2.
            winAmount = multiplier * 2;
        }
        if (freq == 4) {    //if most repeated png count is 4, multiply it by 2.
            winAmount = multiplier * 3;
        }
        if (freq == 5) {     //if most repeated png count is 5, multiply it by 2.

            winAmount = multiplier * 5;
        }
        if (freq == 6) {     //if most repeated png count is 6, multiply it by 2.

            winAmount = multiplier * 5;
        } else {
            winAmount = 0;
        }

    }
    private double win() { //win method that will determine the most repeated png in the ImageView obejcts from the resultArrList.
            winAmount = 0;

        if (freq == 3) {    //if most repeated png count is 3, multiply it by 2.
            balanceAmount += multiplier * 2;
            winAmount += multiplier * 2;

        }
        if (freq == 4) {    //if most repeated png count is 4, multiply it by 2.
            balanceAmount += multiplier * 3;
            winAmount += multiplier * 3;
        }
        if (freq == 5) {     //if most repeated png count is 5, multiply it by 2.
            balanceAmount += multiplier * 5;
            winAmount += multiplier * 5;
        }
        if (freq == 6) {     //if most repeated png count is 6, multiply it by 2.
            balanceAmount += multiplier * 7;
            winAmount += multiplier * 7;
        } else {
            balanceAmount = balanceAmount;
            winAmount += 0;
        }

        System.out.println(freq + " " + req);                           //print out the most repeated png string and count.
        System.out.println("Multiplier is: " + multiplier);

        return winAmount;



    }


    private void spin1() {      //spin1 method that will assign the ImageView objects with randomize method and set new pngs to imageBlocks randomly.
        balanceAmount = balanceAmount-3;
        for (int i = 0; i < 3; ++i) {
            this.imageBlocksArry[i].setImage(this.pngsArry[randomize()]);
        }
        for (int i = 3; i < 6; ++i) {
            this.imageBlocksArry[i].setImage(this.pngsArry[randomize()]);
        }
        for (int i = 6; i < 9; ++i) {
            this.imageBlocksArry[i].setImage(this.pngsArry[randomize()]);
        }


    }

    private void spin2() {
//        balanceAmount -= 2.5;
//        for (int i = 0; i < 3; ++i) {
//            this.imageBlocks[i].setImage(this.pngs[randomize()]);
//        }
    }

    private void spin3() {
//        balanceAmount -= 5.0;
//        for (int i = 0; i < 3; ++i) {
//            this.imageBlocks[i].setImage(this.pngs[randomize()]);
//        }
    }

    @Deprecated
    private void playBlackJack(ActionEvent event) throws IOException {
        this.HomeController.playBlackJack(event);
    }

    @Deprecated
    private void playRoulette(ActionEvent event) throws IOException {
        this.HomeController.playRoulette(event);
    }

    @Deprecated
    private void homeDash(ActionEvent event) throws IOException {
        this.HomeController.homeDash(event);
    }

    @FXML
    public void infoButtonHandler(ActionEvent actionEvent) {    //infoButtonHandler method that will create a Alert object and let it display the rules for the slot machine game if user clicks the 'i' icon.
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
