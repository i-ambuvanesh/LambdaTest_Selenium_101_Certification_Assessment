package tests;

import CommonUtils.ExtentReportManager;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Objects;

public class SimpleFormDemoTests extends BaseTest {

    @Test(groups = {"Scenario_1", "text_message", "selenium_certification"})
    public void verifyTextMessageFunctionality(){

        //Navigate to Form Page and verify the URL contains “simple-form-demo”.
        seleniumPlaygroundPageUtil.navigateToPage("Simple Form Demo");

        String currentUrl = Objects.requireNonNull(getDriver().getCurrentUrl());
        Assert.assertTrue(currentUrl.contains("simple-form-demo"), "URL doesn't contain simple-form-demo text");

        ExtentReportManager.logInfo("Verified that pageUrl contains text : simple-form-demo");

        //Verify the text message is displayed properly
        simpleFormDemoPageUtil.verifyTextMessageFunctionality("Welcome to LambdaTest");
    }
}
