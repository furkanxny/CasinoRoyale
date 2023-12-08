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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
interface regexMatcher {
    String regexUserName = "\b[A-Z][a-zA-Z]+";
    String regexEmail = "[a-z0-9]+@[a-z0-9]+.[0-z]{2,6}";
    String regexPassword = "^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

}
public class PrimaryController implements regexMatcher{
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
    void writeButtonClicked(ActionEvent event) throws IOException {
        addData();
        homeController.loginDash(event);
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
        String name = nameTextField.getText();
        int age = Integer.parseInt(ageTextField.getText());
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        double balance = Double.parseDouble(startingBalanceTextField.getText());
        System.out.println(name + " " + age + " " + email + " "+ password + " " + balance);

        if (!name.matches(regexUserName) ||
                !password.matches(regexPassword) ||
                age < 18 ||
                !email.matches(regexEmail) || // regexEmail should be a string pattern for email validation
                balance >= 1000) {
            Alert requirementsAlert = new Alert(Alert.AlertType.ERROR);
            requirementsAlert.setTitle("REGISTER ERROR");
            requirementsAlert.setHeaderText("REGISTER ERROR");
            requirementsAlert.setContentText("You are missing at least one of the requirements");
            requirementsAlert.showAndWait();
            return;
        }

        DocumentReference docRef = CSRApplication.fstore.collection("Persons").document(UUID.randomUUID().toString());
        Map<String, Object> data = new HashMap<>();
        data.put("Name", nameTextField.getText());
        data.put("Age", Integer.parseInt(ageTextField.getText()));
        data.put("email", emailTextField.getText());
        data.put("Password", passwordTextField.getText());
        data.put("Balance", Double.parseDouble(startingBalanceTextField.getText()));
        ApiFuture<WriteResult> result = docRef.set(data);
//
//        try {
//        // Wait for the operation to complete
//        WriteResult writeResult = result.get();
//
//        // If success, get() will return without throwing an exception
//        System.out.println("Write successful with timestamp: " + writeResult.getUpdateTime());
//
//    } catch (InterruptedException e) {
//        // Handle if the thread was interrupted during get()
//        System.err.println("InterruptedException occurred: " + e.getMessage());
//        Thread.currentThread().interrupt();
//    } catch (ExecutionException e) {
//        // Handle if the write operation failed
//        System.err.println("Write failed: " + e.getMessage());
//    }
    }

    public void signInButtonHandler(ActionEvent actionEvent) throws IOException{
        homeController.loginDash(actionEvent);
    }
}