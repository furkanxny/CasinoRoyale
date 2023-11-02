//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package app.casinoroyale.Controller.GamesControllers;

import app.casinoroyale.Controller.HomeController;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
//Furkan Uzun

public class SlotsController {
    @FXML
    private ImageView imageBlock1;
    @FXML
    private ImageView imageBlock2;
    @FXML
    private ImageView imageBlock3;
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
    private Label textLabel;
    @FXML
    private MenuItem homeDash;
    private ImageView[] imageBlocks;
    Image[] pngs = new Image[8];
    File[] file = new File[7];
    private static double balanceAmount = 1000.0;
    int multiplier = 0;
    private HomeController HomeController;

    @FXML
    public void initialize() {
        this.imageBlocks = new ImageView[]{this.imageBlock1, this.imageBlock2, this.imageBlock3};
        initializeImages();
        initailizeBetHistoryTF();
        InitializeSetImages();
        balanceInitialize();
        InitializeButtonImages();
    }

    private void initializeImages() {
        int i;
        for(i = 0; i < 7; ++i) {
            this.file[i] = new File("icons/" + String.valueOf(i) + ".png");
        }
        for(i = 0; i < 7; ++i) {
            this.pngs[i] = new Image(this.file[i].toURI().toString());
        }
    }

    private void InitializeSetImages() {
        for(int i = 0; i < 3; ++i) {
            this.imageBlocks[i].setImage(this.pngs[6]);
        }
    }

    private void InitializeButtonImages(){
        File[] dollarPngFileArry = new File[3];
        dollarPngFileArry[0] = new File("css/1dollar.png");
        dollarPngFileArry[1] = new File("css/spin.png");
        dollarPngFileArry[2] = new File("css/infoButton.png");

        Image[] buttonImageArry = new Image[3];
        buttonImageArry[0] = new Image(dollarPngFileArry[0].toURI().toString());
        buttonImageArry[1] = new Image(dollarPngFileArry[1].toURI().toString());
        buttonImageArry[2] = new Image(dollarPngFileArry[2].toURI().toString());

        ImageView[] buttonImageViewArry = new ImageView[3];
        buttonImageViewArry[0] = buttonImageView1;
        buttonImageViewArry[1] = buttonImageView2;
        buttonImageViewArry[2] = buttonImageView3;

        for(int i = 0; i < 3; i++) {buttonImageViewArry[i].setImage(buttonImageArry[1]);}
        this.button1ImageView.setImage(buttonImageArry[0]);
        this.infoButtonImage.setImage(buttonImageArry[2]);

    }

    public void initailizeBetHistoryTF() {
        this.textLabel.setText("Welcome to the Slote Mchine by CASINOROYALE.\nPlease press one of the buttons to spin the Slot Machine!");
    }

    public void balanceInitialize() {
        this.balanceText.setText(String.valueOf("$ " + balanceAmount));
    }

    public void displayBalance() {
        this.balanceText.setText(String.valueOf("$ " + balanceAmount));
    }

    public void displayTextArea() {
        this.textLabel.setText("");
        this.textLabel.setText("Remaining Balance is: $" + balanceAmount);
    }

    private static int randomize() {
        int max = 6;
        int min = 0;
        new Random();
        int a = min + (int)(Math.random() * (double)(max - min + 1));
        return a;
    }

//    private void slotSound() {
//    }

private void winHistory(){
    String firstBlock = this.imageBlocks[0].getImage().toString();
    String secondBlock = this.imageBlocks[1].getImage().toString();
    String thirdBlock = this.imageBlocks[2].getImage().toString();
    if (firstBlock.equals(secondBlock) || secondBlock.equals(thirdBlock) || firstBlock.equals(thirdBlock)) {
        StringBuffer stringBuffer = new StringBuffer();
    }
}
    @FXML
    public void spinButton1Handler(ActionEvent actionEvent) {
   // playSound();
        if (balanceAmount >= 1.0) {
            this.spin1();
            this.getMultiplier();
            this.getResult();
            this.displayBalance();
            this.displayTextArea();
        } else {
            this.balanceText.setText("You Don't have enough credit!");
        }

    }

    @FXML
    public void spinButton2Handler(ActionEvent actionEvent) {
//        if (balanceAmount >= 2.5) {
//            this.spin2();
//            this.getMultiplier();
//            this.getResult();
//            this.displayBalance();
//            this.displayTextArea();
//        } else {
//            this.balanceText.setText("You Don't have enough credit!");
//        }

    }

