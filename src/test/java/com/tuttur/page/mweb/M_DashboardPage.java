package com.tuttur.page.mweb;


import com.tuttur.constants.web.DashboardPage_Constants;
import com.tuttur.interfaces.IMainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class M_DashboardPage extends DashboardPage_Constants implements IMainPage {


    public M_DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void getLoginPage() {

        clickObjectBy(By.className("headerLogout-loginLink"));
    }
}
