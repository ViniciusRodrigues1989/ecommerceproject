package com.carbon.project.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class StorePage extends SFDXPage {
    private static final String  CREATESTOREBTN = "//button[@title='Create Store']";

    public StorePage(WebDriver driver) {
        super(driver);
    }

    public void validateStorePage(String name) {
        Assert.assertEquals(name, waitUntilElementIsVisible(By.xpath(CREATESTOREBTN)).getText());
        captureScreenshot(driver, "StorePage");

    }
}