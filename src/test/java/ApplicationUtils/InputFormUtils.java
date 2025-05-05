package ApplicationUtils;

import CommonUtils.ExtentReportManager;
import base.BaseTest;
import com.github.javafaker.Faker;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import pages.InputFormPage;

import java.util.Objects;

public class InputFormUtils extends BaseTest {

    public void verifyFormSubmitFunctionality(Faker faker){

        //Fill the Form Data
        commonUtil.findElement(InputFormPage.inputTxb("Name")).sendKeys(faker.name().fullName());
        commonUtil.findElement(InputFormPage.inputTxb("Email")).sendKeys(faker.internet().emailAddress());
        commonUtil.findElement(InputFormPage.inputTxb("Password")).sendKeys(faker.internet().password());
        commonUtil.findElement(InputFormPage.inputTxb("Company")).sendKeys(faker.company().name());
        commonUtil.findElement(InputFormPage.inputTxb("Website")).sendKeys(faker.internet().url());
        commonUtil.selectDropDwnOption(InputFormPage.dropDown("Country"), "United States","text");
        commonUtil.findElement(InputFormPage.inputTxb("City")).sendKeys(faker.address().city());
        commonUtil.findElement(InputFormPage.inputTxb("Address 1")).sendKeys(faker.address().fullAddress());
        commonUtil.findElement(InputFormPage.inputTxb("Address 2")).sendKeys(faker.address().secondaryAddress());
        commonUtil.findElement(InputFormPage.inputTxb("State")).sendKeys(faker.address().state());
        commonUtil.findElement(InputFormPage.inputTxb("Zip code")).sendKeys(faker.address().zipCode());

        ExtentReportManager.logInfo("Form details filled");

        //Click submit and Verify success message
        commonUtil.findElement(InputFormPage.submitBtn()).click();
        Assert.assertTrue(commonUtil.isDisplayed(InputFormPage.successMsg("Thanks for contacting us, we will get back to you shortly.")), "Success Message not displayed after submitting form Data");
        ExtentReportManager.logInfo("Verified that after filling all details and submitting form, success message is displayed");
    }

    public void verifyEmptyFormSubmitFunctionality(){

        //Click submit and Verify popup
        commonUtil.findElement(InputFormPage.submitBtn()).click();

        // Check if the field is valid using JavaScript
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        Boolean isValid = (Boolean) js.executeScript("return arguments[0].reportValidity();", commonUtil.findElement(InputFormPage.inputTxb("Name")));

        Assert.assertFalse(Objects.requireNonNull(isValid), "Error Popup not displayed after trying to submit Empty form");
        ExtentReportManager.logInfo("Verified that Error popup is displayed when trying to submit empty form");
    }
}
