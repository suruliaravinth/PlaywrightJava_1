package utils;

import com.microsoft.playwright.Page;

import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    public static String takeScreenshot(Page page, String testName) {
        try {
            File screenshotDir = new File("test-output/screenshots");
            if (!screenshotDir.exists()) {
                screenshotDir.mkdirs();
            }
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String path = "test-output/screenshots/" + testName + "_" + timestamp + ".png";

            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get(path))
                    .setFullPage(false));

            return Paths.get(path).toAbsolutePath().toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] takeScreenshotUtility(Page page) {
        SimpleDateFormat dateFormat= new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
        Date date= new Date();
        String timestamp = dateFormat.format(date);
        byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true).setPath(Paths.get("test-output/screenshots/screenshot_" + timestamp + ".png")));
        return screenshot;
    }
}
