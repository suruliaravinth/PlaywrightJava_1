package tests;

import base.BaseClass;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.WaitUntilState;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.util.regex.Pattern;

public class loginTest1 extends BaseClass {

    @Test
    public void verifyLoginPage() {
        LoginPage loginPage = new LoginPage(page);
        HomePage homePage = new HomePage(page);
        page.setDefaultNavigationTimeout(60000);
        page.navigate("https://www.automationexercise.com/",
                new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
        test.info("Navigating to login page");
        homePage.clickSignUpLoginLink();
        //loginPage.loginPageActions("sss.aravinth@gmail.com","Suruli@30");
        test.info("Entering Email id details");
        loginPage.enterEmail("sss.aravinth@gmail.com");
        test.info("Entering Password details");
        loginPage.enterPassword("Suruli@30");
        test.info("Clicking login button");
        loginPage.clickLoginButton();
        PlaywrightAssertions.assertThat(page).hasURL(Pattern.compile("automationexercise"));
        test.info("URL Validation");
    }
}
