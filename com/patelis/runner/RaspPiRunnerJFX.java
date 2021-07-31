package com.patelis.runner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RaspPiRunnerJFX extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {   

        Parent root = FXMLLoader.load(getClass().getResource("/com/patelis/GUI/Runner.fxml"));
        root.setStyle("{-fx-border-insets: 5;-fx-background-insets: 5;-fx-background-radius: 6;-fx-border-radius: 6;-fx-border-color: gray;-fx-border-style: solid;-fx-border-width: 2;-fx-effect: dropshadow(three-pass-box, rgba(100, 100, 100, 1), 24, 0.5, 0, 0);}");

        Scene scene = new Scene(root);

        stage.getIcons().add(new Image("/com/patelis/icons/RaspberryPiIcon.png"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("RaspPi Runner");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
