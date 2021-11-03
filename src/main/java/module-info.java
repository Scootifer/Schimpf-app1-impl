module ApplicationPackage {
    requires javafx.controls;
    requires javafx.fxml;


    opens ApplicationPackage to javafx.fxml;
    exports ApplicationPackage;
}