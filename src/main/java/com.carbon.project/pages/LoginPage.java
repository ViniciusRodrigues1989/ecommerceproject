package com.carbon.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private static final String USERNAME_TEXT_BOX = "//input[@name='username']";
    private static final String PASSWORD_TEXT_BOX = "//input[@type='password']";
    private static final String LOGIN_BUTTON = "//input[@id='Login']";


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void load(String url) {
        driver.get(url);
    }

    public void authenticate(String username, String password) {
        waitUntilElementIsClickable(By.xpath(USERNAME_TEXT_BOX)).sendKeys(username);
        waitUntilElementIsClickable(By.xpath(PASSWORD_TEXT_BOX)).sendKeys(password);
        waitUntilElementIsClickable(By.xpath(LOGIN_BUTTON)).click();
        System.out.println(" - Login realizado com sucesso");
    }
}
