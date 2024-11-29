package edu.gmu.cs321;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

import static edu.gmu.cs321.Status.ASYLUM;
import static edu.gmu.cs321.Status.LAWFUL;
import static edu.gmu.cs321.Test.formIDToForm;
import static edu.gmu.cs321.Test.workflow;

public class ReviewerScreen extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Employee reviewer = new Reviewer("2", "steve");
        Form form1 = new Form("11", "gob", "10/28/1999", LAWFUL);
        Form form2 = new Form("24", "berry", "03/26/2002", ASYLUM);

        // Javafx
        BorderPane root = new BorderPane();
        TableView<Form> table = new TableView<>();

        TableColumn<Form, Integer> formIdColumn = new TableColumn<>("Form ID");
        formIdColumn.setCellValueFactory(new PropertyValueFactory<>("formId"));

        TableColumn<Form, Integer> aidColumn = new TableColumn<>("Applicant ID");
        aidColumn.setCellValueFactory(new PropertyValueFactory<>("aid"));

        TableColumn<Form, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Form, String> dobColumn = new TableColumn<>("Date of Birth");
        dobColumn.setCellValueFactory(new PropertyValueFactory<>("dob"));

        TableColumn<Form, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        table.getColumns().add(formIdColumn);
        table.getColumns().add(aidColumn);
        table.getColumns().add(nameColumn);
        table.getColumns().add(dobColumn);
        table.getColumns().add(statusColumn);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.getItems().add(form1);
        table.getItems().add(form2);

        // populate table with forms
        refresh(table);

        root.setCenter(table);

        Button refreshButton = new Button("Refesh");
        Button denyButton = new Button("Deny");
        Button approveButton = new Button("Approve");

        refreshButton.setMinWidth(100);
        denyButton.setMinWidth(100);
        approveButton.setMinWidth(100);

        HBox hbox = new HBox(50);
        hbox.getChildren().addAll(refreshButton, denyButton, approveButton);
        hbox.setAlignment(Pos.CENTER);

        root.setBottom(hbox);

        refreshButton.setOnAction(e -> {
            refresh(table);
            System.out.println("refreshing");
        });

        approveButton.setOnAction(e -> {
            Form row = table.getSelectionModel().getSelectedItem();
            if (row != null) {
                workflow.AddWFItem(row.getFormId(), "Approve");
            }
        });

        denyButton.setOnAction(e -> {
            Form selectedForm = table.getSelectionModel().getSelectedItem();
        });

        Scene scene = new Scene(root, 500, 300);
        stage.setTitle("Reviewer Workflow Screen");
        stage.setScene(scene);
        stage.show();
    }

    public void refresh(TableView<Form> table) {
        int id = 0;
        System.out.println(workflow.GetNextWFItem("Review"));
        while ((id = workflow.GetNextWFItem("Review")) > 0) {
            System.out.println(id);
            table.getItems().add(formIDToForm.get(id));
        }
    }

}