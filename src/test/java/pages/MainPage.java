package pages;

import base.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BaseSeleniumPage {

    @FindBy(xpath = "//span[contains(@class, 'mira-vertical-portal-menu-fixer-title')]")
    public WebElement menuButton;

    public MainPage() {
        PageFactory.initElements(driver, this);
    }

    public WebElement getMenuButton() {
        return menuButton;
    }
}