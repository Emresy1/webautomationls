package com.tuttur.page;

import com.tuttur.configs.PropertiesFile;
import com.tuttur.constants.ForgotPass_Constants;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertTrue;


import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class ForgotPassPage extends ForgotPass_Constants {
    public ForgotPassPage(WebDriver driver) throws IOException {
        super(driver);
    }


    Actions actions = new Actions(driver);
    DbQueriesPage db = new DbQueriesPage(driver);
    PropertiesFile prop = new PropertiesFile(driver);
    GeneralPage general = new GeneralPage(driver);

    private void setSSN() {
        setObjectBy(SSN, "17376674056");
    }

    private void selectDay() {
        WebElement days = findElements(BIRTHDATE).get(0);
        Select select = new Select(days);
        select.selectByVisibleText("20");
    }

    private void selectMonth() {
        WebElement months = findElements(BIRTHDATE).get(1);
        Select select = new Select(months);
        select.selectByVisibleText("Nisan");
    }

    private void selectYear() {
        WebElement years = findElements(BIRTHDATE).get(2);
        Select select = new Select(years);
        select.selectByVisibleText("1991");
    }

    private void setBirthdate() {
        waitForElement(driver, MIN_WAIT_4_ELEMENT, BIRTHDATE);
        selectDay();
        selectMonth();
        selectYear();
    }

    /**
     * Şifremi unuttum ekranındaki checkboxlar için
     * İndex = 1 sms checkbox
     * index = 2 mail checkbox
     *
     * @param index
     * @return
     */
    private WebElement checkbox(int index) {

        return findElements(SEND_SMS_CHECKBOX).get(index);
    }


    public LoginPage forgotPassAction() {
        setSSN();
        setBirthdate();
        waitForElement(driver, MIN_WAIT_4_ELEMENT, RESET_PASS);
        clickObjectBy(RESET_PASS);
        sleep(2);
        checkbox(1).click();
        clickObjectBy(By.cssSelector(".medium.primary.w-100"));
        sleep(1);
        buttonClose().click();
        return new LoginPage(driver);

    }

    public WebElement buttonClose() {

        waitForElement(driver, OPT_WAIT_4_ELEMENT, By.cssSelector(".medium.primary"));
        return getElemenstBy(By.cssSelector(".medium.primary"), 1);
    }

    public ForgotPassPage setPasswordChange() throws IOException {

        setObjectBy(PASSWORD_INPUT, general.newPasswordChange);
        clickObjectBy(CHANGE_BUTTON);
        switchToWindows();
        checkPasswordChange();
        return this;

    }

    private void checkPasswordChange() throws IOException {

        assertTrue("şifre değişikliği başarısız", getElementBy(SUCCESS_MESSAGE)
                .getText().substring(0, 26).equals(prop.getObject("success_pass_change_message")));

    }

}


