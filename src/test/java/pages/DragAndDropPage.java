package pages;

import org.openqa.selenium.By;

public class DragAndDropPage {

    public static By slider(String heading){
        return By.xpath("//h4[normalize-space() = '"+ heading +"']/following-sibling::div/input[@type = 'range']");
    }

    public static By sliderOutput(String heading){
        return By.xpath("//h4[normalize-space() = '"+ heading +"']/following-sibling::div/output");
    }
}
