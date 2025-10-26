package tests;

import base.BaseClass;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitUntilState;
import org.testng.annotations.Test;

public class FirstTest extends BaseClass {
    @Test
    public void verifyHomePageTitle() {
        page.setDefaultNavigationTimeout(60000);
        page.navigate("https://www.automationexercise.com/",
                new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
        System.out.println("Page title is : "+page.title());
    }
}