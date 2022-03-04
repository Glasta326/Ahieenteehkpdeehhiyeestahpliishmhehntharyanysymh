package com.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.io.IOException;
import javafx.stage.Stage;

public class GraphicsHandler {
    public GraphicsHandler() {
    }
    @FXML
    private Label welcomeText;


    @FXML
    protected void onHelloButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(GraphicsHandler.class.getResource("resources/PlayerSelect.fxml"));
        Scene scene = new Scene((Parent)fxmlLoader.load(), 1120.0D, 630.0D);
        stage.setScene(scene);

    }
}