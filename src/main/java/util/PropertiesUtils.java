package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {
        private static Properties properties;

        static {
            try {
                properties = new Properties();
                FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties");
                properties.load(fileInputStream);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Falha ao carregar o arquivo config.properties");
            }
        }

        public static String getProperty(String key) {
            return properties.getProperty(key);
        }
    }

