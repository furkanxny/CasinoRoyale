package app.casinoroyale.Controller.GamesControllers;
import app.casinoroyale.Controller.HomeController;
import java.io.File;
import java.io.IOException;
import java.util.*;
import app.casinoroyale.Model.DataModels.GameModels.SlotsModel.*;
import app.casinoroyale.Model.DataModels.GameModels.SlotsModel.SlotGame;
import app.casinoroyale.Model.DataModels.UserModels.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SlotsController {
    @FXML
    private ImageView imageBlock1, imageBlock2, imageBlock3, imageBlock4, imageBlock5, imageBlock6, imageBlock7, imageBlock8, imageBlock9,
            buttonImageView1, infoButtonImage, jackpotImageView, exitButtonImage;
    @FXML
    private ImageView i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20,
            i21, i22, i23, i24, i25, i26, i27, i28, i29, i30, i31, i32, i33, i34, i35;
    @FXML
    private Label aTextLabel, textLabel, historyTF, winText, welcomeLabel, lastBetLabel;
    @FXML
    private MenuItem homeDash;
    @FXML
    private Button button1$;
    private ImageView[] imageBlocksArry;
    Image[] pngsArry = new Image[15];
    ImageView[] jackpotArry = new ImageView[35];
    File[] fileArry = new File[14];
    private HomeController HomeController;
    ArrayList<String> resultArrList = new ArrayList<String>();
    private Player player;
    Bet bet1 = new Bet(-3);

    int spinTestCounter = 1;

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
        this.imageBlocksArry = new ImageView[]{this.imageBlock1, this.imageBlock2, this.imageBlock3, this.imageBlock4, this.imageBlock5, this.imageBlock6, this.imageBlock7, this.imageBlock8, this.imageBlock9};
                this.jackpotArry = new ImageView[]{this.i1, this.i2, this.i3, this.i4, this.i5, this.i6, this.i7, this.i8, this.i9,
                this.i10, this.i11, this.i12, this.i13, this.i14, this.i15, this.i16, this.i17, this.i18, this.i19, this.i20,
                this.i21, this.i22, this.i23, this.i24, this.i25, this.i26, this.i27, this.i28, this.i29, this.i30, this.i31,
                this.i32, this.i33, this.i34, this.i35};
        initializeImages();
        initailizeBetHistoryTF();
        InitializeSetImages();
        InitializeButtonImages();
        setaTextLabelInitialiaze();
        initializeSlotsW();
        initializeJackpotImages();
        initializeWelcomeLabel();
        initializeLastBetLabel();
    }



    public void initailizeBetHistoryTF() {  //Initailize the betHistoryTF to beginning prompt.
        this.textLabel.setText("$$$$");
    }

    public void setaTextLabelInitialiaze() {
        this.aTextLabel.setText("$$$");
    }

    public void initializeLastBetLabel() {
        this.lastBetLabel.setText("$$");
    }
    private void initializeWelcomeLabel() {
        welcomeLabel.setText("   WELCOME \n          TO \n         THE \n        SLOT \n   MACHINE");
    }

    private void initializeSlotsW() {
        File sFile = new File("src/main/resources/app/Assets/Slots/css/slotsW.png");
        Image sImage = new Image(sFile.toURI().toString());
        jackpotImageView.setImage(sImage);
    }
    private void initializeJackpotImages() {
        for (int i = 0; i < 35; i++) {
            jackpotArry[i].setImage(pngsArry[7]);
        }}

    private void initializeImages() {
        int i;
        for (i = 0; i < 14; ++i) {
            this.fileArry[i] = new File("src/main/resources/app/Assets/Slots/icons/" + i + ".png");
        }
        for (i = 0; i < 14; ++i) {
            this.pngsArry[i] = new Image(this.fileArry[i].toURI().toString());
        }
    }

    private void InitializeSetImages() {
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

    private void InitializeButtonImages() {
        File[] dollarPngFileArry = new File[4];
        dollarPngFileArry[0] = new File("src/main/resources/app/Assets/Slots/css/1dollar.png");
        dollarPngFileArry[1] = new File("src/main/resources/app/Assets/Slots/css/spin.png");
        dollarPngFileArry[2] = new File("src/main/resources/app/Assets/Slots/css/infoButton.png");
        dollarPngFileArry[3] = new File("src/main/resources/app/Assets/Slots/css/exitButton.png");

        Image infoImage = new Image(dollarPngFileArry[2].toURI().toString());
        Image exitImage = new Image(dollarPngFileArry[3].toURI().toString());
        Image spinImage = new Image(dollarPngFileArry[1].toURI().toString());


        infoButtonImage.setImage(infoImage);
        exitButtonImage.setImage(exitImage);
        buttonImageView1.setImage(spinImage);

        ImageView[] buttonImageViewArry = new ImageView[3];
        buttonImageViewArry[0] = buttonImageView1;
    }

    @FXML
    public void spinButton(ActionEvent actionEvent) throws InterruptedException {
        spin();
        attrb();
    }


    public void attrb(){
        Player.getInstance().setAccountBalance(bet1.getAmount());
        lastBetLabel.setText("$3");
        welcomeLabel.setText("");
    }

    public void spin() {
        for (int i = 0; i < spinTestCounter; i++) {
            SlotGameView.spin(imageBlocksArry, pngsArry);
            SlotGame.setResults(resultArrList, pngsArry, imageBlocksArry);
            SlotGameView.printResults(resultArrList, historyTF, winText);
            SlotGame.setMostRepeated(resultArrList);
            SlotGame.setMultiplier();
            SlotGame.setWinnings();
            SlotGame.displayWinAmount(aTextLabel);
            SlotGame.displayBalanceAmount(textLabel);
            SlotGame.setCounter();
        }
    }

    @FXML
    public void infoButtonHandler(ActionEvent actionEvent) {
        SlotGameView.infoButton();
    }
    @FXML
    void exitButtonHandler(ActionEvent event) {
        SlotGameView.exitButton();
    }
}
