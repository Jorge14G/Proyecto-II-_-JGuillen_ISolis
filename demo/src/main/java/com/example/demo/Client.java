package com.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Client extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox();
        Scene canvas = new Scene(root, 500,500);
        stage.setScene(canvas);
        stage.show();
    }
}
