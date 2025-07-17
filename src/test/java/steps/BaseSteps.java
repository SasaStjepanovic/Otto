package steps;

import Excel.ExcelSupport;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import org.testng.Reporter;
import pages.BasePage;
import pages.LoginPage;
import tests.BaseTest;

import java.util.Map;

public class BaseSteps extends BaseTest {

    Map<String, String> data;

    String browser = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
    String env = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("env");
    String wait = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("wait");

    @Before
    public void setup() throws Exception {
        init(browser, wait);
        otto((env));
    }

    @After
    public void tearDown() {
        quit();
    }

    @Given("a user reads test data from {string} {string} by id {string}")
    public void aUserReadsTestDataFromById(String fileName, String sheetName, String id) throws Exception {
        data = new ExcelSupport().getDataByID(fileName, sheetName, id);
        System.out.println(data.get("name"));
    }

    @And("user enter email and password")
    public void userEnterEmailAndPassword() {
        new LoginPage(driver).enterEmailAndPassword(data.get("email"), data.get("password"));
    }

    @And("user press login button")
    public void userPressLoginButton() {
        new LoginPage(driver).logInButton();
    }

    @Then("user verify login action")
    public void userVerifyLoginAction() {
        new LoginPage(driver).visibilityOfButton();
    }
}
