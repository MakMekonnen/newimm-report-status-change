module edu.gmu.cs321 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    

    requires org.controlsfx.controls;
    requires javafx.graphics;

    opens edu.gmu.cs321 to javafx.fxml;
    exports edu.gmu.cs321;
}