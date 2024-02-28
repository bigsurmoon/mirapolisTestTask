package tests;

import base.BaseSeleniumTest;

import helper.TestValues;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RecoveryPage;

import static helper.TestValues.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPageTest extends BaseSeleniumTest {

    private static LoginPage loginPage;

    @BeforeEach
    public void setup() {
        loginPage = new LoginPage(driver);
    }

    @Test
    void posLoginTest() {
        loginPage.posAuth(TestValues.USERNAME, TestValues.PASSWORD);
        assertTrue(new MainPage(driver).getMenuButton().isDisplayed());
    }

    @Test
    void spaceButtonBeforeLoginAndValues() {
        loginPage.posAuth(USERNAME_WITH_SPACE, PASSWORD_WITH_SPACE);
        assertTrue(new MainPage(driver).getMenuButton().isDisplayed());
    }
    // Баг, не желательно, чтоб авторизация проходила с пробелами перед тестовыми данными

    @Test
    void emptyFieldsLogin() {
        loginPage.negAuth(EMPTY_USERNAME, EMPTY_PASSWORD);
        String actualAlertText = loginPage.getAlertText();
        assertEquals(WRONG_VALUES, actualAlertText);
    }

    @Test
    void maxCharactersInAuthFields() {
        String username = RANDOM_VALUE(160);
        String password = RANDOM_VALUE(160);
        String actualAlertText = loginPage.negAuth(username, password);
        assertEquals(MAX_LENGTH_CHARS, actualAlertText);
    }

    @Test
    void showPassButton() {
        loginPage.checkShowPassButton();
        loginPage.waitForShowPasswordButton();
        String actualType = loginPage.getPasswordInputType();
        assertEquals("text", actualType);
    }

    @Test
    void enterRecoveryPage() {
        loginPage.enterRecoveryPage();
        assertTrue(new RecoveryPage(driver).getLoginOrEmailButton().isDisplayed());
    }
}