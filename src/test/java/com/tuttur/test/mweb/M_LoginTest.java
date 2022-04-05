package com.tuttur.test.mweb;

import com.tuttur.base.web.BaseTest;
import com.tuttur.page.mweb.M_DashboardPage;

import org.testng.annotations.Test;


public class M_LoginTest extends BaseTest {


    @Test
    public void successfullLogin(){

        new M_DashboardPage(driver)
                .getLoginPage()
                .login("gokhansangar","Sangar123");
    }



}
