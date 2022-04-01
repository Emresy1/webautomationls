package com.tuttur.page.web;


import com.tuttur.constants.web.DashboardPage_Constants;
import com.tuttur.interfaces.IMainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage  extends DashboardPage_Constants implements IMainPage {


    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void getLoginPage() {

        clickObjectBy(By.cssSelector(".headerTop-content-loginLink.px-35.mr-10"));
    }
}
