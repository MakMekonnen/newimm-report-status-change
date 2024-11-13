package edu.gmu.cs321;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DataEntryScreen extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Data Entry");
        stage.setScene(scene);
        stage.show();
    }
}