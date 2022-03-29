package com.tuttur.tutturWeb.mweb.test;

import com.tuttur.tutturWeb.base.BaseTest;
import com.tuttur.tutturWeb.mweb.page.M_MainPage;
import org.testng.annotations.Test;

public class M_LoginTest extends BaseTest {




    @Test
    public void successfullLogin(){

        new M_MainPage(driver).getLoginPage();
    }
}
