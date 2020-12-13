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



}
