package com.patelis.runner;

import java.io.PrintWriter;
import java.io.StringWriter;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.StageStyle;

public class AlertDialog {
    
    private static final Alert error = new Alert(Alert.AlertType.ERROR);
    private static final Alert info = new Alert(Alert.AlertType.INFORMATION);
    
    public static void showInfoAlert(String message){
        info.setTitle("Information Dialog");
        info.setHeaderText(null);
        info.setContentText(message);
        info.showAndWait();
    }
    
    public static void showErrorAlert(Exception ex){
        error.setTitle("Error Dialog");
        error.setHeaderText("An error has occured!");
        error.setContentText(ex.toString()); 
        
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);
        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(textArea, 0, 0);

        error.getDialogPane().setExpandableContent(expContent);
        error.showAndWait();
        System.exit(0);
    }
    
}
