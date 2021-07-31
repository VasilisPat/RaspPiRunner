package com.patelis.runner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationHandler {
    
    private static Properties props = new Properties();
    private static File configFile = new File(System.getProperty("user.dir") + "/config.properties");
    
    private static void configReader(){
        if(configFile.exists()){ //If config exists, read from the config file
            try{
                InputStream inputStream = new FileInputStream(configFile);
                props.load(inputStream);
                inputStream.close();
            }catch(IOException ex) {
                AlertDialog.showErrorAlert(ex);
            }
        }else{ //If the config doesn't exist, create a new file and copy the config parameters from the .properties inside the jar
            try{
                configFile.createNewFile();
                InputStream inputStream = ConfigurationHandler.class.getResourceAsStream("/com/patelis/resources/config.properties");
                FileOutputStream outputStream = new FileOutputStream(configFile);
                props.load(inputStream);
                props.store(outputStream, null);
                inputStream.close();
                outputStream.close();
            }catch(IOException ex) {
                AlertDialog.showErrorAlert(ex);
            }
        }
        
    }
    
    private static Boolean updateConfig(Properties newProps){
        Boolean done = false;
        try{
            FileOutputStream outputStream = new FileOutputStream(configFile);
            newProps.store(outputStream, null);
            outputStream.close();
            done=true;
        }catch(IOException ex) {
            AlertDialog.showErrorAlert(ex);
        }
        return done;
    }
        
    public static Properties getConfig(){
        configReader();
        return props;
    }
    
    public static Boolean setConfig(Properties newProps){
        return updateConfig(newProps);
    }
    
}