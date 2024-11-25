package edu.gmu.cs321;

import java.lang.StringBuilder;
import java.net.URL;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class DataEntryScreenController implements Initializable{

    @FXML
    private ChoiceBox<Status> status;

    @FXML
    private DatePicker dob;

    @FXML
    private TextField aid;

    @FXML
    private TextField lastName;

    @FXML
    private TextField firstName;

    @FXML

    private Button submit;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        status.getItems().addAll(Status.values());
    }

    @FXML
    void onSubmitButtonClick(ActionEvent event) {

    }

    @FXML
    void onValidateButtonClick(ActionEvent event) {
        StringBuilder sb = new StringBuilder(firstName.getText());
        sb.append(" ");
        sb.append(lastName.getText());
        String current_name = sb.toString();
        String current_dob = dob.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.println(current_dob);

        Form session_form = new Form("1", aid.getText(), current_name, current_dob, status.getValue());

        System.out.println(session_form);
    }

}
