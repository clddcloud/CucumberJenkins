package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
        public static String read(String path,String key) {
            Properties properties=new Properties();
            try (FileInputStream fis=new FileInputStream(path)) {
                properties.load(fis);
            } catch (IOException ioException){
                ioException.printStackTrace();
            }
            return properties.getProperty(key);
        }
        // method overloading - and it returns "read" that is going to be executed in the first method after it
        public static String read (String key)  {
            return read (Constants.CONFIG_FILE_PATH, key);
        }
    }

