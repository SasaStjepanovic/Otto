package pages;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class BasePage {

    public WebDriver driver;
    int waitTime = 30;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void explicitWait(WebElement element) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, waitTime);
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
    }

    public void clickElement(WebElement element, String log) {
        explicitWait(element);

        try {
            scrollToElement(element);
            new Actions(driver).moveToElement(element).perform();
            element.click();
            System.out.println("Clicked element1: " + log);
        } catch (Exception e) {
            e.printStackTrace();
            scrollToElement(element);
            element.click();
            System.out.println("Clicked element2: " + log);
        }
    }

    public void typeText(WebElement element, String text, String log) {
        explicitWait(element);

        try {
            scrollToElement(element);
            new Actions(driver).moveToElement(element).perform();
            element.click();
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            element.sendKeys(Keys.DELETE);
            element.sendKeys(text);
            System.out.println("Entered text: " + text + " :to element: " + log);
        } catch (Exception e) {
            e.printStackTrace();
            element.click();
            element.sendKeys(text);
            System.out.println("Entered text: " + text + " :to element: " + log);
        }
    }

    public void dropDownMenu(WebElement element, String item){
        explicitWait(element);

        Select select = new Select(element);
        select.selectByVisibleText(item);

    }


}
