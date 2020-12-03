package com.tuttur.constants;

import com.tuttur.util.BasePageUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage_Constants extends BasePageUtil {
    public LoginPage_Constants(WebDriver driver) {
        super(driver);
    }

    public By USERNAME = By.id("username");
    public By PASSWORD = By.id("password");
    public By SUBMIT = By.id("submit");
}
