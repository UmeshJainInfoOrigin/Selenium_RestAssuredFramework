package testAutomation.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {

    private static final Logger LOG = LoggerFactory.getLogger(ReadProperties.class);

    private static ReadProperties instance = null;
    private final Properties properties;

    public ReadProperties() {
        this.properties = new Properties();
        try {
        	// Properties form propertyFile
            System.out.println("getProperty=="+System.getProperty("EnvName"));
            InputStream inputStreamForPropertyFile = ReadProperties.class.getResourceAsStream("/"+System.getProperty("EnvName")+".properties");
            this.properties.load(inputStreamForPropertyFile);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }

    public static ReadProperties getInstance() {
        if (instance == null) {
            instance = new ReadProperties();
        }
        return instance;
    }

    public String getValue(String key){
        // Properties defined in system has more priority than application property file
        if(System.getProperty(key)!=null){
                return System.getProperty(key);
        }
        return this.properties.getProperty(key);
    }

    public String getEnvironment(){
       return System.getProperty("EnvName");
    }
}