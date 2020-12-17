package com.tuttur.page;

import com.tuttur.constants.ForgotPass_Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;
import java.util.List;

public class ForgotPassPage extends ForgotPass_Constants {
    public ForgotPassPage(WebDriver driver) {
        super(driver);
    }
    Actions actions = new Actions(driver);

    private void setSSN () {
        setObjectBy(SSN,"17376674056");
    }
    private void selectDay () {
       WebElement days =  findElements(BIRTHDATE).get(0);
       Select select = new Select(days);
       select.selectByVisibleText("20");
    }
    private void selectMonth () {
        WebElement months = findElements(BIRTHDATE).get(1);
        Select select = new Select(months);
        select.selectByVisibleText("Nisan");
    }
    private void selectYear () {
        WebElement years = findElements(BIRTHDATE).get(2);
        Select select = new Select(years);
        select.selectByVisibleText("1991");
    }
    private void setBirthdate () {
        waitForElement(driver,MIN_WAIT_4_ELEMENT,BIRTHDATE);
        selectDay();
        selectMonth();
        selectYear();
    }

    /**
     * Şifremi unuttum ekranındaki checkboxlar için
     * İndex = 1 sms checkbox
     * index = 2 mail checkbox
     * @param index
     * @return
     */
    private WebElement checkbox (int index ) {

        return findElements(SEND_SMS_CHECKBOX).get(index);
    }


    public ForgotPassPage forgotPassAction () {
        setSSN();
        setBirthdate();
        waitForElement(driver,MIN_WAIT_4_ELEMENT,RESET_PASS);
        clickObjectBy(RESET_PASS);
        waitForElement(driver,DEFAULT_WAIT_4_ELEMENT,SEND_SMS_CHECKBOX);
        checkbox(1).click();
        return this;

    }


}
