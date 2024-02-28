package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.RecoveryPage;

import static helper.TestValues.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RecoveryPageTest extends BaseTest {

    private static LoginPage loginPage;

    @BeforeEach
    public void setup() {
        loginPage = new LoginPage(driver);
    }

    @Test
    void successfulRecovery() {
        loginPage.enterRecoveryPage();
        assertTrue(new RecoveryPage(driver).enterLoginOrPass(USERNAME).getSuccessfulRecovery().isDisplayed());
    }

    @Test
    void failedRecoveryWithEmptyFields() {
        loginPage.enterRecoveryPage();
        assertTrue(new RecoveryPage(driver).enterLoginOrPass(EMPTY_USERNAME).getFailedRecovery().isDisplayed());
    }

    @Test
    void failedRecoveryWithWrongValues() {
        loginPage.enterRecoveryPage();
        assertTrue(new RecoveryPage(driver).enterLoginOrPass(RANDOM_VALUE(20)).getFailedRecovery().isDisplayed());
    }

    @Test
    void returnToLoginPage() {
        loginPage.enterRecoveryPage();
        new RecoveryPage(driver).backToLogin();
        assertTrue(loginPage.getForgotPass().isDisplayed());
    }
}