package com.tuttur.page.android;


import com.tuttur.constants.app.DashboardPage_Constants;
import com.tuttur.interfaces.IMainPage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class DashboardPage  extends DashboardPage_Constants implements IMainPage {

    public DashboardPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    @Override
    public void getLoginPage() {

        appiumDriver.findElement(By.id("com.tuttur.tuttur_mobile_android:id/Login")).click();
    }
}
