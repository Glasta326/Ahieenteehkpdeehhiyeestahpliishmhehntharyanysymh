package com.main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GraphicsSetup extends Application {
    public GraphicsSetup() {
    }

    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GraphicsSetup.class.getResource("resources/MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280.0D, 720.0D);
        primaryStage.setTitle("Animalopoly!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(new String[0]);
    }
}