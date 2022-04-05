package com.tuttur.page.android;

import com.tuttur.constants.app.A_LoginModal_Constants;
import com.tuttur.interfaces.ILoginModal;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class LoginModal extends A_LoginModal_Constants implements ILoginModal {

    public LoginModal(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }



    public void setUsername(String username) {

        setObjectBy(By.id("com.tuttur.tuttur_mobile_android:id/etUser"), username);

    }


    public void setPassword(String password) {

        setObjectBy(By.id("com.tuttur.tuttur_mobile_android:id/etUserPassword"), password);
    }

    @Override
    public DashboardPage login(String username, String password) {

        setUsername(username);
        setPassword(password);
        clickObjectBy(By.id("com.tuttur.tuttur_mobile_android:id/tvLoginButton"));
        return new DashboardPage(appiumDriver);
    }
}
