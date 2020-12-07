package com.tuttur.page;

import com.tuttur.configs.PropertiesFile;
import com.tuttur.constants.LoginPage_Constants;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class LoginPage extends LoginPage_Constants {

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    PropertiesFile prop = new PropertiesFile(driver);

    private void setUsername(String username) throws IOException {
        setObjectBy(USERNAME,username);

    }
    private void setPassword(String pass) throws IOException {
        setObjectBy(PASSWORD, prop.getObject("password"));

    }

    public MainPage login(String user) throws IOException {
        setUsername(user);
        setPassword(prop.getObject("password"));
        clickObjectBy(BUTTON_LOGIN_ON_POPUP);
        return new MainPage(driver);
    }

    public void checkFailLogin () throws IOException {
        Assert.assertTrue("Başarısız login hatalı",getElementBy(LOGIN_ERROR_FIELD).getText().equals(prop.getObject("fail_login_message")));
    }



}
