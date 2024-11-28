package edu.gmu.cs321;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ApproverScreen extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        // Template Form
        Form form = new Form("A24", "John Berry", "03/26/2002", Status.ASYLUM);

        // Main layout
        BorderPane root = new BorderPane();

        // Back Button
        Button backButton = new Button("Back");
        HBox topLeft = new HBox(backButton);
        topLeft.setAlignment(Pos.TOP_LEFT);
        root.setTop(topLeft);
        BorderPane.setMargin(topLeft, new Insets(10, 0, 0, 15));

        // Main Layout
        GridPane formGrid = createFormGrid(form);
        formGrid.setAlignment(Pos.CENTER);
        root.setCenter(formGrid);

        // Approve and Deny Buttons
        HBox bottomButtons = new HBox(10);
        Button approveButton = new Button("Approve");
        Button denyButton = new Button("Deny");
        bottomButtons.getChildren().addAll(approveButton, denyButton);
        bottomButtons.setAlignment(Pos.CENTER);
        bottomButtons.setPadding(new Insets(10, 0, 10, 0));
        root.setBottom(bottomButtons);

        // Set Actions
        approveButton.setOnAction(event -> showConfirmationScreen(stage, form.getName(), "Approve"));
        denyButton.setOnAction(event -> showConfirmationScreen(stage, form.getName(), "Deny"));

        // Set up scene and stage
        Scene scene = new Scene(root, 640, 480);
        stage.setTitle("Approver Screen");
        stage.setScene(scene);
        stage.show();
    }

    // Helper method to create the form grid with labels and text fields
    private GridPane createFormGrid(Form form) {
        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setPadding(new Insets(20));

        // Labels and text fields for form fields
        Label formIdLabel = new Label("Form ID: ");
        TextField formIdField = new TextField("" + form.getFormId());
        formIdLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 15px");
        formIdField.setStyle("-fx-font-size: 12px");
        formIdField.setEditable(false);

        Label aidLabel = new Label("Alien Registration Number: ");
        TextField aidField = new TextField(form.getAid());
        aidLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 15px");
        aidField.setStyle("-fx-font-size: 12px");
        aidField.setEditable(false);

        Label nameLabel = new Label("Name: ");
        TextField nameField = new TextField(form.getName());
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 15px");
        nameField.setStyle("-fx-font-size: 12px");
        nameField.setEditable(false);

        Label dobLabel = new Label("Date of Birth: ");
        TextField dobField = new TextField(form.getDob());
        dobLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 15px");
        dobField.setStyle("-fx-font-size: 12px");
        dobField.setEditable(false);

        Label statusLabel = new Label("Immigration Status: ");
        TextField statusField = new TextField(form.getStatus().toString());
        statusLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 15px");
        statusField.setStyle("-fx-font-size: 12px");
        statusField.setEditable(false);

        // Add labels and fields to the grid
        grid.add(formIdLabel, 0, 0);
        grid.add(formIdField, 1, 0);
        grid.add(aidLabel, 0, 1);
        grid.add(aidField, 1, 1);
        grid.add(nameLabel, 0, 2);
        grid.add(nameField, 1, 2);
        grid.add(dobLabel, 0, 3);
        grid.add(dobField, 1, 3);
        grid.add(statusLabel, 0, 4);
        grid.add(statusField, 1, 4);

        return grid;
    }

    // Method for the Confirmation Screen for both Approve and Deny
    private void showConfirmationScreen(Stage parentStage, String name, String action) {
        Stage confirmationStage = new Stage();
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        Label message = new Label("Are you sure you want to " + action + " the status change of " + name + "?");
        message.setStyle("-fx-font-size: 14px;");

        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        // Success Message for either Approve or Deny based on action variable
        yesButton.setOnAction(event -> {
            showFinalScreen(action.equals("Approve") ? "Approval Sent." : "Denial Sent.");
            confirmationStage.close();
        });

        noButton.setOnAction(event -> confirmationStage.close());

        HBox buttonBox = new HBox(10, yesButton, noButton);
        buttonBox.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(message, buttonBox);

        Scene scene = new Scene(layout, 600, 200);
        confirmationStage.setTitle(action + " Confirmation");
        confirmationStage.setScene(scene);
        confirmationStage.show();
    }

    // Final Screen with Success Message
    private void showFinalScreen(String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Result");
    alert.setHeaderText("Notice!"); 
    alert.setContentText(message); 
    alert.showAndWait(); 
    }
}
