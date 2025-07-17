package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {


    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#outlined-basic")
    WebElement emailEl;

    @FindBy(css = "#outlined-adornment-password")
    WebElement passwordEl;

    @FindBy(xpath = "//button[@data-testid='otto-login-btn']")
    WebElement loginButtonEl;

    @FindBy(xpath = "//button[@data-testid='table-settings']")
    WebElement loginVerificationButtonEl;


    public void logInButton() {
        clickElement(loginButtonEl, "Login button is pressed");
    }

    public void enterEmailAndPassword(String email, String password) {
        typeText(emailEl, email, "Email is entered");
        typeText(passwordEl, password, "Password is entered");
    }

    public void visibilityOfButton() {
        explicitWait(loginVerificationButtonEl);
    }

}


