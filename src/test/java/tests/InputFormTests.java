package tests;

import base.BaseTest;
import com.github.javafaker.Faker;
import org.testng.annotations.Test;


public class InputFormTests extends BaseTest {

    @Test(groups = {"Scenario_3", "input_form", "selenium_certification"})
    public void verifyInputFormFunctionality() {

        //Use Faker Library to generate random test data
        Faker testProfileData = new Faker();

        seleniumPlaygroundPageUtil.navigateToPage("Input Form Submit");
        inputFormUtils.verifyEmptyFormSubmitFunctionality();
        inputFormUtils.verifyFormSubmitFunctionality(testProfileData);
    }
}
