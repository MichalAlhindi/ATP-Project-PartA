package Server;

import java.util.Properties;

public class Configurations {
    private static Configurations instance = null;
    private Properties prop;

    private Configurations() {
        prop = new Properties();
    }

    public void addProperty(String key, String value) {
        // set key and value
        prop.setProperty(key, value);
    }

    public String getProperty(String key) {
        return prop.getProperty(key);
    }

    public static Configurations getInstance() {
        if (instance == null) {
            instance = new Configurations();
        }
        return instance;
    }
}
