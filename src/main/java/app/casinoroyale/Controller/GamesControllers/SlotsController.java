package app.casinoroyale.Controller.GamesControllers;
import app.casinoroyale.Controller.HomeController;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import app.casinoroyale.Model.DataModels.UserModels.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import static app.casinoroyale.Controller.GamesControllers.SlotGameLogic.setResults;

public class SlotsController {
    @FXML
    private ImageView imageBlock1, imageBlock2, imageBlock3, imageBlock4, imageBlock5, imageBlock6, imageBlock7, imageBlock8, imageBlock9,
            buttonImageView1, buttonImageView2, buttonImageView3, infoButtonImage, button1ImageView, slotsW;
    @FXML
    private Label aTextLabel, textLabel, historyTF;
    @FXML
    private MenuItem homeDash;
    @FXML
    private Button button1$;
    private ImageView[] imageBlocksArry;    //an ImageView array that holds the 9 ImageView(ImageBlock) objects.
    Image[] pngsArry = new Image[15];       //an Image array that holds all the png images.
    File[] fileArry = new File[14];         //an File array that holds the location of Images.
    private HomeController HomeController;
    ArrayList<String> resultArrList = new ArrayList<String>();  //resultArrList that hold the String list for outcome of the pngs.
    private Player player;
    private int executionCount = 0;
    private final int totalExecutions = 8;





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
        this.initializeImages();
        initailizeBetHistoryTF();
        this.InitializeSetImages();
        InitializeButtonImages();
        setaTextLabelInitialiaze();
        initializeSlotsW();
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
        this.textLabel.setText("$$$$");
    }


    public void setaTextLabelInitialiaze() {
        this.aTextLabel.setText("$$$");
    }


    @FXML
    public void spinButton1Handler(ActionEvent actionEvent) {
      spin();

    }



    public void spin(){
        SlotGameView.updateSlotsImages(imageBlocksArry, pngsArry);
        SlotGameLogic.setResults(resultArrList, pngsArry, imageBlocksArry);
        SlotGameView.printResults(resultArrList, historyTF);
        SlotGameLogic.setMostRepeated(resultArrList);
        SlotGameLogic.setMultiplier();
        SlotGameLogic.setWinnings();
        SlotGameLogic.displayWinAmount(aTextLabel);
        SlotGameLogic.displayBalanceAmount(textLabel);
    }

    @FXML
    public void spinButton2Handler(ActionEvent actionEvent) {
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
