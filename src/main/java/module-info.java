module org.application.taskms {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens org.application.taskms to javafx.fxml;
    exports org.application.taskms;

    opens org.application.core to javafx.fxml;
    exports org.application.core;
}