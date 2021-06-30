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
    public By DATE = By.cssSelector(".formInput-input.undefined");
    public By YEAR = By.className("formInput-input");
    public By MONTHS = By.className("formSelect-select");
    public By BIRTHDATE = By.className("formSelect-border");
    public By CHECKBOX = By.className("formCheckbox-checkbox");
    public By SUBMIT = By.cssSelector(".medium.primary.registerForm-button");
    public By ACTIVATION_FIELD = By.name("activationCode");
    public By ACTIVATION_BUTTON = By.cssSelector(".medium.primary.phoneActivation-activateButton");
    public By FORM_INPUT = By.className("formInput-input");
    public By BANNER = By.className("registerModal-banner");
    public By WARNING_TEXT_ON_MODAL = By.className("formPage-error");
    public By MODAL_TITLE = By.className("modalHeader-text");
    public By BUTTON_RESET_PASSWORD = By.cssSelector(".medium.primary.forgotPasswordForm-button");
    public By INPUT_ERROR_TEXT = By.className("formElementError-text");
    public By CLOSE_MODAL = By.cssSelector(".medium.modalHeader-close");
    public By WARNING_TEXT = By.className("formElementError-text");
    public By REGISTER_DESC = By.className("formPage-description");
    public By PROGRESS_BAR = By.className("passwordInfo-progress");
    public By CURSOR_FİELD= By.cssSelector(".formInput.formInput--isFocus.formInput--isError.formInput--hasLabel");
    public By ALREADY_MEMBER = By.className("registerForm-link");
    public By INFO_TEXT = By.className("formCheckbox-span");
    public By USERNAME_LOADING = By.cssSelector(".formInput.formInput--hasLabel.formInput--hasPlaceholder.loading");
    public By USERNAME_FUNCTION = By.className("medium");
    public By FORM_SELECT_MONTHS= By.cssSelector(".formSelect.formSelect--hasLabel");
    public By PLACEHOLDER = By.className("formInput-label");
    public By BUTTON_CLOSE_COOKİE_BAR = By.className("cookieBar-close");






}
