package com.tuttur.test.web;



import com.tuttur.base.web.BasePage;

import com.tuttur.page.web.DashboardPage;
import org.testng.annotations.Test;


public class LoginTest extends BasePage {

    @Test
    public void successfullLogin() {
        new DashboardPage(driver)
                .getLoginPage()
                .login("gokhanangar","Sangar123");
    }

}