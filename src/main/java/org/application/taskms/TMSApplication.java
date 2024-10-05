package org.application.taskms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class TMSApplication extends Application {
    static Stage stage = null;

    @Override
    public void start(Stage stage) throws IOException {
        TMSApplication.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(TMSApplication.class.getResource("application.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 400);
        stage.setTitle("Task Management System");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        stage.getIcons().add(new Image(TMSApplication.class.getResourceAsStream("appIcon.png")));
        stage.setOpacity(0.99);
    }

    public static void main(String[] args) {
        launch();
    }
}