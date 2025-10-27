package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitUntilState;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ExtendManager;
import utils.ScreenshotUtil;

import java.io.File;
import java.lang.reflect.Method;

public class BaseClass {
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;
    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeMethod
    public void setUp(Method method) {
        extent = ExtendManager.getInstance();
        test = extent.createTest(method.getName());

        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome").setSlowMo(5000));
        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1920, 1080));
        page=context.newPage();
/*        page.setDefaultNavigationTimeout(60000);
        page.navigate("https://www.automationexercise.com/",
                new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));*/
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        if (result.getStatus()==ITestResult.FAILURE){
            test.fail(result.getThrowable());

            String screenshotPath= ScreenshotUtil.takeScreenshot(page,result.getName());

            System.out.println("Screenshot path is : " +screenshotPath);
            String projectPath = System.getProperty("user.dir");

            String absoluteScreenshotPath = projectPath +"/"+ screenshotPath;
            String NewabsoluteScreenshotPath = absoluteScreenshotPath.replace("\\", "/");
            System.out.println("Absolute Screenshot path is : " +NewabsoluteScreenshotPath);

            test.addScreenCaptureFromPath(NewabsoluteScreenshotPath,"screenshot");
        }else if (result.getStatus()==ITestResult.SUCCESS){
            test.pass("Test passed");
        }else {
            test.skip("Test skipped");
        }
        extent.flush();

        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}
