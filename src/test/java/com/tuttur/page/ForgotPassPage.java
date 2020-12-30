package com.tuttur.page;

import com.tuttur.base.BaseTest;
import com.tuttur.configs.PropertiesFile;
import com.tuttur.constants.ForgotPass_Constants;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ForgotPassPage extends ForgotPass_Constants {
    public ForgotPassPage(WebDriver driver) throws IOException {
        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(driver,2000);
    PropertiesFile prop = new PropertiesFile(driver);
    GeneralPage general = new GeneralPage(driver);
    RegisterPage register = new RegisterPage(driver);

    private void setSSN(int rowNumber) {
        setObjectBy(SSN,getData(rowNumber,1) );
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
        sleep(2);
        return findElements(SEND_SMS_CHECKBOX).get(index);
    }

    private void clickResetPassword(){
        waitForElement(driver, MIN_WAIT_4_ELEMENT, RESET_PASS);
        clickObjectBy(RESET_PASS);
    }


    public ForgotPassPage forgotPassActions(int rowNumber) throws IOException {

        setSSN(rowNumber);
        register.setBirthDate(rowNumber,3,4);
        clickResetPassword();

        return this;

    }

    public LoginPage sendResetLink() {

        checkbox(0).click();
        clickObjectBy(BUTTON_SEND);
        buttonClose().click();  //cliklemiyor
        return new LoginPage(driver);

    }

    public WebElement buttonClose() {

        waitForElement(driver, OPT_WAIT_4_ELEMENT, BUTTON_CLOSE);
        return getElemenstBy(BUTTON_CLOSE, 1);
    }

    public ForgotPassPage setPasswordChange() throws IOException {

        setObjectBy(PASSWORD_INPUT, general.newPasswordChange);
        clickObjectBy(CHANGE_BUTTON);
        switchToWindows();
        checkPasswordChange();
        clickObjectBy(LOGIN_BUTTON);


        return this;

    }

    private void checkPasswordChange() throws IOException {

        Assert.assertTrue("şifre değişikliği başarısız", getElementBy(SUCCESS_MESSAGE)
                                .getText().substring(0, 26).equals(prop.getObject("success_pass_change_message")));

    }

    public ForgotPassPage checkFailMessage (int rowNumber) {

        Assert.assertTrue(getData(rowNumber,7),getElementBy(ERROR_FIELD)
                                 .getText().equals(getData(rowNumber,11)));
        return this;
    }

    public ForgotPassPage checkFailMessageForSsn (int rowNumber) {

        Assert.assertTrue(getData(rowNumber,7),getElementBy(SSN_ERROR_MESSAGE)
                .getText().equals(getData(rowNumber,12)));
        return this;

    }


}


