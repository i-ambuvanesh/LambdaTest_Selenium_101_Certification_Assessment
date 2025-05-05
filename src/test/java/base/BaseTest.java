package base;

import ApplicationUtils.DragAndDropSliderUtils;
import ApplicationUtils.InputFormUtils;
import ApplicationUtils.SeleniumPlaygroundPageUtil;
import ApplicationUtils.SimpleFormDemoPageUtil;
import CommonUtils.CommonUtil;
import Constants.FrameworkConstants;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;
import java.time.Duration;

public class BaseTest {

    //Made the driver as ThreadLocal to ensure safe Parallel Testing
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected static Dotenv dotenv = Dotenv.load();

    protected static ThreadLocal<FluentWait<WebDriver>> fluentWait = new ThreadLocal<>();
    protected static ThreadLocal<WebDriverWait> explicitWait = new ThreadLocal<>();


    //Util Objects (non-static objects as these will require driver instance
    protected static CommonUtil commonUtil;
    protected static SeleniumPlaygroundPageUtil seleniumPlaygroundPageUtil;
    protected static SimpleFormDemoPageUtil simpleFormDemoPageUtil;
    protected static DragAndDropSliderUtils dragAndDropSliderUtils;
    protected static InputFormUtils inputFormUtils;


    //Getter Method to get driver
    public static WebDriver getDriver() {

        return ThreadGuard.protect(driver.get());
    }


    @BeforeSuite
    public void setupUtilObjects(){

        commonUtil = new CommonUtil();
        seleniumPlaygroundPageUtil = new SeleniumPlaygroundPageUtil();
        simpleFormDemoPageUtil = new SimpleFormDemoPageUtil();
        dragAndDropSliderUtils = new DragAndDropSliderUtils();
        inputFormUtils = new InputFormUtils();
    }


    @Parameters({"browser", "platform", "version"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method, String browser, String platform, String version){

        LambdaTestManager.initializeDriver(method, browser, platform, version);
        getDriver().manage().window().maximize();

        fluentWait.set(new FluentWait<>(getDriver())
                .withTimeout(Duration.ofSeconds(FrameworkConstants.TIME_OUT_IN_SECONDS))
                .pollingEvery(Duration.ofMillis(FrameworkConstants.POLLING_WAIT_IN_MILLIS))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class));

        explicitWait.set(new WebDriverWait(getDriver(), Duration.ofSeconds(FrameworkConstants.TIME_OUT_IN_SECONDS)));
    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}