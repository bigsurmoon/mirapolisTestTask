package pages;

import base.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RecoveryPage extends BaseSeleniumPage {

    @FindBy(xpath = "//input[@type='text']")
    public WebElement recoveryInput;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement recoverySubButton;

    @FindBy(xpath = "/html/body/div/div/div/div[2]/form/table[2]/tbody/tr/td/a/div")
    public WebElement backToLoginButton;

    public WebElement getSuccessfulRecovery() {
        return successfulRecovery;
    }

    public WebElement getFailedRecovery() {
        return failedRecovery;
    }

    @FindBy(xpath = "//div[@class='success']")
    public WebElement successfulRecovery;

    @FindBy(xpath = "//div[@class='alert']")
    public WebElement failedRecovery;

    public RecoveryPage() {
        PageFactory.initElements(driver, this);
    }

    public WebElement getLoginOrEmailButton() {
        return recoverySubButton;
    }

    public RecoveryPage enterLoginOrPass(String username) {
        recoveryInput.sendKeys(username);
        recoverySubButton.click();
        return new RecoveryPage();
    }

    public RecoveryPage backToLogin() {
        backToLoginButton.click();
        return new RecoveryPage();
    }
}
