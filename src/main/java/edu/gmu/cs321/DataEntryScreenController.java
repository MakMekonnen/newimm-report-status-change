package edu.gmu.cs321;

import java.lang.StringBuilder;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;

import static edu.gmu.cs321.Test.formIDToForm;
import static edu.gmu.cs321.Test.workflow;

public class DataEntryScreenController implements Initializable{

    @FXML
    private ChoiceBox<Status> status;

    @FXML
    private DatePicker dob;

    @FXML
    private Label labelAID;

    @FXML
    private Label labelDOB;

    @FXML
    private Label labelFirstName;

    @FXML
    private Label labelLastName;

    @FXML
    private Label labelStatus;

    @FXML
    private TextField aid;

    @FXML
    private TextField lastName;

    @FXML
    private TextField firstName;

    private DataEntry de;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        status.getItems().addAll(Status.values());
        de = new DataEntry("123456", "Adrian Rios");
    }

    @FXML
    void onSubmitButtonClick(ActionEvent event) {

    }

    @FXML
    void onValidateButtonClick(ActionEvent event) {
        int errors = 0;

        String fname = firstName.getText();
        if(!Validation.checkNameFormat(fname)){
            labelFirstName.setTextFill(Color.RED);
            errors++;
        }else{
            labelFirstName.setTextFill(Color.BLACK);
        }

        String lname = lastName.getText();
        if(!Validation.checkNameFormat(lname)){
            labelLastName.setTextFill(Color.RED);
            errors++;
        }else{
            labelLastName.setTextFill(Color.BLACK);
        }

        String current_dob = null;
        if(dob.getValue() == null){
            labelDOB.setTextFill(Color.RED);
            errors++;
        }else{
            labelDOB.setTextFill(Color.BLACK);
            current_dob = dob.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        }
        if(!Validation.checkDateFormat(current_dob)){
            labelDOB.setTextFill(Color.RED);
            errors++;
        }else{
            labelDOB.setTextFill(Color.BLACK);
        }

        String current_aid = aid.getText();
        if(!Validation.checkAIDFormat(current_aid)){
            labelAID.setTextFill(Color.RED);
            errors++;
        }else{
            labelAID.setTextFill(Color.BLACK);
        }

        Status current_status = status.getValue();
        if(current_status == null){
            labelStatus.setTextFill(Color.RED);
            errors++;
        }else{
            labelStatus.setTextFill(Color.BLACK);
        }

        if(errors > 0){
            return;
        }
        StringBuilder sb = new StringBuilder(fname);
        sb.append(" ");
        sb.append(lname);
        String current_name = sb.toString();

        Form session_form = new Form(current_aid, current_name, current_dob, current_status);
        session_form.setState(State.REVIEWER_STATE);
        formIDToForm.put(session_form.getFormId(), session_form);
        int result = Test.workflow.AddWFItem(session_form.getFormId(), session_form.getState().getValue());

        Alert alert = new Alert(AlertType.INFORMATION);
        if(result == 0){
            alert.setHeaderText("Submitted!");
            alert.setContentText("Form has been validated and submitted into the workflow!");
            if(alert.showAndWait().get() == ButtonType.OK){
                clearAll();
            }
        }else if (result == -1){
            alert.setHeaderText("Unable to submit!");
            alert.setContentText("The form has an invalid state!");
            if(alert.showAndWait().get() == ButtonType.OK){
                clearAll();
            }
        }else{
            alert.setHeaderText("Unable to submit!");
            alert.setContentText("The form already exists in the workflow!");
            if(alert.showAndWait().get() == ButtonType.OK){
                clearAll();
            }
        }

        
    }
    public void clearAll(){
        firstName.clear();
        lastName.clear();
        dob.setValue(null);
        aid.clear();
        status.setValue(null);
    }
}
