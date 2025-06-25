package com.carbon.project.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private static final String LOCKED_USER_TEXT = "//h3[@data-test='error' and text()='Epic sadface: Sorry, this user has been locked out.']";
    private static final String LOGIN_BUTTON = "//*[@id='login-button']";
    private static final String PASSWORD_TEXT_BOX = "//*[@id='password']";
    private static final String USERNAME_TEXT_BOX = "//*[@id='user-name']";


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void load(String url) {
        driver.get(url);
    }

    public void preencheCredenciais(String username, String password) {
        waitUntilElementIsClickable(By.xpath(USERNAME_TEXT_BOX)).sendKeys(username);
        System.out.println("Preenche usuário " + username);
        waitUntilElementIsClickable(By.xpath(PASSWORD_TEXT_BOX)).sendKeys(password);
        System.out.println("Preenche senha " + password);
    }

    public void clicaLoginBtn() {
        waitUntilElementIsClickable(By.xpath(LOGIN_BUTTON)).click();
        System.out.println("Clica em Login");
    }

    public void validaMensagemLogin(String mensagem) {
        String mensagemAtual = waitUntilElementIsVisible(By.xpath(LOCKED_USER_TEXT)).getText();
        Assert.assertEquals("A mensagem de erro exibida não é a esperada.", mensagem, mensagemAtual);
    }
}
