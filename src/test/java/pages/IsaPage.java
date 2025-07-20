package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class IsaPage extends BasePage {


    public IsaPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@data-testid='Zone1-drawer']")
    WebElement zone1ButtonEl;

    @FindBy(xpath = "//button[@data-testid='ISA-drawer']")
    WebElement isaButtonEl;

    @FindBy(xpath = "//ul[@role='listbox']/li[4]")
    WebElement programPickerMenuItemEl;

    @FindBy(xpath = "//button[@data-testid='KPI Feature-drawer']")
    WebElement kpiFeatureButtonEl;

    @FindBy(xpath = "//button[@data-testid='sendToDetails']")
    WebElement previewButtonEl;

    @FindBy(xpath = "//div[@class='vis-foreground']/div[6]/div[10]/div[1]/div")
    WebElement proba;


    public void selectProgramItem() {
        clickElement(programPickerMenuItemEl, "Picker element is selected");
    }

    public void selectZone1() throws InterruptedException {
        clickElement(kpiFeatureButtonEl, "KPI Sensor button is pressed");
        clickElement(isaButtonEl, "FCM button is pressed");
        clickElement(zone1ButtonEl, "Lanes button is pressed");
        Thread.sleep(500);
    }

    public void selectDTIDs(String numberDTDTs) {

        int number = Integer.parseInt(numberDTDTs);
        // Select the first 7 checkboxes (if available)
        for (int i = 0; i < number; i++) {
            List<WebElement> checkboxes = driver.findElements(By.xpath("//tbody[@data-testid='tbody-left']//tr['" + i + "']//td//span/input"));
            WebElement checkbox = checkboxes.get(i);

            // Check if it's already selected
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }
    }

    public void previewButton() {
        clickElement(previewButtonEl, "Preview button is pressed");
    }

    public void checkNewOpenedTab() throws InterruptedException {
        Thread.sleep(2000);
        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        String latestTab = windowHandles.get(windowHandles.size() - 1);
        driver.switchTo().window(latestTab);
        System.out.println("New tab is opened");

        String expextedUrl = "https://qa-ottoviz.ominf.net/programs/VI1/zone1/kpi-details";
        String actualUrl = driver.getCurrentUrl();
        System.out.println("Current url address: " + actualUrl);
        Assert.assertTrue(actualUrl.contains(expextedUrl), actualUrl);
    }

    public void getAllDTIDsFNevents() {
        List<WebElement> numberOfCells = driver.findElements(By.xpath("//div[@class='vis-foreground']/div[6]"));

        for (int i = 1; i <= numberOfCells.size(); i++) {
            int total = 0; //sum of all EN event per one cell

            // Get all FN events
            List<WebElement> cellValues2 = driver.findElements(By.xpath("//div[@class='vis-foreground']/div[6]/div"));

            for (WebElement cell : cellValues2) {
                String text = cell.getText();
                // Calculate the average of sum values of all rows
                if (!text.isEmpty()) {
                    int value = Integer.parseInt(text);
                    total += value;
                }
            }
            System.out.println("Total number of FN events in the timeline: " + total);


        }
    }

}