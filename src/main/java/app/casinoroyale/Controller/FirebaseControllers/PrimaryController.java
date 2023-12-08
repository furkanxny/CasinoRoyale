package app.casinoroyale.Controller.FirebaseControllers;

import app.casinoroyale.CSRApplication;
import app.casinoroyale.Controller.HomeController;
import app.casinoroyale.Controller.LoginController;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class PrimaryController {


    private app.casinoroyale.Controller.HomeController homeController;

    private Stage stage;
    @FXML
    private TextField ageTextField;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField emailTextField;

    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField startingBalanceTextField;


    @FXML
    private TextArea outputTextArea;

    @FXML
    private Button readButton;

    @FXML
    private Button registerButton;

    @FXML
    private Button loginViewButton;

    @FXML
    private Button writeButton;

    private boolean key;
    private final ObservableList<Person> listOfUsers = FXCollections.observableArrayList();
    private Person person;
    private Firestore firestore;
    private LoginController LG;
    private app.casinoroyale.Controller.LoginController loginController;

    private static String personEmail;

    public ObservableList<Person> getListOfUsers() {
        return listOfUsers;
    }

    public PrimaryController(){

        //LG = new LoginController();
        this.homeController = new HomeController();
        this.stage = new Stage();
        firestore = CSRApplication.fstore;

    }
    void initialize() {
        AccessDataView accessDataViewModel = new AccessDataView();
        nameTextField.textProperty().bindBidirectional(accessDataViewModel.userNameProperty());
        writeButton.disableProperty().bind(accessDataViewModel.isWritePossibleProperty().not());
    }



    @FXML
    void writeButtonClicked(ActionEvent event) throws IOException {
        addData();
        homeController.loginDash(event);
    }

    public boolean updateBalance(double newBalance) {
        // Reference to the specific document in the Firestore collection
        DocumentReference docRef = CSRApplication.fstore.collection("Persons")
                .document("46db3c1c-7abc-4f9d-b233-a2d93e6d73aa");

        // Prepare the update data
        Map<String, Object> updates = new HashMap<>();
        updates.put("Balance", newBalance);

        // Perform the update operation
        ApiFuture<WriteResult> writeResult = docRef.update(updates);
        try {
            // Block on response
            writeResult.get();
            return true; // Update successful
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false; // Update failed
        }
    }



    public boolean readFirebase()
    {
        key = false;

        //asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future =  CSRApplication.fstore.collection("Persons").get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents;
        try
        {
            documents = future.get().getDocuments();

            if(documents.size()>0)
            {
                System.out.println("Outing data from firabase database....");
                listOfUsers.clear();
                for (QueryDocumentSnapshot document : documents)
                {

                    System.out.println(document.getId() + " => " + document.getData().get("Name"));
                    person  = new Person(
                            String.valueOf(document.getData().get("Name")),
                            String.valueOf(document.getData().get("Email")),
                            String.valueOf(document.getData().get("Password")),
                            Integer.parseInt(document.getData().get("Age").toString()),
                            Double.parseDouble(document.getData().get("Balance").toString())

                    );
                    listOfUsers.add(person);

                }
            }
            else
            {
                System.out.println("No data");
            }
            key=true;

        }
        catch (InterruptedException | ExecutionException ex)
        {
            ex.printStackTrace();
        }
        return key;

    }

    public void addData() {

        DocumentReference docRef = CSRApplication.fstore.collection("Persons").document(UUID.randomUUID().toString());
        Map<String, Object> data = new HashMap<>();
        data.put("Name", nameTextField.getText());
        data.put("Age", Integer.parseInt(ageTextField.getText()));
        data.put("email", emailTextField.getText());
        data.put("Password", passwordTextField.getText());
        data.put("Balance", Double.parseDouble(startingBalanceTextField.getText()));
        //asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);
    }

    public void signInButtonHandler(ActionEvent actionEvent) throws IOException{
        homeController.loginDash(actionEvent);
    }
}