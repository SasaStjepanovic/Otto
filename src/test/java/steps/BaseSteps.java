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
import pages.LanesPage;
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
//        quit();
    }

    @Given("a user reads test data from {string} {string} by id {string}")
    public void aUserReadsTestDataFromById(String fileName, String sheetName, String id) throws Exception {
        data = new ExcelSupport().getDataByID(fileName, sheetName, id);
        System.out.println(data.get("name"));
    }

    @And("user enters email and password")
    public void userEntersEmailAndPassword() {
        new LoginPage(driver).enterEmailAndPassword(data.get("email"), data.get("password"));
    }

    @And("user presses login button")
    public void userPressesLoginButton() {
        new LoginPage(driver).logInButton();
    }

    @Then("user verify login action")
    public void userVerifyLoginAction() {
        new LoginPage(driver).visibilityOfButton();
    }

    @And("user presses program picker menu")
    public void userPressesProgramPickerMenu() {
        new LanesPage(driver).programPickerMenu();
    }

    @And("user selects camera system vt{int}")
    public void userSelectsCameraSystemVt(int arg0) {
        new LanesPage(driver).selectProgramItem(data.get("program"));
    }

    @And("user selects lines option")
    public void userSelectsLinesOption() throws InterruptedException {
        new LanesPage(driver).selectLanes();
    }


    @Then("user compares the average of the row values with the total value")
    public void userComparesTheAverageOfTheRowValuesWithTheTotalValue() {
        new LanesPage(driver).checkSumValuesFromAllRowsWithTotalRowForColumn1();
        new LanesPage(driver).checkSumValuesFromAllRowsWithTotalRowForColumn2();
        new LanesPage(driver).checkSumValuesFromAllRowsWithTotalRowForColumn3();
        new LanesPage(driver).checkSumValuesFromAllRowsWithTotalRowForColumn4();
        new LanesPage(driver).checkSumValuesFromAllRowsWithTotalRowForColumn5();
        new LanesPage(driver).checkSumValuesFromAllRowsWithTotalRowForColumn6();
        new LanesPage(driver).checkSumValuesFromAllRowsWithTotalRowForColumn7();
        new LanesPage(driver).checkSumValuesFromAllRowsWithTotalRowForColumn8();
        new LanesPage(driver).checkSumValuesFromAllRowsWithTotalRowForColumn9();
        new LanesPage(driver).checkSumValuesFromAllRowsWithTotalRowForColumn10();
        new LanesPage(driver).checkSumValuesFromAllRowsWithTotalRowForColumn11();
        new LanesPage(driver).checkSumValuesFromAllRowsWithTotalRowForColumn12();
        new LanesPage(driver).checkSumValuesFromAllRowsWithTotalRowForColumn13();
        new LanesPage(driver).checkSumValuesFromAllRowsWithTotalRowForColumn14();
//        new LanesPage(driver).checkSumValuesFromAllRowsWithTotalRowForColumn();
    }
}
