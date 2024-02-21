package pages;

import base.BaseSeleniumPage;
import helper.TestValues;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static helper.TestValues.*;

public class LoginPage extends BaseSeleniumPage {

    @FindBy(xpath = "//input[@name='user']")
    public WebElement loginInput;

    @FindBy(xpath = "//input[@name='password']")
    public WebElement passwordInput;

    @FindBy(xpath = "//button[@id='show_password']")
    public WebElement showPassword;

    @FindBy(xpath = "//button[@id='button_submit_login_form']")
    public WebElement submitButton;

    public WebElement getForgotPass() {
        return forgotPass;
    }

    @FindBy(xpath = "//a[@class='mira-default-login-page-link']//div[contains(text(),'Забыли пароль?')]")
    private WebElement forgotPass;

    public LoginPage() {
        driver.get("https://lmslite47vr.demo.mirapolis.ru/mira");
        PageFactory.initElements(driver, this);
    }

    public MainPage posAuth(String username, String password) {
        loginInput.sendKeys(username);
        passwordInput.sendKeys(password);
        submitButton.click();
        return new MainPage();
    }

    public String negAuth(String username, String password) {
        loginInput.sendKeys(username);
        passwordInput.sendKeys(password);
        submitButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }

    public String getAlertText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            return alertText;
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

    public RecoveryPage enterRecoveryPage() {
        forgotPass.click();
        return new RecoveryPage();
    }
}