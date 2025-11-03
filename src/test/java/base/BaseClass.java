package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import utils.ExtendManager;
import utils.ScreenshotUtil;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.List;

public class BaseClass {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext browserContext;
    protected Page page;
    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeMethod
    public void setUp(Method method) {
        extent = ExtendManager.getInstance();
        test = extent.createTest(method.getName());

        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome").setSlowMo(5000).setArgs(List.of("--start-maximized")));
        browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(null).setRecordVideoSize(1920,1080).setRecordVideoDir(Paths.get("test-output/videos")));
        page=browserContext.newPage();
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.FAILURE) {
                test.fail(result.getThrowable());
                test.info("Page URL at failure: " + page.url());

                String screenshotPath = ScreenshotUtil.takeScreenshot(page, result.getName());
                test.addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                test.pass("Test passed successfully");
            } else {
                test.skip("Test skipped");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (browserContext != null) browserContext.close();
            if (browser != null) browser.close();
            if (playwright != null) playwright.close();
        }
    }
    @AfterSuite
    public void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
