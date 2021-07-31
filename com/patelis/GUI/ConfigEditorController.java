package com.patelis.GUI;

import com.patelis.runner.AlertDialog;
import com.patelis.runner.ConfigurationHandler;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ConfigEditorController implements Initializable {
    
    private Properties conf = ConfigurationHandler.getConfig();
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);
    
    @FXML
    private AnchorPane root = new AnchorPane();
    
    @FXML
    private TextField hostIPTextField = new TextField();
            
    @FXML
    private TextField WTProfileTextField = new TextField();
    
    @FXML
    private TextField VNCPathTextField = new TextField();
    
    @FXML
    private TextField WinSCPathTextField = new TextField();
    
    
    @FXML
    private void saveAction(ActionEvent event) throws IOException{
        if(hostIPTextField.getText().equals(conf.getProperty("Host")) && WTProfileTextField.getText().equals(conf.getProperty("WTProfile")) && VNCPathTextField.getText().equals(conf.getProperty("VNCPath")) && WinSCPathTextField.getText().equals(conf.getProperty("WinSCPath"))){
            AlertDialog.showInfoAlert("Values are the same!\nNo Action has been made!");
            backAction(event);
        }else{
            conf.setProperty("Host", hostIPTextField.getText());
            conf.setProperty("WTProfile", WTProfileTextField.getText());
            conf.setProperty("VNCPath", VNCPathTextField.getText());
            conf.setProperty("VNCPath", WinSCPathTextField.getText());
            Boolean result = ConfigurationHandler.setConfig(conf);
            
            if(result==true){
                AlertDialog.showInfoAlert("Values saved successfully!\nRestart the app in order for the changes to take effect.");
                System.exit(0);
            }else{
                AlertDialog.showInfoAlert("Values can't be saved!");
                backAction(event);
            }
        }
    }   
    
    @FXML
    private void backAction(ActionEvent event) throws IOException{
        Button backButton = (Button) event.getSource();
        Stage stage = (Stage) backButton.getScene().getWindow();
        
        Parent root = FXMLLoader.load(getClass().getResource("/com/patelis/GUI/Runner.fxml"));
        root.setStyle("{-fx-border-insets: 5;-fx-background-insets: 5;-fx-background-radius: 6;-fx-border-radius: 6;-fx-border-color: gray;-fx-border-style: solid;-fx-border-width: 2;-fx-effect: dropshadow(three-pass-box, rgba(100, 100, 100, 1), 24, 0.5, 0, 0);}");

        Scene scene = new Scene(root);
        stage.setTitle("RaspPi Runner");
        stage.setScene(scene);
        stage.show();
    }   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       hostIPTextField.setText(conf.getProperty("Host"));
       WTProfileTextField.setText(conf.getProperty("WTProfile"));
       VNCPathTextField.setText(conf.getProperty("VNCPath"));
       WinSCPathTextField.setText(conf.getProperty("WinSCPath"));
    }   
    
}
