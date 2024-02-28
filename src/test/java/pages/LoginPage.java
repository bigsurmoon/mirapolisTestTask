package pages;

import base.BaseSeleniumPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static helper.TestValues.*;

public class LoginPage extends BaseSeleniumPage {

    @FindBy(xpath = "//input[@name='user']")
    private WebElement loginInput;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[@id='show_password']")
    private WebElement showPassword;

    @FindBy(xpath = "//button[@id='button_submit_login_form']")
    private WebElement submitButton;

    public WebElement getForgotPass() {
        return forgotPass;
    }

    @FindBy(xpath = "//a[@class='mira-default-login-page-link']//div[contains(text(),'Забыли пароль?')]")
    private WebElement forgotPass;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void stepsToLogin(String username, String password) {
        loginInput.sendKeys(username);
        passwordInput.sendKeys(password);
        submitButton.click();
    }


    public void positiveAuth(String username, String password) {
        stepsToLogin(username, password);
        new MainPage(driver);
    }

    public String negativeAuth(String username, String password) {
        stepsToLogin(username, password);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }

    public void waitForShowPasswordButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.attributeToBe(passwordInput, "type", "text"));
    }

    public String getAlertText() {
        try {
            Alert alert = driver.switchTo().alert();
            return alert.getText();
        } catch (NoAlertPresentException e) {
            return WRONG_VALUES;
        }
    }

    public void checkShowPassButton() {
        showPassword.click();
    }

    public String getPasswordInputType() {
        return passwordInput.getAttribute("type");
    }

    public void enterRecoveryPage() {
        forgotPass.click();
        new RecoveryPage(driver);
    }
}