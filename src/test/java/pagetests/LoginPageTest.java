package pagetests;

import base.BaseSeleniumTest;

import helper.TestValues;
import org.junit.Assert;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RecoveryPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static helper.TestValues.*;


public class LoginPageTest extends BaseSeleniumTest {

    @Test
    public void posLoginTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.posAuth(TestValues.USERNAME, TestValues.PASSWORD);
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.getMenuButton().isDisplayed());
    }

    @Test
    public void spaceButtonBeforeLoginAndValues() {
        LoginPage loginPage = new LoginPage();
        loginPage.posAuth(USERNAME_WITH_SPACE, PASSWORD_WITH_SPACE);
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.getMenuButton().isDisplayed());
    }
    // Баг, не желательно, чтоб авторизация проходила с пробелами перед тестовыми данными

    @Test
    public void emptyFieldsLogin() {
        LoginPage loginPage = new LoginPage();
        loginPage.negAuth(EMPTY_USERNAME, EMPTY_PASSWORD);
        String actualAlertText = loginPage.getAlertText();
        Assert.assertEquals(WRONG_VALUES, actualAlertText);
    }

    @Test
    public void maxCharactersInAuthFields() {
        LoginPage loginPage = new LoginPage();
        String username = RANDOM_VALUE(160);
        String password = RANDOM_VALUE(160);
        String actualAlertText = loginPage.negAuth(username, password);
        Assert.assertEquals(MAX_LENGTH_CHARS, actualAlertText);
    }

    @Test
    public void showPassButton() {
        LoginPage loginPage = new LoginPage();
        loginPage.checkShowPassButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.attributeToBe(loginPage.passwordInput, "type", "text"));
        String actualType = loginPage.getPasswordInputType();
        Assert.assertEquals("text", actualType);
    }

    @Test
    public void enterRecoveryPage() {
        LoginPage loginPage = new LoginPage();
        loginPage.enterRecoveryPage();
        RecoveryPage recoveryPage = new RecoveryPage();
        Assert.assertTrue(recoveryPage.getLoginOrEmailButton().isDisplayed());
    }
}