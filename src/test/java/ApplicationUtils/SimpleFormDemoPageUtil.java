package ApplicationUtils;

import CommonUtils.ExtentReportManager;
import base.BaseTest;
import org.testng.Assert;
import pages.SimpleFormDemoPage;

public class SimpleFormDemoPageUtil extends BaseTest {

    public void verifyTextMessageFunctionality(String expectedMessage){

        //Enter the expected message in the text box
        commonUtil.findElement(SimpleFormDemoPage.userMessageTxb()).sendKeys(expectedMessage);
        ExtentReportManager.logInfo("Message entered in the text box : " + expectedMessage);


        //Verify the actual message matches the expected message
        commonUtil.findElement(SimpleFormDemoPage.getCheckedValueBtn()).click();
        String actualMessage = commonUtil.findElement(SimpleFormDemoPage.actualMessagePara()).getText();

        Assert.assertEquals(actualMessage, expectedMessage, "Message Mismatch");
        ExtentReportManager.logInfo("Verified that message is accurately displayed on right side after clicking button");
    }
}
