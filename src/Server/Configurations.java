package Server;

import java.util.Properties;

public class Configurations {
    private static Configurations instance = null;
    private Properties prop;

    /**
     * constructor
     */
    private Configurations() {
        prop = new Properties();
    }

    /**
     * add property
     * @param key the key
     * @param value the value
     */
    public void addProperty(String key, String value) {
        // set key and value
        prop.setProperty(key, value);
    }

    /**
     *
     * @param key the key of the wanted property
     * @return the value property
     */
    public String getProperty(String key) {
        return prop.getProperty(key);
    }

    /**
     * @return the configuration instance
     */
    public static Configurations getInstance() {
        if (instance == null) {
            instance = new Configurations();
        }
        return instance;
    }
}
