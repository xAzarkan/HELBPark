package com.example.helbpark;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private final int MAIN_WINDOW_HEIGHT = 600;
    private final int MAIN_WINDOW_WIDTH = 750;
    private final String WINDOW_TITLE = "HELBPark";

    @Override
    public void start(Stage stage) throws IOException {
        Board board = new Board();

        Scene scene = new Scene(board.getLayout(), MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
        stage.setTitle(WINDOW_TITLE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(); //lancement de l'application
    }
}
