package tests;

import base.BaseClass;
import com.microsoft.playwright.Download;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.testng.annotations.Test;
import utils.ScreenshotUtil;

import java.io.File;
import java.io.IOException;
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
        page.frameLocator("iframe[name='SingleFrame']").locator("input[type='text']").fill("sadsad");
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
    public void DragAndDrop() throws InterruptedException {
        page.navigate("https://trytestingthis.netlify.app/");
        Thread.sleep(5000);
        ScreenshotUtil.takeScreenshotUtility(page);
        page.locator("//img[@id='drag1']").dragTo(page.locator("//div[@id='div1']"));
        Thread.sleep(5000);
        ScreenshotUtil.takeScreenshotUtility(page);
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
            page.locator("//a[normalize-space()='TextFile.txt']").click();
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
}
