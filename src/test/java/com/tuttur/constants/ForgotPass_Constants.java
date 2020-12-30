package com.tuttur.constants;

import com.tuttur.util.BasePageUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPass_Constants extends BasePageUtil {
    public ForgotPass_Constants(WebDriver driver) {
        super(driver);
    }
    public By SSN = By.name("ssn");
    public By BIRTHDATE = By.className("formSelect-select");
    public By RESET_PASS = By.cssSelector(".medium.primary.forgotPasswordForm-button");
    public By SEND_SMS_CHECKBOX = By.className("formRadio-checkbox");//By.cssSelector(".formCheckbox-checkbox");
    public By SEND_SMS_CHECK = By.className("formCheckbox-label");
    public By SEND_BUTTON = By.cssSelector(".medium.primary.w-100");
    public By CLOSE_BUTTON = By.cssSelector("medium primary");
    public By PASSWORD_INPUT = By.name("passwordInput");
    public By CHANGE_BUTTON = By.id("changePasswordButton");
    public By SUCCESS_MESSAGE = By.className("emailProcessComplete");
    public By LOGIN_BUTTON = By.id("loginButton");
    public By BUTTON_SEND = By.cssSelector(".medium.primary.w-100");
    public By BUTTON_CLOSE = By.cssSelector(".medium.primary");
    public By ERROR_FIELD = By.className("formPage-error");
    public By SSN_ERROR_MESSAGE = By.className("formElementError-text");

}
