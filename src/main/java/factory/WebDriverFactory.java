package factory;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory {
    public static WebDriver createDriver() {
        String projectPath = System.getProperty("user.dir");
        String chromeDriverPath = projectPath + "/chromedriver";

        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--incognito");
        options.setHeadless(false);

        WebDriver driver = new ChromeDriver(options);
        Dimension dimensions = new Dimension(1920,1080);

        driver.manage().window().setSize(dimensions);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);

        return driver;
    }
}