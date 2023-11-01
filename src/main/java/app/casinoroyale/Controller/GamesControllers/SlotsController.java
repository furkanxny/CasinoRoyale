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
    private Button spinButton1;
    @FXML
    private Button spinButton2;
    @FXML
    private Button spinButton3;
    @FXML
    private ImageView infoButtonImage;
    @FXML
    private ImageView button1ImageView;
    @FXML
    private Label balance;
    @FXML
    private Text balanceText;
    @FXML
    private TextArea textArea;
    @FXML
    private Label textLabel;
    @FXML
    private Label balance1;
    @FXML
    private MenuItem homeDash;
    private ImageView[] imageBlocks;
    Image[] pngs = new Image[8];
    File[] file = new File[7];
    private static double balanceAmount = 1000.0;
    int multiplier = 0;
    private HomeController HomeController;
    @FXML
    private Button infoButton;

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
        File dollarPngFile = new File("css/1dollar.png");
        File buttonPngFile = new File("css/spin.png");
        File infoPngFile = new File("css/infoButton.png");
        Image image1 = new Image(dollarPngFile.toURI().toString());
        Image image2 = new Image(buttonPngFile.toURI().toString());
        Image image3 = new Image(infoPngFile.toURI().toString());
        this.buttonImageView1.setImage(image2);
        this.buttonImageView2.setImage(image2);
        this.buttonImageView3.setImage(image2);
        this.button1ImageView.setImage(image1);
        this.infoButtonImage.setImage(image3);

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

    private void slotSound() {
    }
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
        if (this.pngs[0].toString() == this.imageBlocks[0].getImage().toString() || this.pngs[1].toString() == this.imageBlocks[0].getImage().toString() || this.pngs[2].toString() == this.imageBlocks[0].getImage().toString()) {
            this.multiplier = 1;
        }

        if (this.pngs[3].toString() == this.imageBlocks[0].getImage().toString() || this.pngs[4].toString() == this.imageBlocks[0].getImage().toString() || this.pngs[5].toString() == this.imageBlocks[0].getImage().toString()) {
            this.multiplier = 3;
        }

        if (this.imageBlocks[1].getImage().toString() == this.imageBlocks[2].getImage().toString() && this.pngs[0].toString() == this.imageBlocks[2].getImage().toString() || this.pngs[1].toString() == this.imageBlocks[2].getImage().toString() || this.pngs[2].toString() == this.imageBlocks[2].getImage().toString()) {
            this.multiplier = 1;
        }

        if ((this.imageBlocks[1].getImage().toString() != this.imageBlocks[2].getImage().toString() || this.pngs[3].toString() != this.imageBlocks[2].getImage().toString()) && this.pngs[4].toString() != this.imageBlocks[2].getImage().toString() && this.pngs[5].toString() != this.imageBlocks[2].getImage().toString()) {
            this.multiplier = 7;
        } else {
            this.multiplier = 3;
        }

        return (double)this.multiplier;
    }

    private double getResult() {
        String firstBlock = this.imageBlocks[0].getImage().toString();
        String secondBlock = this.imageBlocks[1].getImage().toString();
        String thirdBlock = this.imageBlocks[2].getImage().toString();
        if (firstBlock.equals(secondBlock) && secondBlock.equals(thirdBlock)) {
            balanceAmount += (double)(10 * this.multiplier);
        }

        if (firstBlock.equals(secondBlock) && !secondBlock.equals(thirdBlock)) {
            balanceAmount += (double)(3 * this.multiplier);
        }

        if (firstBlock.equals(thirdBlock) && !secondBlock.equals(thirdBlock)) {
            balanceAmount += (double)(3 * this.multiplier);
        }

        if (secondBlock.equals(thirdBlock) && !thirdBlock.equals(firstBlock)) {
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
