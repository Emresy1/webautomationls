package com.tuttur.page.android;


import com.tuttur.constants.app.A_DashboardPage_Constants;
import com.tuttur.interfaces.IMainPage;
import com.tuttur.page.mweb.M_LoginPage;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;


public class DashboardPage extends A_DashboardPage_Constants implements IMainPage {


    public DashboardPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public LoginModal getLoginPage() {

        driver.findElement(By.id("com.tuttur.tuttur_mobile_android:id/Login")).click();

        return new LoginModal(appiumDriver);
    }
}
