package CommonUtils;

import base.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.Objects;

public class CommonUtil extends BaseTest {


    /** Find an Element using Fluent Wait */
    public WebElement findElement(By locator) {

        return fluentWait.get().until(driver -> {

            WebElement element = driver.findElement(locator);
            if (element.isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView({ behavior: 'auto', block: 'center', inline: 'center' });",
                        element
                );
                return element;
            }
            return null;
        });
    }

    /** Check if a element is displayed on the webpage or not */
    public boolean isDisplayed(By locator) {
        try {
            WebElement element = findElement(locator);
            return element != null && element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    /** Method for Select based dropdown options */
    public void selectDropDwnOption(By locator, String value, String type){

        Select drpDwn = new Select(findElement(locator));

        switch (type.toLowerCase()){

            case "text": drpDwn.selectByVisibleText(value);
                         break;
            case "index": drpDwn.selectByIndex(Integer.parseInt(value));
                         break;
            case "value": drpDwn.selectByValue(value);
                         break;
        }
    }

    /** Explicitly wait until page is loaded */
    public void waitForPageToLoad() {
        explicitWait.get().until(webDriver ->
                Objects.equals(((JavascriptExecutor) webDriver).executeScript("return document.readyState"), "complete")
        );
    }


    /** Wait for desired time */
    public void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Sleep interrupted: " + e.getMessage());
        }
    }
}
