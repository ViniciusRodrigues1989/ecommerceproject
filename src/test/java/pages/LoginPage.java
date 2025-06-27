package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private static final String EMPTY_USERNAME_TEXT = "//h3[@data-test='error' and text()='Epic sadface: Username is required']";
    private static final String LOCKED_USER_TEXT = "//h3[@data-test='error' and text()='Epic sadface: Sorry, this user has been locked out.']";
    private static final String LOGIN_BUTTON = "//*[@id='login-button']";
    private static final String PASSWORD_TEXT_BOX = "//*[@id='password']";
    private static final String EMPTY_PASSWORD_TEXT = "//h3[@data-test='error' and text()='Epic sadface: Password is required']";
    private static final String USERNAME_TEXT_BOX = "//*[@id='user-name']";
    private static final String WRONG_CREDENTIAL_TEXT = "//h3[@data-test='error' and text()='Epic sadface: Username and password do not match any user in this service']";


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
        if (mensagemAtual.equals(mensagem)) {
            System.out.println("✅ A mensagem de validação está correta.");
        } else {
            System.out.println("❌ A validação de usuário e/ou senha errada está diferente do esperado");
        }
        Assert.assertEquals("A mensagem de erro exibida não é a esperada." + mensagemAtual, mensagem, mensagemAtual);
    }

    public void validaMensagemWrongCredential(String mensagem) {
        String mensagemAtual = waitUntilElementIsVisible(By.xpath(WRONG_CREDENTIAL_TEXT)).getText();
        if (mensagemAtual.equals(mensagem)) {
            System.out.println("✅ A mensagem de validação está correta.");
        } else {
            System.out.println("❌ A validação de usuário e/ou senha errada está diferente do esperado");
        }
        Assert.assertEquals("A mensagem de erro exibida não é a esperada. " + mensagemAtual, mensagem, mensagemAtual);
    }

    public void validaUsuarioVazio(String mensagem) {
        String mensagemAtual = waitUntilElementIsVisible(By.xpath(EMPTY_USERNAME_TEXT)).getText();
        if (mensagemAtual.equals(mensagem)) {
            System.out.println("✅ A mensagem de validação está correta.");
        } else {
            System.out.println("❌ A validação de usuário e/ou senha errada está diferente do esperado");
        }
        Assert.assertEquals("A mensagem de erro exibida não é a esperada. " + mensagemAtual, mensagem, mensagemAtual);
    }

    public void validaSenhaVazia(String mensagem) {
        String mensagemAtual = waitUntilElementIsVisible(By.xpath(EMPTY_PASSWORD_TEXT)).getText();
        if (mensagemAtual.equals(mensagem)) {
            System.out.println("✅ A mensagem de validação está correta.");
        } else {
            System.out.println("❌ A validação de usuário e/ou senha errada está diferente do esperado");
        }
        Assert.assertEquals("A mensagem de erro exibida não é a esperada. " + mensagemAtual, mensagem, mensagemAtual);
    }
}