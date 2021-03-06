package com.tuttur.page.mweb;


import com.tuttur.constants.web.LoginPage_Constants;
import com.tuttur.interfaces.ILoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class M_LoginPage  extends LoginPage_Constants implements ILoginPage {


    public M_LoginPage(WebDriver driver) {
        super(driver);
    }



    public M_DashboardPage login(String username, String password){

        setLoginFields(username,password);
        clickObjectBy(By.cssSelector(".large.primary.w-100"));

        return new M_DashboardPage(driver);
    }

    @Override
    public void setLoginFields(String username, String password) {

        setObjectBy(By.name("username"),username);
        setObjectBy(By.name("password"),password);
    }
}
