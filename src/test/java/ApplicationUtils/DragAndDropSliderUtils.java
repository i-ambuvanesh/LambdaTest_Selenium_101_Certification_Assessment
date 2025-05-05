package ApplicationUtils;

import CommonUtils.ExtentReportManager;
import base.BaseTest;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.DragAndDropPage;

import java.util.Objects;

public class DragAndDropSliderUtils extends BaseTest {

    public void dragAndDropSliderByArrowKeys(String label, int value) {

        //Fetch the Default value of the slider
        WebElement slider = commonUtil.findElement(DragAndDropPage.slider(label));
        int defaultValue = Integer.parseInt(Objects.requireNonNull(slider.getDomProperty("value")));
        ExtentReportManager.logInfo("Default Slider value : " + defaultValue);


        //Calculate how much to shift
        int shiftCount = value - defaultValue;

        if(shiftCount > 0)
            for(int i = 1 ; i <= shiftCount ; i++) {
                commonUtil.sleep(100);
                slider.sendKeys(Keys.ARROW_RIGHT);
            }

        else
            for(int i = 1 ; i <= Math.abs(shiftCount) ; i++) {
                commonUtil.sleep(100);
                slider.sendKeys(Keys.ARROW_LEFT);
            }

        //Verify the Final Value is matching the expected Value
        int actualValue = Integer.parseInt(commonUtil.findElement(DragAndDropPage.sliderOutput(label)).getText().trim());
        Assert.assertEquals(actualValue, value, "Final Slider Position Mismatch");
        ExtentReportManager.logInfo("Verified that slider is dragged to the final value of : " + value);
    }
}
