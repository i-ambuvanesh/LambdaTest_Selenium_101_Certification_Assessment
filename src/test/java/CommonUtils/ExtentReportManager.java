package CommonUtils;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ExtentReportManager extends BaseTest {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();


    public static void initReports(String reportPath) {

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK);  // Set theme (can be 'STANDARD' or 'DARK')
        sparkReporter.config().setDocumentTitle("Test Execution Report");
        sparkReporter.config().setReportName("TestNG Automation Report");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    public static void startTest(String testName) {
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
    }

    public static void logInfo(String message) {
        String base64Screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
        getTest().info(message).addScreenCaptureFromBase64String(base64Screenshot, message);

        System.out.println(Thread.currentThread().getName() + " : " + message);
    }

    public static void logTextInfo(String message) {
        getTest().info(message);
        System.out.println(Thread.currentThread().getName() + " : " + message);
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void markTestPassed() {
        getTest().pass("Test Passed");
    }

    public static void markTestFailed(Throwable throwable) {
        getTest().fail("Test Failed : " + throwable.getMessage());
    }

    public static void markTestSkipped() {
        getTest().fail("Test Skipped");
    }

    public static void flushReports() {
        extent.flush();
    }
}

