package pages;

import base.BaseSeleniumPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RecoveryPage extends BaseSeleniumPage {

    @FindBy(xpath = "//input[@type='text']")
    private WebElement recoveryInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement recoverySubButton;

    @FindBy(xpath = "/html/body/div/div/div/div[2]/form/table[2]/tbody/tr/td/a/div")
    private WebElement backToLoginButton;

    public WebElement getSuccessfulRecovery() {
        return successfulRecovery;
    }

    public WebElement getFailedRecovery() {
        return failedRecovery;
    }

    @FindBy(xpath = "//div[@class='success']")
    private WebElement successfulRecovery;

    @FindBy(xpath = "//div[@class='alert']")
    private WebElement failedRecovery;

    public RecoveryPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getLoginOrEmailButton() {
        return recoverySubButton;
    }

    public RecoveryPage enterLoginOrPass(String username) {
        recoveryInput.sendKeys(username);
        recoverySubButton.click();
        return new RecoveryPage(driver);
    }

    public RecoveryPage backToLogin() {
        backToLoginButton.click();
        return new RecoveryPage(driver);
    }
}
