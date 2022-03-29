package com.tuttur.tutturWeb.mweb.page;

import com.tuttur.tutturWeb.mweb.constants.M_MainPage_Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class M_MainPage extends M_MainPage_Constants {

    public M_MainPage(WebDriver driver) {
        super(driver);
    }

    public M_LoginPage getLoginPage(){

        clickObjectBy(By.className("headerLogout-loginLink"));

        return new M_LoginPage(driver);
    }
}
