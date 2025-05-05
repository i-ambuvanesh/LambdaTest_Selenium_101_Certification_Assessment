package tests;

import base.BaseTest;
import org.testng.annotations.Test;

public class DragAndDropTests extends BaseTest {

    @Test(groups = {"Scenario_2", "drag_n_drop", "selenium_certification"})
    public void dragAndDropSliderTest() {

        //Navigate to Drag and Drop Slider Page
        seleniumPlaygroundPageUtil.navigateToPage("Drag & Drop Sliders");

        //Verify drag and drop functionality is working for the slider with default value 15
        dragAndDropSliderUtils.dragAndDropSliderByArrowKeys("Default value 15", 95);
    }
}
