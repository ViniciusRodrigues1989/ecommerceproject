package factory;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class WebDriverFactory {
    public static WebDriver createDriver() {
        System.setProperty("webdriver.chrome.driver", "/Users/viniciusrodrigues/ecommerceproject/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        // options.setHeadless(false); // Selenium 4.x no longer supports this method, if you need headless use options.addArguments("--headless=new");

        WebDriver driver = new ChromeDriver(options);
        Dimension dimensions = new Dimension(1920,1080);

        driver.manage().window().setSize(dimensions);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        return driver;
    }
}
