package com.tuttur.test;

import com.tuttur.base.BaseTest;
import com.tuttur.page.ForgotPassPage;
import com.tuttur.page.LoginPage;
import com.tuttur.page.MainPage;
import com.tuttur.page.RegisterPage;
import org.junit.Test;

import java.io.IOException;

public class ForgotPassTest extends BaseTest {


    @Test
    public void forgotPassword () throws IOException {
        new MainPage(driver).getLoginPage().getForgotPassModal().forgotPassAction().getForgotPassPage()
                .setPasswordChange();

    }


}
