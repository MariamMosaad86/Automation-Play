package utilities.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {

    public static Properties webConfig;

    public PropertiesManager(){
    }

    public static void initializeProperties(){
        FileInputStream fileInputStream= null;    //بعمل object جديد علشان يقرا ال properties
        try {
           fileInputStream  = new FileInputStream("src/main/resources/webConfigurations.properties");//بديلة ال path الخاص يال config file
            webConfig = new Properties();
            webConfig.load(fileInputStream);
        } catch (FileNotFoundException e) {
            System.out.println("Property file is not found");
        } catch (IOException e) {
            System.out.println("File isn't readable");
        }

    }
}
