package com.tuttur.test.android;


import com.tuttur.base.app.BaseTest;
import com.tuttur.page.android.DashboardPage;
import org.testng.annotations.Test;


public class LoginTest extends BaseTest {


    @Test
    public void successfullLogin(){

        new DashboardPage(appiumDriver)
                .getLoginPage()
                .login("gokhansangar", "Sangar123");
    }


}
