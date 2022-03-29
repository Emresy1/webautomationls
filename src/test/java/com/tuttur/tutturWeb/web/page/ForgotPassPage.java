package com.tuttur.tutturWeb.web.page;


import com.tuttur.configs.PropertiesFile;
import com.tuttur.tutturWeb.web.constants.ForgotPass_Constants;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;


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

    public ForgotPassPage clickResetPassword() {

        waitForElement(driver, MIN_WAIT_4_ELEMENT, RESET_PASS);
        clickObjectBy(RESET_PASS);
        return this;
    }

    public ForgotPassPage setUserInfo(int rowNumber) throws IOException {

        waitForElement(driver,OPT_WAIT_4_ELEMENT,SSN);
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

        sleep(1);
        int loopCount = 180;

            while (0 < loopCount) {

                String timer = getElementBy(TIMER).getText().trim();
                int intTimer = Integer.parseInt(timer);

                loopCount--;

                Assert.assertEquals(loopCount, intTimer);

                System.out.println("loopCount : " + loopCount + " timer :" + intTimer);
                
            }
        return this;

    }
    public String verifyCode(String number){

        String verifyQuery = "SELECT activationcode FROM playermobileactivation WHERE mobile='" + number + "'ORDER by requesttime DESC OFFSET 0 LIMIT 1;";

        return verifyQuery;
    }

    public ForgotPassPage setVerifyCode(String verifyCode) throws IOException {

        int smsCodePass = db.getValidationCodeInt(verifyCode,1);
        System.out.println(smsCodePass + "*******");

        setObjectBy(VERIFY_CODE_FIELD, " '"+smsCodePass+"'");
        clickObjectBy(VERIFY_BUTTON);

        return this;

    }

    private void fillPasswordInput(){
        setObjectBy(PASSWORD_INPUT, general.newPasswordChange);
        setObjectBy(PASSWORD_REPEAT_INPUT, general.newPasswordChange);
    }


    public LoginPage changePassword(int rowNumber, int cellNumber) throws IOException, InterruptedException {

        fillPasswordInput();
        clickObjectBy(CHANGE_BUTTON);
        checkPasswordChange();
        setData(general.newPasswordChange, rowNumber, cellNumber);
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


        assertTrue(getElementBy(SUCCESS_MESSAGE)
                .getText().substring(0, 26)
                .equals(prop.getObject("passwordChanged")),prop.getObject("failPasswordChange"));

    }

    public ForgotPassPage checkFailMessage(int index, String message) throws IOException {
        waitForElement(driver, MIN_WAIT_4_ELEMENT, INPUT_ERROR_MESSAGE);

        Assert.assertTrue(getElemenstBy(INPUT_ERROR_MESSAGE, index)
                .getText().equals(message),prop.getObject("warningTextIncorret"));
        return this;

    }

    public ForgotPassPage checkFailMessage() throws IOException {

        waitForElement(driver,MIN_WAIT_4_ELEMENT,ERROR_FIELD);

        assertTrue(getElementBy(ERROR_FIELD)
                .getText()
                .equals(prop.getObject("canNotEmptySsnMessage")),prop.getObject("warningTextIncorret"));
        return this;
    }



    public ForgotPassPage checkSsnMaxValue() {


        int inputLength = getAttribute(SSN, "value").length();
        String input = Integer.toString(inputLength);

        Assert.assertEquals(input, getAttribute(SSN, "maxlength"));

        return this;
    }


    public ForgotPassPage checkButtonDisabledControl() {

        isEnabled(RESET_PASS);
        return this;
    }

    public ForgotPassPage checkFailChangePassword() throws IOException {

        Assert.assertTrue(getElementBy(CHANGE_INPUT_MESSAGE)
                .getText()
                .equals(prop.getObject("unMatchingPassword")),prop.getObject("warningTextIncorret"));
        return this;

    }


}


