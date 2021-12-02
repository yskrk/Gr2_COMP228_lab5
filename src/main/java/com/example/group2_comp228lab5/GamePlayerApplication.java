package com.example.group2_comp228lab5;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GamePlayerApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(GamePlayerApplication.class.getResource("game-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 450);
//        stage.setTitle("Game");
//        stage.setScene(scene);
//        stage.show();

        FXMLLoader fxmlLoader2 = new FXMLLoader(GamePlayerApplication.class.getResource("player-view.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load(), 320, 450);
        stage.setTitle("Player");
        stage.setScene(scene2);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}