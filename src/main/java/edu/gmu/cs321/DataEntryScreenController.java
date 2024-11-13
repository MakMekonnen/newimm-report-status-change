package edu.gmu.cs321;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DataEntryScreenController {

    @FXML
    private ChoiceBox<?> choice_status;

    @FXML
    private DatePicker dob;

    @FXML
    private TextField field_AID;

    @FXML
    private TextField field_LastName;

    @FXML
    private TextField field_firstName;

    @FXML
    private Label label_title;

    @FXML
    void onSubmitButtonClick(ActionEvent event) {

    }

    @FXML
    void onValidateButtonClick(ActionEvent event) {
        label_title.setText("poop");
    }

}
