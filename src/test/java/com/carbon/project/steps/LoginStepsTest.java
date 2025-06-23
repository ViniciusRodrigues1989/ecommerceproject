package steps;

import base.BaseTest;
import com.carbon.project.pages.HomePage;
import com.carbon.project.pages.LoginPage;
import com.carbon.project.pages.StorePage;
import factory.WebDriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import static com.carbon.project.pages.Constants.CreateStore;

public class LoginStepsTest extends BaseTest {
    private WebDriver driver;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected StorePage storePage;

    @Given("Estou logado na homepage do Salesforce Core")
    public void Estou_logado_na_homepage_do_Salesforce_Core() throws InterruptedException {
        driver = WebDriverFactory.createDriver();
        loginPage = new LoginPage(driver);

        loginPage.load(getSalesforceCoreUrl());
        loginPage.authenticate(getCoreUsername(), getCorePassword());
        Thread.sleep(5000);
    }

    @When("Acesso o commerce app")
    public void Acesso_commerce_app() {
        homePage = new HomePage(driver);

        homePage.goToCommerceApp();
    }

    @Then("Devo ser direcionado para o app correto")
    public void Devo_ser_direcionado_para_o_app_correto() {
        storePage = new StorePage(driver);

        storePage.validateStorePage(CreateStore);
        driver.quit();
    }
}