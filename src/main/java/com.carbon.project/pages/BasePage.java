package com.carbon.project.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public abstract class BasePage {
    private static final String ASSERT_VALIDATION_ERROR_MESSAGE = "Validation doesn't match or is not working as expected";

    protected static final String GRID_SPINNER_ICON = "//div[@class='slds-spinner slds-spinner--medium slds-spinner--brand']";
    private static final String LIST_VIEW_ARROW_LINK = "//button[contains(@title, 'Select a List View')]";
    private static final String REFRESH_BUTTON = "//button[@name='refreshButton']";
    private static final String SEARCH_BAR_TEXT_BOX = "//force-list-view-manager-search-bar//input";

    private final Actions actions;
    protected WebDriver driver;
    protected final WebDriverWait wait;
;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, 20);
    }

    public void searchAndClick(String searchText, By link, int maxLimitAttempts) {
        var attempts = 1;

        do {
            try {
                var searchBarElement = waitUntilElementIsClickable(By.xpath(SEARCH_BAR_TEXT_BOX));

                searchBarElement.clear();
                searchBarElement.sendKeys(searchText);
                searchBarElement.sendKeys(Keys.ENTER);

                if (elementExists(By.xpath(GRID_SPINNER_ICON))) {
                    waitUntilElementIsInvisible(By.xpath(GRID_SPINNER_ICON));
                }

                waitUntilElementIsClickable(link).click();
                break;
            } catch (NoSuchElementException | TimeoutException | StaleElementReferenceException ex) {
                attempts++;
                waitUntilElementIsClickable(By.xpath(REFRESH_BUTTON)).click();
            }
        } while (attempts <= maxLimitAttempts);
    }

    public void searchAndClick(String searchText, String xPathToClick) {
        searchAndClick(searchText, By.xpath(xPathToClick), 1);
    }

    public void changeListView(String listViewName) {
        tryToRunTwice(() -> {
            waitUntilElementIsClickable(By.xpath(LIST_VIEW_ARROW_LINK)).click();
            waitUntilElementIsVisible(By.xpath("//span[text() = '" + listViewName + "']//parent::a[@role='option']")).click();
            waitUntilElementIsInvisible(By.xpath(GRID_SPINNER_ICON));
        }, false);
    }

        public static void captureScreenshot(WebDriver driver, String fileName) {
            try {
                // Tira o screenshot
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

                // Define o local onde será salvo
                String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                File destination = new File("screenshots/" + fileName + "_" + timestamp + ".png");

                // Copia o arquivo para o local
                Files.createDirectories(destination.getParentFile().toPath());
                Files.copy(screenshot.toPath(), destination.toPath());

                System.out.println("Screenshot salvo em: " + destination.getAbsolutePath());
            } catch (IOException e) {
                System.err.println("Erro ao salvar o screenshot: " + e.getMessage());
            }
        }


    public void returnToPreviousPage() {
        driver.navigate().back();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    protected WebElement waitUntilElementIsClickable(By locator) {
        return wait.until(elementToBeClickable(locator));
    }

    protected WebElement waitUntilElementIsClickable(WebElement element) {
        return wait.until(elementToBeClickable(element));
    }

    protected void waitUntilElementIsInvisible(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected void waitUntilElementIsInvisible(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected WebElement waitUntilElementIsVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitUntilElementIsPresent(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected void moveAndClick(By locator) {
        actions.moveToElement(waitUntilElementIsClickable((locator))).click().perform();
    }

    protected void selectDropDownByText(By locator, String value) {
        var element = new Select(waitUntilElementIsVisible(locator));
        element.selectByVisibleText(value);
    }

    protected void validateText(String xPathField, String expectedText) {
        var actualText = waitUntilElementIsVisible(By.xpath(xPathField)).getText();
        Assertions.assertEquals(ASSERT_VALIDATION_ERROR_MESSAGE, expectedText, actualText);
    }

    protected void validateEmptyField(String xPathField) {
        var actualText = waitUntilElementIsVisible(By.xpath(xPathField)).getText();
        Assertions.assertTrue(!actualText.isEmpty());
    }

    protected boolean elementExists(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    protected void tryToRunTwice(Runnable func, boolean refresh) {
        try {
            func.run();
        } catch (WebDriverException ex) {
            if (refresh)
                driver.navigate().refresh();

            func.run();
        }
    }
}