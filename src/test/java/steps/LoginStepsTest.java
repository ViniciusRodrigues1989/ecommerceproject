package steps;

import base.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import factory.WebDriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import static pages.Constants.*;

public class LoginStepsTest extends BaseTest {
    private WebDriver driver;
    protected LoginPage loginPage;
    protected HomePage homePage;


    @Given("Acesso a homepage do commerce")
    public void Acesso_a_homepage_do_commerce() {
        driver = WebDriverFactory.createDriver();
        loginPage = new LoginPage(driver);

        loginPage.load(getStoreFrontCommerceUrl());
        loginPage.preencheCredenciais(getValidUsername(), getValidPassword());
    }
    @Given("Acesso a homepage do commerce com usuario bloqueado")
    public void Acesso_a_homepage_do_commerce_com_usuario_bloqueado() {
        driver = WebDriverFactory.createDriver();
        loginPage = new LoginPage(driver);

        loginPage.load(getStoreFrontCommerceUrl());
        loginPage.preencheCredenciais(getLockedUsername(), getValidPassword());
    }

    @Given("Acesso a homepage do commerce com usuario valido e senha errada")
    public void Acesso_a_homepage_do_commerce_com_usuario_valido_e_senha_errada() {
        driver = WebDriverFactory.createDriver();
        loginPage = new LoginPage(driver);

        loginPage.load(getStoreFrontCommerceUrl());
        loginPage.preencheCredenciais(getValidUsername(), getInvalidPassword());
    }

    @Given("Acesso a homepage do commerce com usuario vazio e senha em branco")
    public void Acesso_a_homepage_do_commerce_com_usuario_e_senha_em_branco() {
        driver = WebDriverFactory.createDriver();
        loginPage = new LoginPage(driver);

        loginPage.load(getStoreFrontCommerceUrl());
        loginPage.preencheCredenciais(getEmptyUsername(), getEmptyPassword());
    }

    @Given("Acesso a homepage do commerce com usuario valido e senha em branco")
    public void Acesso_a_homepage_do_commerce_com_usuario_valido_e_senha_em_branco() {
        driver = WebDriverFactory.createDriver();
        loginPage = new LoginPage(driver);

        loginPage.load(getStoreFrontCommerceUrl());
        loginPage.preencheCredenciais(getValidUsername(), getEmptyPassword());
    }

    @When("Clico em login")
    public void Clico_em_login() {
        homePage = new HomePage(driver);

        loginPage.clicaLoginBtn();
    }

    @Then("Valido Login Sucesso")
    public void Valido_Login_Sucesso() {
        homePage = new HomePage(driver);

        homePage.validoLoginSucesso();
        driver.quit();
    }

    @Then("Devo visualizar a mensagem de erro user has been locked out")
    public void Valido_Usuário_Bloqueado() {
        homePage = new HomePage(driver);

        loginPage.validaMensagemLogin(USUARIO_BLOQUEADO_TEXT);
        driver.quit();
    }

    @Then("Devo visualizar a mensagem usuario ou senha invalidos")
    public void Valido_Usuário_Senha_Invalida() {
        homePage = new HomePage(driver);

        loginPage.validaMensagemWrongCredential(INVALID_USER_OR_PASSWORD_TEXT);
        driver.quit();
    }

    @Then("Mensagem usuario obrigatorio sera exibida")
    public void Mensagem_Usuario_Obrigatorio_Exibida() {
        homePage = new HomePage(driver);

        loginPage.validaUsuarioVazio(REQUIRED_USERNAME_TEXT);
        driver.quit();
    }

    @Then("Mensagem senha obrigatorio devera sera exibida")
    public void Valido_Mensagem_Senha_Obrigatoria() {
        homePage = new HomePage(driver);

        loginPage.validaSenhaVazia(REQUIRED_PASSWORD_TEXT);
        driver.quit();
    }
}