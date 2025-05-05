package ApplicationUtils;

import CommonUtils.ExtentReportManager;
import Constants.FrameworkConstants;
import base.BaseTest;
import pages.SeleniumPlaygroundPage;

public class SeleniumPlaygroundPageUtil extends BaseTest {

    public void navigateToPage(String text){

        getDriver().get(FrameworkConstants.URL);
        commonUtil.findElement(SeleniumPlaygroundPage.pageLinks(text)).click();
        commonUtil.waitForPageToLoad();
        ExtentReportManager.logInfo("Navigated to Page : " + text);
    }
}
