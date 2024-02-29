package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RecoveryPage;

import static helper.TestValues.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Login Page Tests")
public class LoginPageTest extends BaseTest {

    private static LoginPage loginPage;

    @BeforeEach
    void setup() {
        loginPage = new LoginPage(driver);
    }

    @DisplayName("Positive Login")
    @Test
    void posLoginTest() {
        loginPage.positiveAuth(USERNAME, PASSWORD);
        assertTrue(new MainPage(driver).getMenuButton().isDisplayed());
    }

    @DisplayName("Log with space before Login and Password")
    @Test
    void spaceButtonBeforeLogin() {
        loginPage.positiveAuth(USERNAME_WITH_SPACE, PASSWORD_WITH_SPACE);
        assertTrue(new MainPage(driver).getMenuButton().isDisplayed());
    }
    // Баг, не желательно, чтоб авторизация проходила с пробелами перед тестовыми данными

    @DisplayName("Try to log with empty username and password")
    @Test
    void emptyFieldsLogin() {
        loginPage.negativeAuth(EMPTY_USERNAME, EMPTY_PASSWORD);
        String actualAlertText = loginPage.getAlertText();
        assertEquals(WRONG_VALUES, actualAlertText);
    }

    @DisplayName("Try to log with more than maximum symbols in fields")
    @Test
    void maxCharactersInAuthFields() {
        String actualAlertText = loginPage.negativeAuth(RANDOM_VALUE(160), RANDOM_VALUE(160));
        assertEquals(MAX_LENGTH_CHARS, actualAlertText);
    }

    @DisplayName("Click 'Show Password' button")
    @Test
    void showPassButton() {
        loginPage.checkShowPassButton();
        loginPage.waitForShowPasswordButton();
        String actualType = loginPage.getPasswordInputType();
        assertEquals("text", actualType);
    }

    @DisplayName("Enter Recovery page")
    @Test
    void enterRecoveryPage() {
        loginPage.enterRecoveryPage();
        assertTrue(new RecoveryPage(driver).getLoginOrEmailButton().isDisplayed());
    }
}