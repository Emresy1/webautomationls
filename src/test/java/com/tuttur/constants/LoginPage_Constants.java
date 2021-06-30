package com.tuttur.constants;

import com.tuttur.util.BasePageUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage_Constants extends BasePageUtil {
    public LoginPage_Constants(WebDriver driver) {
        super(driver);
    }

    public By USERNAME = By.name("username");
    public By PASSWORD = By.name("password");
    public By BUTTON_LOGIN_ON_POPUP = By.cssSelector(".medium.primary.loginForm-button");
    public By MODAL_ERROR_TEXT = By.className("formPage-error");
    public By MODAL_HEADER = By.className("modalHeader-text");
    public By CONTRACT_TAB = By.id("tabButtonActive");
    public By CONTRACT_CHECKBOX = By.className("formCheckbox-checkbox");
    public By CONTRACT_HEADER = By.className("modalHeader-text");
    public By BUTTON_ACCEPT = By.cssSelector(".medium.primary.personelData-acceptContainer-checkboxContainer-button");
    public By INPUT_ERROR_TEXT = By.className("formElementError-item");
    public By REMEMBER_ME = By.className("formCheckbox-checkbox");
    public By FORGOTPASSWORD = By.className("loginForm-forgotLink");
    
}
