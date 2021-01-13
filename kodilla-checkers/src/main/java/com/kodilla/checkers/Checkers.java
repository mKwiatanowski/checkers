package com.kodilla.checkers;


import com.kodilla.checkers.gui.Background;
import javafx.application.Application;
import javafx.stage.Stage;

public class Checkers extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        new Background(primaryStage);

    }
}
