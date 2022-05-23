package com.example.helbpark;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Board board = new Board();

        Scene scene = new Scene(board.getLayout(), 750, 600);
        stage.setTitle("HELBPark");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(); //lancement de l'application
    }
}
