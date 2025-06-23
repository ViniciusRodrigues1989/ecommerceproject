package base;

import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class BaseTest {
    protected WebDriver driver;
    protected Properties credentials = new Properties();
    protected Properties config = new Properties();

    public BaseTest() {
        loadConfigurations();
    }

    private void loadConfigurations() {
        try (FileInputStream configFile = new FileInputStream("src/test/resources/config.properties");
             FileInputStream credentialsFile = new FileInputStream("src/test/resources/credentials.properties")) {
            config.load(configFile);
            credentials.load(credentialsFile);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration or credentials file: " + e.getMessage(), e);
        }
    }

    public String getCoreUsername() {
        var username = System.getProperty("coreUsername");
        return username == null ? credentials.getProperty("coreUsername") : username;
    }

    public String getCorePassword() {
        var password = System.getProperty("corePassword");
        return password == null ? credentials.getProperty("corePassword") : password;
    }

    public String getSalesforceCoreUrl() {
        return config.getProperty("salesforceCoreUrl");
    }

    public String getStoreFrontB2CUrl() {
        return config.getProperty("storeFrontB2BUrl");
    }

    public String getStoreFrontUsername() {
        var username = System.getProperty("buyerUsername");
        return username == null ? credentials.getProperty("buyerUsername") : username;
    }

    public String getBuyerPassword() {
        var password = System.getProperty("buyerPassword");
        return password == null ? credentials.getProperty("buyerPassword") : password;
    }

    protected Integer getRandomNumber() {
        return (int) (Math.random() * 100000000 + 1);
    }
}