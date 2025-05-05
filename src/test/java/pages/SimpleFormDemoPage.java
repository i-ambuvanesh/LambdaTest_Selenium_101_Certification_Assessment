package pages;

import org.openqa.selenium.By;

public class SimpleFormDemoPage {

    public static By userMessageTxb(){
        return By.id("user-message");
    }

    public static By getCheckedValueBtn(){
        return By.id("showInput");
    }

    public static By actualMessagePara(){
        return By.id("message");
    }
}
