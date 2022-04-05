package com.tuttur.test.web;



import com.tuttur.base.web.BaseTest;

import com.tuttur.page.web.DashboardPage;
import org.testng.annotations.Test;


public class LoginTest extends BaseTest {


    @Test
    public void successfullLogin() {


        new DashboardPage(driver)
                .getLoginPage()
                .login("gokhanangar","Sangar123");

    }

}