    @FXML
    public void spinButton3Handler(ActionEvent actionEvent) {
//        if (balanceAmount >= 5.0) {
//            this.spin3();
//            this.getMultiplier();
//            this.getResult();
//            this.displayBalance();
//            this.displayTextArea();
//        } else {
//            this.balanceText.setText("You Don't have enough credit!");
//        }

    }



    private double getMultiplier() {
        String b1 = this.imageBlocks[0].getImage().toString();
        String b2 = this.imageBlocks[1].getImage().toString();
        String b3 = this.imageBlocks[2].getImage().toString();

        String img1 = pngs[0].toString();
        String img2 = pngs[1].toString();
        String img3 = pngs[2].toString();
        String img4 = pngs[3].toString();
        String img5 = pngs[4].toString();
        String img6 = pngs[5].toString();
        String img7 = pngs[6].toString();

        if(b1.equals(b2) && b2.equals(b3)){
            if (b1.equals(img1) || b1.equals(img2) || b1.equals(img3)){
                this.multiplier = 5;
            }
            if(b1.equals(img4)|| b1.equals(img5)|| b1.equals(img6)){
                this.multiplier = 7;
            }
            if(b1.equals(img7)){
                this.multiplier = 10;
            }
        }

        else if (b1.equals(b2) && !b1.equals(b3)){
            if (b1.equals(img1) || b1.equals(img2) || b1.equals(img3)){
                this.multiplier = 1;
            }
            if(b1.equals(img4)|| b1.equals(img5)|| b1.equals(img6)){
                this.multiplier = 3;
            }
            if(b1.equals(img7)){
                this.multiplier = 5;
            }
        }

        else if(b2.equals(b3) && !b1.equals(b3)){
            if (b2.equals(img1) || b2.equals(img2) || b2.equals(img3)){
                this.multiplier = 1;
            }
            if(b2.equals(img4)|| b2.equals(img5)|| b2.equals(img6)){
                this.multiplier = 3;
            }
            if(b2.equals(img7)){
                this.multiplier = 5;
            }
        }


        else if(b1.equals(b3) && !b1.equals(b2)){
            if (b1.equals(img1) || b1.equals(img2) || b1.equals(img3)){
                this.multiplier = 1;
            }
            if(b1.equals(img4)|| b1.equals(img5)|| b1.equals(img6)){
                this.multiplier = 3;
            }
            if(b1.equals(img7)){
                this.multiplier = 5;
            }}
                else {multiplier = 1;}

            return multiplier;
    }

    private double getResult() {
        String block1 = this.imageBlocks[0].getImage().toString();
        String block2 = this.imageBlocks[1].getImage().toString();
        String block3 = this.imageBlocks[2].getImage().toString();
        if (block1.equals(block2) && block2.equals(block3)) {
            balanceAmount += (double)(10 * this.multiplier);
        }

        if (block1.equals(block2) && !block2.equals(block3)) {
            balanceAmount += (double)(3 * this.multiplier);
        }

        if (block1.equals(block3) && !block2.equals(block3)) {
            balanceAmount += (double)(3 * this.multiplier);
        }

        if (block2.equals(block3) && !block3.equals(block1)) {
            balanceAmount += (double)(3 * this.multiplier);
        } else {
            balanceAmount = balanceAmount;
        }

        return balanceAmount;
    }

    private void spin1() {
        --balanceAmount;
        for(int i = 0; i < 3; ++i) {
            this.imageBlocks[i].setImage(this.pngs[randomize()]);
        }
    }

    private void spin2() {
        balanceAmount -= 2.5;
        for(int i = 0; i < 3; ++i) {
            this.imageBlocks[i].setImage(this.pngs[randomize()]);
        }
    }

    private void spin3() {
        balanceAmount -= 5.0;
        for(int i = 0; i < 3; ++i) {
            this.imageBlocks[i].setImage(this.pngs[randomize()]);
        }
    }

    @Deprecated
    private void playBlackJack(ActionEvent event) throws IOException {
        this.HomeController.playBlackJack(event);
    }

    @Deprecated
    private void playRoulette(ActionEvent event) throws IOException {
        this.HomeController.playRoulette(event);
    }

//    @FXML
//    private void playHorseRacing(ActionEvent event) throws IOException {
//        this.HomeController.playHorseRacing(event);
//    }

    @Deprecated
    private void homeDash(ActionEvent event) throws IOException {
        this.HomeController.homeDash(event);
    }

    @FXML
    public void infoButtonHandler(ActionEvent actionEvent) {
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
                "\n                     Diomand - $15" );
        alert.showAndWait();
    }
}
