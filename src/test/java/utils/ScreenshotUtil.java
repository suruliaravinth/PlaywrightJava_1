package utils;

import com.microsoft.playwright.Page;

import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.nio.file.Path;

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

    public static byte[] takeScreenshotUtility(Page page, String testName) {
        try {
            File dir = new File("test-output/screenshots");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String timestamp = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
            String fileName = testName + "_" + timestamp + ".png";
            Path path = Paths.get("test-output/screenshots", fileName);

            // Single screenshot → save + get bytes
            byte[] screenshotBytes = page.screenshot(
                    new Page.ScreenshotOptions()
                            .setFullPage(true)
                            .setPath(path)
            );
            return screenshotBytes;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
