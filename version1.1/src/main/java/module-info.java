module com.mycompany.mokolasvol1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.mokolasvol1 to javafx.fxml;
    exports com.mycompany.mokolasvol1;
}
