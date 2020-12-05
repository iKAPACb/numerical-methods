package ru.projects.methods.TASK_13_14;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/graph13_14.fxml"));

        primaryStage.setTitle("TASK_13_14");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}