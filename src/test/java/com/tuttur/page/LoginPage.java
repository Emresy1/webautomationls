package com.tuttur.page;

import com.tuttur.configs.PropertiesFile;
import com.tuttur.constants.LoginPage_Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class LoginPage extends LoginPage_Constants {

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    PropertiesFile prop = new PropertiesFile(driver);

    private void setUsername(String username) throws IOException {
        setObjectBy(USERNAME, prop.getObject("username"));

    }
    private void setPassword(String pass) throws IOException {
        setObjectBy(PASSWORD, prop.getObject("password"));

    }

    public MainPage successfullyLogin() throws IOException {
        int a = 2;
        setUsername(prop.getObject("account_no"));
        setPassword(prop.getObject("password"));
        clickObjectBy(SUBMIT);


        return new MainPage(driver);
    }


}
