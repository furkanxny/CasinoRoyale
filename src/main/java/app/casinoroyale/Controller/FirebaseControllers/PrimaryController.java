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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

interface regex{
    String regexUserName = "\\b[A-Z][a-zA-Z]+";
    String regexEmail = "[a-z0-9]+@[a-z0-9]+.[0-z]{2,6}";
    String regexPassword =
            "  ^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$\n";
}
public class PrimaryController implements regex{


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
    private RadioButton radioButton;

    boolean isSelected;

    boolean isExist;

    boolean isRegistered = false;

    @FXML
    private Button writeButton;

    private ArrayList<String> registeredEmailArryList = new ArrayList<>();

    private boolean key;
    private final ObservableList<Person> listOfUsers = FXCollections.observableArrayList();
    private Person person;
    private Firestore firestore;
    private LoginController LG;
    private app.casinoroyale.Controller.LoginController loginController;

    //private String[] emailArry = new String[200];

    private static String isEmail;
    private static String ID;

    public void setID(String ID){
        this.ID = ID;
    }
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
        if(isRegistered) {
            homeController.loginDash(event);
        }
    }

    public boolean updateBalance(double newBalance) {
        // Reference to the specific document in the Firestore collection
        DocumentReference docRef = CSRApplication.fstore.collection("Persons")
                .document(ID);

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
                    registeredEmailArryList.add((String) document.getData().get("email"));


                    System.out.println(document.getId() + " => " + document.getData().get("Name"));

                    person  = new Person(
                            String.valueOf(document.getData().get("Name")),
                            String.valueOf(document.getData().get("email")),
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
        for(int i = 0; i < registeredEmailArryList.size(); i++){
        System.out.println(registeredEmailArryList.get(i));}
        return key;



    }

    public void addData() {
        readFirebase();
        isSelected = radioButton.isSelected();
        isExist = true;

for(int i = 0; i < registeredEmailArryList.size(); i++){
            if(registeredEmailArryList.get(i).equals(emailTextField.getText())){
                isExist = false;
        }}

        if(nameTextField.getText().matches(regexUserName) && Double.valueOf(ageTextField.getText()) >= 18 &&
                emailTextField.getText().matches(regexEmail) && isSelected && !passwordTextField.getText().isEmpty() &&
                !startingBalanceTextField.getText().isEmpty() && isExist) {
            DocumentReference docRef = CSRApplication.fstore.collection("Persons").document(UUID.randomUUID().toString());
            Map<String, Object> data = new HashMap<>();
            data.put("Name", nameTextField.getText());
            data.put("Age", Integer.parseInt(ageTextField.getText()));
            data.put("email", emailTextField.getText());
            data.put("Password", passwordTextField.getText());
            data.put("Balance", Double.parseDouble(startingBalanceTextField.getText()));
            ApiFuture<WriteResult> result = docRef.set(data);
            System.out.println("User registration is successful");
            isRegistered = true;


        }
         else{
            Alert requirementsAlert = new Alert(Alert.AlertType.ERROR);
            requirementsAlert.setTitle("REGISTER ERROR");
            requirementsAlert.setHeaderText("                                REGISTER ERROR");
            ArrayList<String> errorMessages = new ArrayList<>();


            if(nameTextField.getText().matches(regexUserName) == false){
                errorMessages.add("Invalid or Empty Name!!");
            }
            if(Double.valueOf(ageTextField.getText()) >= 18 == false ){
                errorMessages.add("You Have To Be Older Than 18!!");
            }
            if(emailTextField.getText().matches(regexEmail) == false){
                errorMessages.add("Invalid Email Or Empty Email. Exp: example@domain.com");
            }
            if(passwordTextField.getText().isEmpty() || startingBalanceTextField.getText().isEmpty()){
                errorMessages.add("Password And Balance Can't Be Empty!!");
            }
            if(!isExist){
                errorMessages.add("This Email Already Exist, Please Enter A Different One!!");
            }
            if(!isSelected){
                errorMessages.add("Please Accept The Terms Of Use And Privacy Policy.");
            }
            StringBuffer strBfr = new StringBuffer();

            Iterator<String> iterator1 = errorMessages.iterator();

            while (iterator1.hasNext()) {
                strBfr.append(iterator1.next() + "\n");
            }

            requirementsAlert.setContentText(strBfr.toString());
            requirementsAlert.showAndWait();


        }

    }


    public void signInButtonHandler(ActionEvent actionEvent) throws IOException{
        homeController.loginDash(actionEvent);
    }
}