package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    @FindBy(xpath = "//span[contains(@class, 'mira-vertical-portal-menu-fixer-title')]")
    private WebElement menuButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getMenuButton() {
        return menuButton;
    }
}