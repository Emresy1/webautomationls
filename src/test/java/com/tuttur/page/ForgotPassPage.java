package com.tuttur.page;


import com.tuttur.configs.PropertiesFile;
import com.tuttur.constants.ForgotPass_Constants;
import com.tuttur.util.ExcelUtil;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ForgotPassPage extends ForgotPass_Constants {
    public ForgotPassPage(WebDriver driver) throws IOException {
        super(driver);
    }


    PropertiesFile prop = new PropertiesFile(driver);
    GeneralPage general = new GeneralPage(driver);
    RegisterPage register = new RegisterPage(driver);
    DbQueriesPage db = new DbQueriesPage(driver);
    LoginPage login = new LoginPage(driver);
    MainPage main = new MainPage(driver);


    private void setSSN(int rowNumber) {
        setObjectBy(SSN, getData(rowNumber, 1));
    }


    /**
     * Şifremi unuttum ekranındaki checkboxlar için
     * İndex = 1 sms checkbox
     * index = 2 mail checkbox
     *
     * @param index
     * @return
     */

    public ForgotPassPage checkboxClick(int index) {

        waitForElement(driver,MIN_WAIT_4_ELEMENT,SEND_SMS_CHECKBOX);

        WebElement checkbox = findElements(SEND_SMS_CHECKBOX).get(index);
        checkbox.click();
        return this;

    }

    private void clickResetPassword() {

        waitForElement(driver, MIN_WAIT_4_ELEMENT, RESET_PASS);
        clickObjectBy(RESET_PASS);
    }

    public ForgotPassPage setUserInfo(int rowNumber) throws IOException {

        setSSN(rowNumber);
        register.setBirthDate(rowNumber, 1, 2);

        return this;

    }

    public ForgotPassPage invalidSsnWithYearControls() throws IOException {

        setData("1880",3,5);
        setUserInfo(3);
        clickObjectsBy(DATE, 1);
        checkFailMessage(1,prop.getObject("invalidYear"));
        checkSsnMaxValue();
        checkFailMessage(0,prop.getObject("invalidSsn"));
        checkButtonDisabledControl();

        return this;

    }

    public ForgotPassPage invalidBirthdateControls() throws IOException {

        clickObjectBy(CLOSE_BUTTON);
        main.getLoginPage();
        login.getForgotPassModal();
        setData("1990",3,5);
        setUserInfo(3);
        clickResetPassword();
        checkFailMessage();

        return this;

    }

    public ForgotPassPage clickButtonSend() {

        clickObjectBy(BUTTON_SEND);
        return this;

    }
    public ForgotPassPage countdown(){


        waitForElement(driver,OPT_WAIT_4_ELEMENT, TIMER);
        int loopCount = 180;

            while (0 < loopCount) {

                sleep(1);
                String timer = getElementBy(TIMER).getText().trim();
                int intTimer = Integer.parseInt(timer);

                Assert.assertEquals(loopCount, intTimer);

                loopCount--;
                System.out.println("loopCount : " + loopCount + " timer :" + intTimer);
                
            }
        return this;

    }

    public ForgotPassPage setVerifyCode(String verifyCode) throws IOException {

        String smsCodePass = db.getValidationCode(verifyCode);
        setObjectBy(VERIFY_CODE_FIELD, smsCodePass);
        clickObjectBy(VERIFY_BUTTON);

        return this;

    }

    private void fillPasswordInput(){
        setObjectBy(PASSWORD_INPUT, general.newPasswordChange);
        setObjectBy(PASSWORD_REPEAT_INPUT, general.newPasswordChange);
    }


    public LoginPage changePassword() throws IOException, InterruptedException {

        fillPasswordInput();
        clickObjectBy(CHANGE_BUTTON);
        checkPasswordChange();
        setData(general.newPasswordChange, 1, 2);
        clickObjectBy(LOGIN_BUTTON);

        return new LoginPage(driver);
    }

    private void setPasswordInputs(int rowNumber){

        setObjectBy(PASSWORD_INPUT,getData(rowNumber,2));
        setObjectBy(PASSWORD_REPEAT_INPUT,getData(rowNumber, 7));
    }

    public ForgotPassPage checkUnmatchPassword () throws IOException {

        setPasswordInputs(4);
        clickObjectBy(CHANGE_BUTTON);
        checkFailChangePassword();

        return this;

    }
    private void checkPasswordChange() throws IOException {


        assertTrue(prop.getObject("failPasswordChange"), getElementBy(SUCCESS_MESSAGE)
                .getText().substring(0, 26).equals(prop.getObject("passwordChanged")));

    }

    public ForgotPassPage checkFailMessage(int index, String message) throws IOException {
        waitForElement(driver, MIN_WAIT_4_ELEMENT, INPUT_ERROR_MESSAGE);

        Assert.assertTrue(prop.getObject("warningTextIncorret"), getElemenstBy(INPUT_ERROR_MESSAGE, index)
                .getText().equals(message));
        return this;

    }

    public ForgotPassPage checkFailMessage() throws IOException {

        waitForElement(driver,MIN_WAIT_4_ELEMENT,ERROR_FIELD);

        assertTrue(prop.getObject("warningTextIncorret"), getElementBy(ERROR_FIELD)
                .getText().equals(prop.getObject("canNotEmptySsnMessage")));
        return this;
    }



    public ForgotPassPage checkSsnMaxValue() {


        int inputLength = getAttribute(SSN, "value").length();
        String input = Integer.toString(inputLength);

        Assert.assertEquals(input, getAttribute(SSN, "maxlength"));

        return this;
    }


    public ForgotPassPage checkButtonDisabledControl() {

        getElementBy(RESET_PASS).isEnabled();
        return this;
    }

    public ForgotPassPage checkFailChangePassword() throws IOException {

        Assert.assertTrue(prop.getObject("warningTextIncorret"), getElementBy(CHANGE_INPUT_MESSAGE)
                .getText().equals(prop.getObject("unMatchingPassword")));
        return this;

    }


}


