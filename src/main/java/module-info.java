module com.example.demo4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.javadoc;
    requires java.desktop;

    opens com.example.demo4 to javafx.fxml;
    exports com.example.demo4;
}