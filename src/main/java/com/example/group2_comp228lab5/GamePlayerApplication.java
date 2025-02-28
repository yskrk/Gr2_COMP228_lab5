package com.example.group2_comp228lab5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GamePlayerApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GamePlayerApplication.class.getResource("gameplayer-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 726, 462);
        stage.setTitle("Game and Player registration");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}