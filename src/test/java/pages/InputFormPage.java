package pages;

import org.openqa.selenium.By;

public class InputFormPage {

    public static By inputTxb(String placeHolder){

        return By.xpath("//input[@placeholder = '"+ placeHolder +"']");
    }

    public static By dropDown(String label){

        return By.xpath("//label[contains(text(), '"+ label +"')]/following-sibling::select");
    }

    public static By submitBtn(){
        return By.xpath("//button[@type = 'submit'][text() = 'Submit']");
    }

    public static By successMsg(String text){

        return By.xpath("//p[contains(@class, 'success-msg')][text() = '"+ text +"']");
    }
}
