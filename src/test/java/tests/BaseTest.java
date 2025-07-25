package tests;

import Selenium.DriverManager;
import Selenium.DriverManagerFactory;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;

    DriverManager driverManager;

    public void init(String browser, String wait) throws Exception {
        driverManager = DriverManagerFactory.getDriverManager(browser);
        driver = driverManager.getWebDriver();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(wait), TimeUnit.SECONDS);
    }

    public void otto(String env) throws Exception {
        switch (env.toUpperCase()) {
            case "QA": {
                driver.get("https://www.booking.com/");
            }
            break;
            case "PROD": {
                driver.get("https://qa-ottoviz.ominf.net/");
            }
            break;
            case "BIZ": {
                driver.get("https://www.yahoo.com/");
            }
            break;
            default:
                throw new Exception("No such environment: " + env);
        }
    }

    public void quit() {
        driverManager.quitWebDriver();
    }
}