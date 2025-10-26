package tests;

import base.BaseClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class loginTest1 extends BaseClass {
    @Test
    public void verifyLoginPage() {
        LoginPage loginPage = new LoginPage(page);
        HomePage homePage = new HomePage(page);
        test.info("Navigating to login page");
        homePage.clickSignUpLoginLink();
        //loginPage.loginPageActions("sss.aravinth@gmail.com","Suruli@30");
        test.info("Entering Email id details");
        loginPage.enterEmail("sss.aravinth@gmail.com");
        test.info("Entering Password details");
        loginPage.enterPassword("Suruli@30");
        test.info("Clicking login button");
        loginPage.clickLoginButton();

    }
}
