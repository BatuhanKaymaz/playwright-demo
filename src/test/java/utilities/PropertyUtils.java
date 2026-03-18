package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

    static Properties properties ;

    static {
        try(FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+ "/src/test/resources/config.properties")){
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("Veriler cekilmedi");
        }
    }

    public static  String getProperty (String key){
        return properties.getProperty(key);
    }
}
