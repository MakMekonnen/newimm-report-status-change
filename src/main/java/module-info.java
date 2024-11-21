module edu.gmu.cs321 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    

    requires org.controlsfx.controls;

    opens edu.gmu.cs321 to javafx.fxml;
    exports edu.gmu.cs321;
}