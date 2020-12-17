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
    public By SEND_SMS_CHECKBOX = By.className("formCheckbox-checkbox");
    public By SEND_BUTTON = By.cssSelector(".medium.primary.w-100");
    public By CLOSE_BUTTON = By.cssSelector("medium primary");

}
