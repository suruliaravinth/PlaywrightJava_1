package tests;

import base.BaseClass;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

public class LoginTest extends BaseClass {
    @Test
    public void verifyLoginPage() {
        page.setDefaultNavigationTimeout(60000);
        page.navigate("https://www.automationexercise.com/");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" Signup / Login")).click();
        page.locator("form").filter(new Locator.FilterOptions().setHasText("Login")).getByPlaceholder("Email Address").click();
        page.locator("form").filter(new Locator.FilterOptions().setHasText("Login")).getByPlaceholder("Email Address").fill("sss.aravinth@gmail.com");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).click();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).fill("Suruli@30");
        //assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"))).isVisible();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
        //assertThat(page.locator("#header")).containsText("Logged in as Suruli Aravinth S");
    }
}
