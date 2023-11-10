package app.casinoroyale.Controller.GamesControllers;
import app.casinoroyale.Controller.HomeController;

import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.*;

import app.casinoroyale.Model.DataModels.GameModels.SlotsModel.Bet;
import app.casinoroyale.Model.DataModels.GameModels.SlotsModel.SlotGame;
import app.casinoroyale.Model.DataModels.GameModels.SlotsModel.SlotGame;
import app.casinoroyale.Model.DataModels.GameModels.SlotsModel.SlotGameView;
import app.casinoroyale.Model.DataModels.UserModels.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public class SlotsController {
    @FXML
    private ImageView imageBlock1, imageBlock2, imageBlock3, imageBlock4, imageBlock5, imageBlock6, imageBlock7, imageBlock8, imageBlock9,
            buttonImageView1, buttonImageView2, buttonImageView3, infoButtonImage, button1ImageView, slotsW;
    @FXML
    private ImageView i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20,
            i21, i22, i23, i24, i25, i26, i27, i28, i29, i30, i31, i32, i33, i34, i35;
    @FXML
    private Label aTextLabel, textLabel, historyTF, winText, welcomeLabel, lastBetLabel;
    @FXML
    private MenuItem homeDash;
    @FXML
    private Button button1$;
    private ImageView[] imageBlocksArry;    //an ImageView array that holds the 9 ImageView(ImageBlock) objects.
    Image[] pngsArry = new Image[15];       //an Image array that holds all the png images.
    ImageView[] jackpotArry = new ImageView[35];
    File[] fileArry = new File[14];         //an File array that holds the location of Images.
    private HomeController HomeController;
    ArrayList<String> resultArrList = new ArrayList<String>();  //resultArrList that hold the String list for outcome of the pngs.
    private Player player;
    private int executionCount = 0;
    private final int totalExecutions = 8;
    Bet bet1 = new Bet(-3);


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
    public void initialize() {
        player = Player.getInstance();
        this.imageBlocksArry = new ImageView[]{this.imageBlock1, this.imageBlock2, this.imageBlock3, this.imageBlock4,
                this.imageBlock5, this.imageBlock6, this.imageBlock7, this.imageBlock8, this.imageBlock9};
        this.jackpotArry = new ImageView[]{this.i1, this.i2, this.i3, this.i4, this.i5, this.i6, this.i7, this.i8, this.i9,
                this.i10, this.i11, this.i12, this.i13, this.i14, this.i15, this.i16, this.i17, this.i18, this.i19, this.i20,
                this.i21, this.i22, this.i23, this.i24, this.i25, this.i26, this.i27, this.i28, this.i29, this.i30, this.i31,
                this.i32, this.i33, this.i34, this.i35};
        this.initializeImages();
        initailizeBetHistoryTF();
        this.InitializeSetImages();
        InitializeButtonImages();
        setaTextLabelInitialiaze();
        initializeSlotsW();
        initializeJackpotImages();
        initializeWelcomeLabel();
        initializeLastBetLabel();
    }

    private void initializeJackpotImages(){
        for(int i = 0; i < 35; i++){
            jackpotArry[i].setImage(pngsArry[7]);
        }
    }

    private void initializeWelcomeLabel(){
        welcomeLabel.setText("   WELCOME \n          TO \n         THE \n        SLOT \n   MACHINE");
    }

    private void initializeSlotsW() {
        File sFile = new File("src/main/resources/app/Assets/Slots/css/slotsW.png");
        Image sImage = new Image(sFile.toURI().toString());
        slotsW.setImage(sImage);

    }

    private void initializeImages() {   //Initialize Images and Files.
        int i;
        for (i = 0; i < 14; ++i) {
            this.fileArry[i] = new File("src/main/resources/app/Assets/Slots/icons/" + i + ".png");
        }
        for (i = 0; i < 14; ++i) {
            this.pngsArry[i] = new Image(this.fileArry[i].toURI().toString());
        }
    }

    private void InitializeSetImages() {    //InitializeSetImages that sets the first load of pngs in the beginning of the game.
        for (int i = 0; i < 3; ++i) {
            this.imageBlocksArry[i].setImage(this.pngsArry[13]);
        }
        for (int i = 3; i < 6; ++i) {
            this.imageBlocksArry[i].setImage(this.pngsArry[13]);
        }
        for (int i = 6; i < 9; ++i) {
            this.imageBlocksArry[i].setImage(this.pngsArry[13]);
        }
    }

    private void InitializeButtonImages() {     //Initialize pngs and files for the style of the buttons.
        File[] dollarPngFileArry = new File[3];
        dollarPngFileArry[0] = new File("src/main/resources/app/Assets/Slots/css/1dollar.png");
        dollarPngFileArry[1] = new File("src/main/resources/app/Assets/Slots/css/spin.png");
        dollarPngFileArry[2] = new File("src/main/resources/app/Assets/Slots/css/infoButton.png");

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
        this.textLabel.setText("$$$$");
    }


    public void setaTextLabelInitialiaze() {
        this.aTextLabel.setText("$$$");
    }

    public void initializeLastBetLabel(){this.lastBetLabel.setText("$$");}

    @FXML
    public void spinButton1Handler(ActionEvent actionEvent) {
      spin();

    }



    public void spin(){
        welcomeLabel.setText("");
        lastBetLabel.setText("$3");
        for(int i =0; i < 1; i++){
        Player.getInstance().setAccountBalance(bet1.getAmount());
        SlotGameView.updateSlotsImages(imageBlocksArry, pngsArry);
        SlotGame.setResults(resultArrList, pngsArry, imageBlocksArry);
        SlotGameView.printResults(resultArrList, historyTF, winText);
        SlotGame.setMostRepeated(resultArrList);
        SlotGame.setMultiplier();
        SlotGame.setWinnings();
        SlotGame.displayWinAmount(aTextLabel);
        SlotGame.displayBalanceAmount(textLabel);
    }
    }

    @FXML
    public void spinButton2Handler(ActionEvent actionEvent) {
        welcomeLabel.setText("");
        lastBetLabel.setText("$6");
    }

    @FXML
    public void spinButton3Handler(ActionEvent actionEvent) {
    }

    //If users clicks infoButton button it will call the infoButtonHandler and the methods inside.
    @FXML
    public void infoButtonHandler(ActionEvent actionEvent) {
        SlotGameView.infoButton();
    }
}
