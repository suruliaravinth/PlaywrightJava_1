package tests;

import base.BaseClass;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

public class locatorsDemo extends BaseClass {
    @Test
    public void testAllLocators(){
        page.setDefaultNavigationTimeout(60000);
        page.navigate("https://trytestingthis.netlify.app/");
        // #id locator attribute
        page.locator("#fname").fill("Aravinth");
        // input name attribute & value locator
        page.locator("input[name='lname']").fill("S");
        //Radio button using value locator
        page.locator("input[value='male']").check();
        //Radio button using nth index
        page.locator("input[type='radio']").nth(1).check();
        //Dropdown using selectOption
        page.locator("select#option").selectOption("option 2");
        //Dropdown using Multiple selectOption
        page.locator("select#owc").selectOption("option 3");
        page.locator("select#owc").selectOption("option 1");
        //Checkbox using value locator
        page.locator("input[type='checkbox'][value='Option 3']").check();
        page.locator("input[name='option1']").check();
        page.locator("input[name='option2']").check();
        //Checkbox using label text
        page.getByLabel("Option 2").check();
        //Checkbox using nth index
        page.locator("input[type='checkbox']").nth(0).check();
        //Xpath date
        page.locator("//input[@type='date']").fill("2024-12-31");
        //Button using text locator
        page.getByText("Click Me").click();
        //Button using role locator
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
        //Recording pause
        //page.pause();

    }
}
