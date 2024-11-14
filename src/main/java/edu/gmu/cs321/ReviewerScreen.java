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

public class ReviewerScreen extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Employee reviewer = new Reviewer("2", "steve");
        Form form1 = new Form("5", "11", "gob", "10/28/1999", LAWFUL);
        Form form2 = new Form("6", "24", "berry", "03/26/2002", ASYLUM);

        // Javafx
        BorderPane root = new BorderPane();
        TableView<Form> table = new TableView<>();

        TableColumn<Form, Integer> formIdColumn = new TableColumn<>("Form ID");
        formIdColumn.setCellValueFactory(new PropertyValueFactory<>("formId"));

        TableColumn<Form, Integer> aidColumn = new TableColumn<>("Applicant ID");
        aidColumn.setCellValueFactory(new PropertyValueFactory<>("aid"));

        TableColumn<Form, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Form, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        table.getColumns().add(formIdColumn);
        table.getColumns().add(aidColumn);
        table.getColumns().add(nameColumn);
        table.getColumns().add(statusColumn);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.getItems().add(form1);
        table.getItems().add(form2);

        root.setCenter(table);

        Button viewFormButton = new Button("View Form");
        Button submitButton = new Button("Submit");

        HBox hbox = new HBox(50);
        hbox.getChildren().addAll(viewFormButton, submitButton);
        hbox.setAlignment(Pos.CENTER);

        root.setBottom(hbox);

        Scene scene = new Scene(root, 500, 300);
        stage.setTitle("Reviewer Workflow Screen");
        stage.setScene(scene);
        stage.show();
    }
}