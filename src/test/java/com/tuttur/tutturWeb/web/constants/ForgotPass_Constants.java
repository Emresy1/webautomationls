package com.tuttur.tutturWeb.web.constants;

import com.tuttur.util.BasePageUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPass_Constants extends BasePageUtil {
    public ForgotPass_Constants(WebDriver driver) {
        super(driver);
    }
    public By SSN = By.name("ssn");
    public By BIRTHDATE = By.className("formSelect-select");
    public By DATE = By.cssSelector(".formInput-input.undefined");
    public By RESET_PASS = By.cssSelector(".medium.primary.forgotPasswordForm-button");
    public By SEND_SMS_CHECKBOX = By.className("formRadio-checkbox");
    public By SEND_SMS_CHECK = By.className("formCheckbox-label");
    public By SEND_BUTTON = By.cssSelector(".medium.primary.w-100");
    public By CLOSE_BUTTON = By.cssSelector(".medium.modalHeader-close");
    public By PASSWORD_INPUT = By.name("password");
    public By PASSWORD_REPEAT_INPUT = By.name("passwordRepeat");
    public By CHANGE_BUTTON = By.cssSelector(".medium.primary.resetPasswordContainer-resetPasswordSubmit");
    public By SUCCESS_MESSAGE = By.className("resetPasswordSuccess-successTitle");
    public By LOGIN_BUTTON = By.cssSelector(".medium.primary.resetPasswordSuccess-successButton");
    public By BUTTON_SEND = By.cssSelector(".medium.primary.w-100");
    public By BUTTON_CLOSE = By.xpath("//*[@id=\"modal\"]/div[2]/div[2]/div[2]/div/div/div[2]/button");
    public By ERROR_FIELD = By.className("formPage-error");
    public By INPUT_ERROR_MESSAGE = By.className("formElementError-text");
    public By WARNING_TEXT_MODAL = By.className("formPage-description");
    public By VERIFY_CODE_FIELD = By.name("verifyCode");
    public By VERIFY_BUTTON = By.cssSelector(".medium.primary.forgotPasswordForm-button.validateCodeButton");
    public By CHANGE_INPUT_MESSAGE = By.className("resetPasswordContainer-inputErrorField ");
    public By TIMER = By.className("countdown-number");


}
