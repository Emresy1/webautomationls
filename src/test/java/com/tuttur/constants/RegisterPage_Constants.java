package com.tuttur.constants;

import com.tuttur.util.BasePageUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage_Constants extends BasePageUtil {
    public RegisterPage_Constants(WebDriver driver) {
        super(driver);
    }
    public By NAME = By.name("firstname");
    public By LASTNAME = By.name("lastname");
    public By SSN = By.name("ssn");
    public By GSM = By.name("mobile");
    public By EMAIL = By.name("email");
    public By REGISTER_USERNAME = By.name("username");
    public By REGISTER_PASSWORD = By.name("password");
    public By DATE = By.className("formInput-input");
    public By YEAR = By.className("formInput-input");
    public By MONTHS = By.className("formSelect-select");
    public By CHECKBOX = By.className("formCheckbox-checkbox");
    public By SUBMIT = By.cssSelector(".medium.primary.registerForm-button");
    public By ACTIVATION_FIELD = By.name("activationCode");
    public By ACTIVATION_BUTTON = By.cssSelector(".medium.primary.phoneActivationForm-button");
    public By FORM_INPUT = By.className("formInput-input");
    public By BANNER = By.className("registerModal-banner");
    public By WARNING_TEXT_ON_MODAL = By.className("formPage-error");





}
