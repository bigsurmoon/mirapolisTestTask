package pagetests;

import base.BaseSeleniumTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.RecoveryPage;

import static helper.TestValues.*;

public class RecoveryPageTest extends BaseSeleniumTest {

    @Test
    public void successfulRecovery() {
        LoginPage loginPage = new LoginPage();
        loginPage.enterRecoveryPage();
        RecoveryPage recoveryPage = new RecoveryPage();
        recoveryPage.enterLoginOrPass(USERNAME);
        Assert.assertTrue(recoveryPage.getSuccessfulRecovery().isDisplayed());
    }

    @Test
    public void failedRecoveryWithEmptyFields() {
        LoginPage loginPage = new LoginPage();
        loginPage.enterRecoveryPage();
        RecoveryPage recoveryPage = new RecoveryPage();
        recoveryPage.enterLoginOrPass(EMPTY_USERNAME);
        Assert.assertTrue(recoveryPage.getFailedRecovery().isDisplayed());
    }

    @Test
    public void failedRecoveryWithWrongValues() {
        LoginPage loginPage = new LoginPage();
        loginPage.enterRecoveryPage();
        RecoveryPage recoveryPage = new RecoveryPage();
        recoveryPage.enterLoginOrPass(RANDOM_VALUE(20));
        Assert.assertTrue(recoveryPage.getFailedRecovery().isDisplayed());
    }

    @Test
    public void returnToLoginPage() {
        LoginPage loginPage = new LoginPage();
        loginPage.enterRecoveryPage();
        RecoveryPage recoveryPage = new RecoveryPage();
        recoveryPage.backToLogin();
        Assert.assertTrue(loginPage.getForgotPass().isDisplayed());
    }
}