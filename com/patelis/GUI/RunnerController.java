package com.patelis.GUI;

import com.patelis.runner.AlertDialog;
import com.patelis.runner.ConfigurationHandler;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RunnerController implements Initializable {
    
    private static final Desktop desktop = Desktop.getDesktop();
    private static final Properties conf = ConfigurationHandler.getConfig();
    
    private static final String hostIP = conf.getProperty("Host");
    private static final String WTProfile = conf.getProperty("WTProfile");
    private static final String VNCPath = conf.getProperty("VNCPath");
    private static final String WinSCPath = conf.getProperty("WinSCPath");

    
    @FXML
    private AnchorPane root = new AnchorPane();
    
    @FXML
    private void netdataAction(ActionEvent event){
        try{
            desktop.browse(new URI(hostIP + ":19999/"));
        }catch(URISyntaxException | IOException ex) {
            AlertDialog.showErrorAlert(ex);
        }
    }
    
    @FXML
    private void piHoleAction(ActionEvent event){
        try{
            desktop.browse(new URI(hostIP + "/admin"));
        }catch(URISyntaxException | IOException ex) {
            AlertDialog.showErrorAlert(ex);
        }
    }
    
    @FXML
    private void portainerAction(ActionEvent event){
        try{
            desktop.browse(new URI(hostIP + ":9000/"));
        }catch(URISyntaxException | IOException ex) {
            AlertDialog.showErrorAlert(ex);
        }
    }
    
    @FXML
    private void sshAction(ActionEvent event){
        try{
            Runtime.getRuntime().exec("wt -p" + WTProfile);
        }catch(IOException ex) {
            AlertDialog.showErrorAlert(ex);
        }
    }
    
    @FXML
    private void ultraVncAction(ActionEvent event){
        try{
            Runtime.getRuntime().exec(VNCPath);
        }catch(IOException ex) {
            AlertDialog.showErrorAlert(ex);
        }
    }
    
    @FXML
    private void winSCPAction(ActionEvent event){
        try{
            Runtime.getRuntime().exec(WinSCPath);
        }catch(IOException ex) {
            AlertDialog.showErrorAlert(ex);
        }
    }
    
    @FXML
    private void exitAction(ActionEvent event){
        Button exitButton = (Button) event.getSource();
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
        System.exit(0);
    }   
    
    @FXML
    private void configAction(ActionEvent event) throws IOException{
        Button configButton = (Button) event.getSource();
        Stage stage = (Stage) configButton.getScene().getWindow();
        
        Parent root = FXMLLoader.load(getClass().getResource("/com/patelis/GUI/ConfigEditor.fxml"));
        root.setStyle("{-fx-border-insets: 5;-fx-background-insets: 5;-fx-background-radius: 6;-fx-border-radius: 6;-fx-border-color: gray;-fx-border-style: solid;-fx-border-width: 2;-fx-effect: dropshadow(three-pass-box, rgba(100, 100, 100, 1), 24, 0.5, 0, 0);}");
            
        Scene scene = new Scene(root);
        stage.setTitle("RaspPi Runner Settings");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) { }

}
