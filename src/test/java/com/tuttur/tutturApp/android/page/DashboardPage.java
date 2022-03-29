package com.tuttur.tutturApp.android.page;

import com.tuttur.util.BasePageUtil;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class DashboardPage extends BasePageUtil {

    public DashboardPage(AndroidDriver androidDriver) {
        super(androidDriver);
    }

    public LoginModal getLoginModal(){

        androidDriver.findElement(By.id("com.tuttur.tuttur_mobile_android:id/Login")).click();

        return new LoginModal(androidDriver);
    }
}
