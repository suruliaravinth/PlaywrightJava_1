package tests;

import base.BaseClass;
import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.annotations.Test;
import utils.ScreenshotUtil;

import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class freelanceSignUp extends BaseClass {
    @Test
    public void SignUpTest() {
        page.navigate("https://freelance-learn-automation.vercel.app/login");
        page.getByText("New user? Signup").click();
        assertThat(page.locator("text=Learn Automation Courses").nth(0)).isVisible();
        assertThat(page.locator(".submit-btn")).isDisabled();
        page.locator("#name").fill("Test User");
        page.getByPlaceholder("Email").fill("Automation"+System.currentTimeMillis()+"@gmail.com");
        page.getByPlaceholder("Password").fill("Test@1234");
        //page.getByPlaceholder("Password").type("Test", new Locator.TypeOptions().setDelay(100));
        assertThat(page.getByPlaceholder("Password")).hasValue("Test@1234");
        assertThat(page.getByPlaceholder("Password")).hasValue(Pattern.compile("Test"));
        //Pause for debugging
        //page.pause();
        page.locator("input[type='checkbox']").nth(4).check();
        assertThat(page.locator("input[type='checkbox']").nth(4)).isChecked();
        page.locator("input[value='Female']").click();
        page.locator("#state").selectOption("Goa");
        Locator options = page.locator("//select[@id='state']/option");
        List<String> allTexts = options.allInnerTexts();
        for (String text : allTexts) {
            System.out.println(text);
        }
        String hobbies[] = {"Playing", "Swimming"};
        page.locator("#hobbies").selectOption(hobbies);
        assertThat(page.locator(".submit-btn")).isEnabled();
        page.locator(".submit-btn").click();
    }
    @Test
    public void AutoSuggestionTest(){
        page.navigate("https://www.google.com/");
        page.locator("//textarea[@title='Search']").fill("Aravinth");

        Locator locatorList = page.locator("//ul[@role='listbox']//li");
        System.out.println("Count value:"+locatorList.count());
        for (int i=0; i<locatorList.count(); i++){
            String suggestionText = locatorList.nth(i).innerText();
            System.out.println("Suggestion "+i+" : "+suggestionText);
            if (suggestionText.equalsIgnoreCase("aravinth")){
                locatorList.nth(i).click();
                break;
            }
        }
    }
    @Test
    public void fileUploadTest(Method method){
        page.navigate("https://trytestingthis.netlify.app/");
        page.locator("#myfile").setInputFiles(Path.of(System.getProperty("user.dir")+"/Files/Sample.png"));
        //page.setInputFiles("#myfile",Paths.get(System.getProperty("user.dir")+"/Files/Sample.png"));
        ScreenshotUtil.takeScreenshotUtility(page,method.getName() + "_before");
        page.setInputFiles("#myfile",new Path[0]);
        page.waitForTimeout(3000);
        ScreenshotUtil.takeScreenshotUtility(page,method.getName() + "_after");
    }

    @Test
    public void fileUploadTest1(){
        page.navigate("https://the-internet.herokuapp.com/upload");
        FileChooser fileChooser=page.waitForFileChooser(()->page.locator("#drag-drop-upload").click());
        fileChooser.setFiles(Paths.get(System.getProperty("user.dir")+"/Files/Sample.png"));
        Path[] uploadedFiles = {
                Paths.get(System.getProperty("user.dir")+"/Files/Sample.png")
        };
        fileChooser.setFiles(uploadedFiles);
    }

    @Test
    public void alertTest(){
        page.navigate("https://the-internet.herokuapp.com/");
        page.getByText("JavaScript Alerts").click();
        page.onDialog(dialog -> {
            System.out.println("Alert message: " + dialog.message());
            dialog.accept("Suruli Aravinth");
        });
        page.getByText("Click for JS Prompt").click();
    }

    @Test
    public void MultipleWindowTest(){
        page.navigate("https://the-internet.herokuapp.com/");
        page.getByText("Multiple Windows").click();
        page.getByText("Click Here").click();
        System.out.println("Parent Page Title: "+page.title());
        for (Page p : page.context().pages()) {
            if (!p.equals(page)) {
                System.out.println("Child Page Title: "+p.title());
            }
        }
    }

    @Test
    public void MultipleWindow1AlertTest(){
        page.navigate("https://www.hyrtutorials.com/p/window-handles-practice.html");
        Page newPage = page.waitForPopup(() -> {
            page.locator("//button[@id='newTabBtn']").click();
        });
        newPage.onDialog(dialog -> {
            System.out.println("Alert message: " + dialog.message());
            dialog.accept("Suruli Aravinth");
        });
        newPage.locator("#promptBox").click();
        newPage.waitForTimeout(3000);
        String resultText = newPage.locator("#output").textContent();
        System.out.println("Result on page: " + resultText);
        newPage.bringToFront();
        newPage.waitForTimeout(5000);
    }

    @Test
    public void shadowDomTest(){
       page.navigate("https://selectorshub.com/xpath-practice-page/");
       Locator shadowRoot=page.locator("div#userName");
        shadowRoot.locator("#kils").fill("Suruli Aravinth S");
    }
    @Test
    public void HandlingWebTables(){
        page.navigate("https://money.rediff.com/indices/nse/NIFTY-50?src=moneyhome_nseIndices");
        //Row count
        System.out.println(page.locator(".dataTable > tbody").locator("tr").count());
        //col count
        System.out.println(page.locator(".dataTable > tbody").locator("tr:first-child").locator("td").count());
        assertThat(page.locator(".dataTable > tbody").locator("tr:first-child").locator("td:nth-child(2)")).hasText("Nifty");
        page.locator(".dataTable > tbody").allInnerTexts().forEach(text -> System.out.println(text));
    }
}
