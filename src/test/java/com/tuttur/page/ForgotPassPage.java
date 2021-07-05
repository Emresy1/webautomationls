package com.tuttur.page;


import com.tuttur.configs.PropertiesFile;
import com.tuttur.constants.ForgotPass_Constants;
import com.tuttur.util.ExcelUtil;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

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

        sleep(2);
        WebElement checkbox = findElements(SEND_SMS_CHECKBOX).get(index);
        checkbox.click();
        return this;

    }

    private WebElement checkbox(int index) {

        sleep(2);
        return findElements(SEND_SMS_CHECKBOX).get(index);
    }

    private void clickResetPassword() {
        waitForElement(driver, MIN_WAIT_4_ELEMENT, RESET_PASS);
        clickObjectBy(RESET_PASS);
    }

    public ForgotPassPage setUserInfo(int rowNumber) throws IOException {

        setSSN(rowNumber);
        register.setBirthDate(rowNumber, 1, 2);
        clickResetPassword();

        return this;

    }

    public ForgotPassPage invalidSsnControls () throws IOException {

        //setSSN(2);
        setObjectBy(SSN,"123456789012");
        clickObjectsBy(DATE,1);
        waitForElement(driver,MIN_WAIT_4_ELEMENT,SSN_ERROR_MESSAGE);
        checkSsnMaxValue();
        checkFailMessageForSsn();

        return this;

    }

    public ForgotPassPage invalidBirthdateControls () throws IOException {

        clickObjectBy(CLOSE_BUTTON);
        main.getLoginPage();
        login.getForgotPassModal();
        setUserInfo(3);
        checkFailMessage();

        return this;

    }

    public ForgotPassPage clickButtonSend() {

        clickObjectBy(BUTTON_SEND);
        return this;

    }

    public ForgotPassPage setVerifyCode() throws IOException {

        String smsCodePass = db.getValidationCode(prop.getObject("verifyCode"));
        setObjectBy(VERIFY_CODE_FIELD, smsCodePass);
        clickObjectBy(VERIFY_BUTTON);

        return this;

    }

    public ForgotPassPage checkWarningTextOnModal(String text) {

        waitForElement(driver, OPT_WAIT_4_ELEMENT, RESET_PASS);
        Assert.assertTrue("Modalda uyarı texti görülmedi", getElementBy(WARNING_TEXT_MODAL).getText()
                .equals(text));

        return this;
    }

    //public LoginPage buttonCloseClick () {

    //     waitForElement(driver, OPT_WAIT_4_ELEMENT, BUTTON_CLOSE);
    //    clickObjectBy(BUTTON_CLOSE);
    //    return new LoginPage(driver);

    // }


    public LoginPage changePassword() throws IOException, InterruptedException {

        setObjectBy(PASSWORD_INPUT, general.newPasswordChange);
        setObjectBy(PASSWORD_REPEAT_INPUT, general.newPasswordChange);
        clickObjectBy(CHANGE_BUTTON);
        checkPasswordChange();
        setData(general.newPasswordChange, 1, 2);
        clickObjectBy(LOGIN_BUTTON);

        return new LoginPage(driver);
    }

    private void checkPasswordChange() throws IOException {


        Assert.assertTrue(prop.getObject("failPasswordChange"), getElementBy(SUCCESS_MESSAGE)
                .getText().substring(0, 26).equals(prop.getObject("PasswordChanged")));

    }

    public ForgotPassPage checkFailMessage() throws IOException {

        Assert.assertTrue(prop.getObject("warningTextİncorret"), getElementBy(ERROR_FIELD)
                .getText().equals(prop.getObject("infoDidNotMatch")));
        return this;
    }

    public ForgotPassPage checkFailMessageForSsn() throws IOException {

        Assert.assertTrue(prop.getObject("warningTextİncorret"), getElementBy(SSN_ERROR_MESSAGE)
                .getText().equals(prop.getObject("invalidSsn")));
        return this;

    }

    public ForgotPassPage checkSsnMaxValue() {


        int inputLength = getAttribute(SSN, "value").length();
        String input = Integer.toString(inputLength);

        Assert.assertEquals(input, getAttribute(SSN, "maxlength"));

        return this;
    }

}


