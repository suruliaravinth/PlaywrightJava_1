package tests;

import base.BaseClass;
import com.microsoft.playwright.FrameLocator;
import org.testng.annotations.Test;
import utils.ScreenshotUtil;

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
}
