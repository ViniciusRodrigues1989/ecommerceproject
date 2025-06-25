package com.carbon.project.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends SFDXPage {
    private static final String BURGERMENUBTN = "//*[@id='react-burger-menu-btn']";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void validoLoginSucesso () {
        WebElement btnMenu = driver.findElement(By.xpath(BURGERMENUBTN));

        if (btnMenu.isDisplayed()) {
            System.out.println("✅ O botão está visível.");
        } else {
            System.out.println("❌ O botão não está visível.");
        }
        Assert.assertTrue("O botão do menu não está visível após o login.", btnMenu.isDisplayed());
    }
}
