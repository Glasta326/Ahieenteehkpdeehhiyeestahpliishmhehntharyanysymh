module com.main.graphics {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.main to javafx.fxml;
    exports com.main;
}