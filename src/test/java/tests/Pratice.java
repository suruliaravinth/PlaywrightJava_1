package tests;

import base.BaseClass;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ScreenshotUtil;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Pratice extends BaseClass {

    @Test
    public void MaxScreen(){
        page.navigate("http://way2automation.com");
    }

    @Test
    public void AlertTest() throws InterruptedException {
        page.navigate("https://trytestingthis.netlify.app/");
        page.onDialog(dialog -> {
            System.out.println("Alert message: " + dialog.message());
            dialog.accept();
            //dialog.dismiss();
        });
        page.locator("button[onclick='alertfunction()']").click();
        Thread.sleep(5000);
    }

    @Test
    public void FrameTest(){
        page.navigate("https://demo.automationtesting.in/Frames.html");
        //FrameLocator frame = page.frameLocator("iframe[name='SingleFrame']");
        //frame.locator("input[type='text']").fill("sadsad");
        page.waitForTimeout(5000);
        Locator input=page.frameLocator("iframe[name='SingleFrame']").locator("input[type='text']");
        input.fill("sadsad");
        System.out.println("Entered value is :" +input.inputValue());
    }

    @Test
    public void mouseHover(){
        page.navigate("https://www.way2automation.com/");
        page.waitForTimeout(5000);
        page.locator("//i[@class='eicon-close']").click();
        page.locator("//li[@id='menu-item-27580']//span[@class='menu-text'][normalize-space()='All Courses']").hover();
        page.locator("//li[@id='menu-item-27582']//span[@class='menu-text'][normalize-space()='Selenium']").hover();
        page.locator("//li[@id='menu-item-27583']//span[@class='menu-text'][normalize-space()='Selenium with Java']").click();
    }
    @Test
    public void DragAndDrop(Method method) throws InterruptedException {
        page.navigate("https://trytestingthis.netlify.app/");
        Thread.sleep(5000);
        ScreenshotUtil.takeScreenshotUtility(page,method.getName() + "_before");
        page.locator("//img[@id='drag1']").dragTo(page.locator("//div[@id='div1']"));
        Thread.sleep(5000);
        ScreenshotUtil.takeScreenshotUtility(page,method.getName() + "_after");
    }
    @Test
    public void KeywordAction(){
        page.navigate("https://trytestingthis.netlify.app/");
        page.locator("//input[@id='fname']").fill("Suruli Aravinth");
        page.keyboard().press("Control+A");
        page.keyboard().press("Control+C");
        page.keyboard().press("Tab");
        page.keyboard().press("Control+V");
/*
        page.keyboard().down("Control");
        page.keyboard().press("a");
        page.keyboard().up("Control");

        page.keyboard().down("Control");
        page.keyboard().press("c");
        page.keyboard().up("Control");

        page.keyboard().press("Tab");

        page.keyboard().down("Control");
        page.keyboard().press("v");
        page.keyboard().up("Control");*/
    }
    @Test
    public void HandleSlidder(){
        page.navigate("https://jqueryui.com/slider/");
        FrameLocator frame = page.frameLocator(".demo-frame");
        Locator slidder=frame.locator("//span[contains(@class,'ui-slider-handle')]");
        slidder.focus();
        for (int i=0;i<15;i++){
            page.keyboard().press("ArrowRight");
        }
    }
    @Test
    public void downloadtest() throws IOException {
        page.navigate("https://the-internet.herokuapp.com/download");
        Download download = page.waitForDownload(() -> {
            page.locator("//a[normalize-space()='testfile.txt']").click();
        });
        String downloadPath = System.getProperty("user.dir")+ "/downloadfiles/" + download.suggestedFilename();
        download.saveAs(Paths.get(downloadPath));
        if (downloadPath.endsWith(".txt")){
            System.out.println("File Extension verified");
        }else {
            System.out.println("File Extension failed");
        }
        if (Files.size(Path.of(downloadPath))>0){
            System.out.println("File Size verified");
        }else {
            System.out.println("File size not verified");
        }
        String readFile = Files.readString(Path.of(downloadPath));
        if (readFile.contains("sample test")){
            System.out.println("File content verified");
        }else {
            System.out.println("File content not verified");
        }
    }
    @Test
    public void dynamicWait(){
        page.navigate("https://seleniumpractise.blogspot.com/2016/08/how-to-use-explicit-wait-in-selenium.html");
        page.locator("//button[@onclick='timedText()']").click();
        page.locator("//p[text()='WebDriver']").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(20000));
        System.out.println("Dynamic wait completed: "+page.locator("//p[text()='WebDriver']").isVisible());
    }
    @Test
    public void JSExecutor(){
        page.navigate("https://login.yahoo.com/");
        //page.evaluate("document.getElementById(\"persistent\").click()");
        Locator checkBox = page.locator("#persistent");
        checkBox.evaluate("Checkbox => Checkbox.click()");
    }
    @Test
    public void table(){
        page.navigate("https://datatables.net/");
        Locator locatorRow = page.locator("table#example tr");
        locatorRow.locator(":scope",new Locator.LocatorOptions().setHasText("Ashton Cox")).locator(".select-checkbox").click();
        locatorRow.locator(":scope").allInnerTexts().forEach(e->System.out.println(e));
    }
    @Test
    public void Auth(){
        page.navigate("https://www.automationexercise.com/");
        page.locator("//a[normalize-space()='Signup / Login']").click();
        page.locator("//input[@data-qa='login-email']").fill("sss.aravinth@gmail.com");
        page.locator("[name=\"password\"]").fill("Suruli@30");
        page.locator("button[data-qa='login-button']").click();
        browserContext.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("appLoginState.json")));
    }
    @Test
    public void AuthLogin(){
        browserContext= browser.newContext((new Browser.NewContextOptions().setViewportSize(null).setStorageStatePath(Paths.get("appLoginState.json"))));
        page = browserContext.newPage();
        page.navigate("https://www.automationexercise.com/");
        System.out.println("Auth Login completed");
        String actualResult = page.getByText("Logged in as Suruli Aravinth S").textContent().trim();
        Assert.assertEquals(actualResult,"Logged in as Suruli Aravinth S");
        System.out.println("Validation completed");
    }
}
