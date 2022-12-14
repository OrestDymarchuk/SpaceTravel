package space_travel.preferences;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileReader {
    private static final Properties PROPERTIES = new Properties();
    public String getPath() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("application.properties");
        PROPERTIES.load(fileInputStream);
        return PROPERTIES.getProperty("path");
    }
}
