package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.HashMap;


public class LambdaTestManager {

    public static String hubURL = "https://hub.lambdatest.com/wd/hub";

    @Parameters
    public static DesiredCapabilities setLambdaTestCapabilities(Method method, String browser, String platform, String version){

        DesiredCapabilities browserOptions = new DesiredCapabilities();
        browserOptions.setCapability("browserName", browser);
        browserOptions.setCapability("browserVersion", version);

        HashMap<String, Object> ltOptions = new HashMap<>();

        ltOptions.put("username", BaseTest.dotenv.get("LT_USERNAME"));
        ltOptions.put("accessKey", BaseTest.dotenv.get("LT_ACCESS_KEY"));

        ltOptions.put("platformName", platform);
        ltOptions.put("visual", true);
        ltOptions.put("video", true);
        ltOptions.put("network", true);
        ltOptions.put("console", "true");
        ltOptions.put("w3c", true);
        ltOptions.put("resolution", "1920x1080");

        ltOptions.put("build", "Selenium_101_Certification_Test");
        ltOptions.put("project", "LambdaTest_Selenium_101_Certification_Assessment");
        ltOptions.put("smartUI.project", "LambdaTest_Selenium_101_Certification_Assessment");

        String testName = method.getName() + "_" + platform + "_" + browser;
        ltOptions.put("name", testName);

        browserOptions.setCapability("LT:Options", ltOptions);

        return browserOptions;
    }


    public static ChromeOptions setLocalTestCapabilities(){

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        return options;
    }


    public static void initializeDriver(Method method, String browser, String platform, String version){

        String runMode = BaseTest.dotenv.get("RUN_MODE", "local");

        if(runMode.equalsIgnoreCase("lambda_test")) {

            try {
                BaseTest.driver.set(new RemoteWebDriver(URI.create(hubURL).toURL(), setLambdaTestCapabilities(method, browser, platform, version)));

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Driver initialized");
        }

        else {

            WebDriverManager.chromedriver().setup();
            BaseTest.driver.set(new ChromeDriver(setLocalTestCapabilities()));
        }
    }
}
