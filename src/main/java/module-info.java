module com.example.ssopfa {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ssopfa.entities to javafx.base;
    opens com.example.ssopfa to javafx.fxml;
    exports com.example.ssopfa;
    exports com.example.ssopfa.controllers;
    opens com.example.ssopfa.controllers to javafx.fxml;
}
