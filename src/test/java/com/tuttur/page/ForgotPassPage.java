package com.tuttur.page;

import com.tuttur.base.BaseTest;
import com.tuttur.configs.PropertiesFile;
import com.tuttur.constants.ForgotPass_Constants;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;



import java.io.IOException;
import java.util.Arrays;
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


    public LoginPage forgotPassAction() throws IOException {
        setSSN();
        new RegisterPage(driver).setBirthDate(1,3,4);
        clickResetPassword();
        checkbox(1).click();
        clickObjectBy(BUTTON_SEND);
        buttonClose().click();
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

}


