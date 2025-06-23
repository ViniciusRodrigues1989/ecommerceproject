package com.carbon.project.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;

public abstract class SFDXPage extends BasePage {
    private static final String COMMERCE = "Commerce";
    private static final String SELECT = "Select...";
    private static final String SPECIFICATIONS = "Specifications";
    private static final String STORES = "Stores";

    private static final String APP_LAUNCHER_LINK = "//div[@class='slds-icon-waffle']";
    private static final String APP_NAME_TEXT = "//h1[@class='appName slds-context-bar__label-action slds-context-bar__app-name']";
    private static final String CLOSE_TAB_PATTERN_BUTTON = "//button[contains(@title, 'Close')]";
    protected static final String GRID_SPINNER_ICON = "//div[@class='slds-spinner slds-spinner--medium slds-spinner--brand']";
    private static final String LIST_VIEW_ARROW_LINK = "//button[contains(@title, 'Select a List View')]";
    private static final String NAV_BAR_DROP_DOWN_BUTTON = "//button[@title='Show Navigation Menu']";
    private static final String PAGE_TEXT = "//div[contains(@class,'selectedListItem')]//span";
    private static final String SEARCH_APPS_AND_ITEMS_TEXT_BOX = "//input[@placeholder='Search apps and items...']";

    public SFDXPage(WebDriver driver) {
        super(driver);
    }

    protected void goToApp(String appName) {
        var currentAppName = waitUntilElementIsVisible(By.xpath(APP_NAME_TEXT)).getText();

        if (!currentAppName.equals(appName)) {
            tryToRunTwice(() -> {
                changeApp(appName);
            }, false);
        }
        closeTabs();
    }

    public void searchAndClick(String searchText, int maxLimitAttempts) {
        var locator = By.xpath("//a[text() = '" + searchText + "']");
        searchAndClick(searchText, locator, maxLimitAttempts);
    }

    public void goToStores() {
        goToPage(STORES);
    }

    public void goToSpecifications() {
        goToPage(SPECIFICATIONS);
    }

    public void goToCommerceApp() {
        goToApp(COMMERCE);
    }

    private void goToPage(String pageName) {
        var currentPage = "";

        do {
            currentPage = waitUntilElementIsVisible(By.xpath(PAGE_TEXT)).getText();
        } while (currentPage.equals(SELECT));

        if (!currentPage.equals(pageName)) {
            tryToRunTwice(() -> {
                waitUntilElementIsClickable(By.xpath(NAV_BAR_DROP_DOWN_BUTTON)).click();
                waitUntilElementIsClickable(By.xpath("//a[@data-label='" + pageName + "']")).click();
            }, false);
        }
    }

    private void changeApp(String appName) {
        waitUntilElementIsClickable(By.xpath(APP_LAUNCHER_LINK)).click();
        waitUntilElementIsVisible((By.xpath(SEARCH_APPS_AND_ITEMS_TEXT_BOX))).sendKeys(appName);
        moveAndClick(By.xpath("//a[@data-label='"+appName+"']"));
    }

    private void closeTabs() {
        tryToRunTwice(() -> {
            try {
                var tabs = new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(CLOSE_TAB_PATTERN_BUTTON)));
                do {
                    if (!tabs.isEmpty()) {
                        Collections.reverse(tabs);
                        for (var tab : tabs) {
                            try {
                                waitUntilElementIsClickable(tab).click();
                            } catch (StaleElementReferenceException | ElementNotInteractableException ignored) {

                            }
                        }
                    }
                    tabs = driver.findElements(By.xpath(CLOSE_TAB_PATTERN_BUTTON));
                } while (!tabs.isEmpty());
            } catch (TimeoutException ignored) {

            }
        }, false);
    }
}