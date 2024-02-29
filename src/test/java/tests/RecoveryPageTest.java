package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.RecoveryPage;

import static helper.TestValues.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Recovery Page Tests")
public class RecoveryPageTest extends BaseTest {

    private static LoginPage loginPage;
    private static RecoveryPage recoveryPage;

    @BeforeEach
    void setup() {
        loginPage = new LoginPage(driver);
        recoveryPage = new RecoveryPage(driver);
    }

    @DisplayName("Enter Recovery Page")
    @Test
    void successfulRecovery() {
        loginPage.enterRecoveryPage();
        assertTrue(recoveryPage.enterLoginOrPass(USERNAME).getSuccessfulRecovery().isDisplayed());
    }

    @DisplayName("Try to recover with empty field")
    @Test
    void failedRecoveryWithEmptyFields() {
        loginPage.enterRecoveryPage();
        assertTrue(recoveryPage.enterLoginOrPass(EMPTY_USERNAME).getFailedRecovery().isDisplayed());
    }

    @DisplayName("Try to recover with wrong values")
    @Test
    void failedRecoveryWithWrongValues() {
        loginPage.enterRecoveryPage();
        assertTrue(recoveryPage.enterLoginOrPass(RANDOM_VALUE(20)).getFailedRecovery().isDisplayed());
    }

    @DisplayName("Return to Login Page")
    @Test
    void returnToLoginPage() {
        loginPage.enterRecoveryPage();
        recoveryPage.backToLogin();
        assertTrue(loginPage.getForgotPass().isDisplayed());
    }
}