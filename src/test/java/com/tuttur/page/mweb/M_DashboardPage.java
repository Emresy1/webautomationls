package com.tuttur.page.mweb;



import com.tuttur.constants.web.DashboardPage_Constants;
import com.tuttur.interfaces.IMainPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class M_DashboardPage extends DashboardPage_Constants implements IMainPage {


    public M_DashboardPage(WebDriver driver) {
        super(driver);
    }


    public M_LoginPage getLoginPage(){

        waitForElement(driver,3, By.className("headerLogout-loginLink"));
        clickObjectBy(By.className("headerLogout-loginLink"));

        return new M_LoginPage(driver);
    }

}
