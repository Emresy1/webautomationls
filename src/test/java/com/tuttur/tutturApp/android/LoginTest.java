package com.tuttur.tutturApp.android;

import com.tuttur.tutturApp.android.page.DashboardPage;
import com.tuttur.tutturApp.base.BaseTest;

import org.testng.annotations.Test;


public class LoginTest extends BaseTest {



    @Test
    public void test() throws InterruptedException {


        Thread.sleep(10000);
        new DashboardPage(androidDriver).getLoginModal();


    }
}
