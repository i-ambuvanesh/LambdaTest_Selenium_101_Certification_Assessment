package pages;

import org.openqa.selenium.By;

public class SeleniumPlaygroundPage {

    public static By pageLinks(String text){
        return By.xpath("//li/a[text() = '"+ text +"']");
    }
}
