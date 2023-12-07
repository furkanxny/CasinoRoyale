package app.casinoroyale.Controller.FirebaseControllers;

import app.casinoroyale.CSRApplication;
import app.casinoroyale.Controller.HomeController;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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
    private Button switchSecondaryViewButton;

    @FXML
    private Button writeButton;

    private boolean key;
    private final ObservableList<Person> listOfUsers = FXCollections.observableArrayList();
    private Person person;

    public ObservableList<Person> getListOfUsers() {
        return listOfUsers;
    }

    public PrimaryController(){
        this.homeController = new HomeController();
        this.stage = new Stage();
    }
    void initialize() {
        AccessDataView accessDataViewModel = new AccessDataView();
        nameTextField.textProperty().bindBidirectional(accessDataViewModel.userNameProperty());
        writeButton.disableProperty().bind(accessDataViewModel.isWritePossibleProperty().not());
    }


    @FXML
    void readButtonClicked(ActionEvent event) {
        readFirebase();
    }


    @FXML
    void writeButtonClicked(ActionEvent event) {
        addData();
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
                StringBuilder outputText = new StringBuilder();

                for (QueryDocumentSnapshot document : documents) {
                    String name = String.valueOf(document.getData().get("Name")); // Ensure field name matches
                    String email = String.valueOf(document.getData().get("email")); // Ensure field name matches
                    String password = String.valueOf(document.getData().get("password")); // Ensure field name matches
                    int age = document.getData().get("Age") != null ? ((Long) document.getData().get("Age")).intValue() : 0; // Handle null and cast to int
                    double balance = document.getData().get("balance") != null ? Double.parseDouble(document.getData().get("balance").toString()) : 0.0; // Handle null and parse to double

                    outputText.append(name).append(" , Age: ").append(age).append("\n").append(" , email:").append(email).append(" , password:").append(password).append(" , balance:").append(balance);
                    person = new Person();
                    listOfUsers.add(person);
                }

                outputTextArea.setText(outputText.toString());

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

        DocumentReference docRef = CSRApplication.fstore.collection("CasinoPersons").document(UUID.randomUUID().toString());
        Map<String, Object> data = new HashMap<>();
        data.put("Name", nameTextField.getText());
        data.put("Age", Integer.parseInt(ageTextField.getText()));
        data.put("email", emailTextField.getText());
        data.put("Password", passwordTextField.getText());
        data.put("Balance", Double.parseDouble(startingBalanceTextField.getText()));
        //asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);
    }

    public void signInButtonHandler(ActionEvent actionEvent) {
    }
}