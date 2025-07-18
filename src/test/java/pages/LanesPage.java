package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LanesPage extends BasePage {


    public LanesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@data-testid='Lanes-drawer']")
    WebElement lanesButtonEl;

    @FindBy(xpath = "//button[@data-testid='FCM-drawer']")
    WebElement fcmButtonEl;

    @FindBy(xpath = "//div[@data-testid='program-picker-menu-select']")
    WebElement pickerMenuButtonEl;

    @FindBy(xpath = "//ul[@role='listbox']/li[3]")
    WebElement programPickerMenuItemEl;

    @FindBy(xpath = "//button[@data-testid='KPI Sensor-drawer']")
    WebElement kpiSensorButtonEl;


    public void programPickerMenu() {
        clickElement(pickerMenuButtonEl, "Picker menu program is expand");
    }

    public void selectProgramItem(){
        clickElement(programPickerMenuItemEl, "Picker element is selected");
    }

    public void selectLanes() throws InterruptedException {
        clickElement(kpiSensorButtonEl, "KPI Sensor button is pressed");
        clickElement(fcmButtonEl, "FCM button is pressed");
        clickElement(lanesButtonEl, "Lanes button is pressed");
        Thread.sleep(500);
    }

    public void checkSumValuesFromAllRowsWithTotalRowForColumn(){

        for (int i = 1; i < 15; i++){
            double total = 0.0; //sum of all rows for one column
            int count = 0; //count how many values/rows read

            // Get all the columns, one by one , rows 0 - 38
            List<WebElement> cellValues = driver.findElements(By.xpath("//*[@data-testid='tbody-center']/tr[position() <= 38]/td[" + i + "]"));

            for (WebElement cell : cellValues) {
                String text = cell.getText().replace("%", "").trim(); // Remove % symbol
                if (!text.isEmpty()) {
                    double value = Double.parseDouble(text);
                    total += value;
                    count++;
                }
            }

            // Calculate the average of sum values of all rows
            double average = total / count;
            System.out.println("Column_" + i + ":");
            System.out.println("Calculated Average: " + average + "%");

            // Get value from total row and appropriate column
            WebElement totalRow = driver.findElement(By.xpath("//table[@data-testid='table-center']//tfoot[@class='MuiTableFooter-root css-1q1uqq3']//tr/td[" + i + "]"));
            String text2 = totalRow.getText().replace("%", "").trim();
            double totalAverage = Double.parseDouble(text2);
            System.out.println("Existing Total: " + totalAverage + "%");

            // Compare with deviation of 0.1

            if (Math.abs(average - totalAverage) < 0.1) {
                System.out.println("Match: Calculated value matches the total.");
                System.out.println("==========================================");
            } else {
                System.out.println("Mismatch: Calculated value does not match the total.");
                System.out.println("==========================================");
            }
        }

    }
}


