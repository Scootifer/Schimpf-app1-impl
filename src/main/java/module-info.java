module ApplicationPackage {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;


    opens ApplicationPackage to javafx.fxml;
    exports ApplicationPackage;
}