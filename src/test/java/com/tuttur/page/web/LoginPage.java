package com.tuttur.page.web;

import com.tuttur.constants.web.LoginPage_Constants;
import com.tuttur.interfaces.ILoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends LoginPage_Constants implements ILoginPage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage login(String username, String password){

        setLoginFields(username,password);
        clickObjectBy(By.cssSelector(".medium.primary.loginForm-button"));

        return new DashboardPage(driver);
    }

    @Override
    public void setLoginFields(String username, String password) {

        setObjectBy(By.name("username"), username);
        setObjectBy(By.name("password"),password);
    }
}
