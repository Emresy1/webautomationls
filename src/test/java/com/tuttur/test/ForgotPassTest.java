package com.tuttur.test;

import com.tuttur.base.BaseTest;
import com.tuttur.configs.PropertiesFile;
import com.tuttur.page.*;
import org.junit.Test;

import java.io.IOException;

public class ForgotPassTest extends BaseTest {

    PropertiesFile prop = new PropertiesFile(driver);
    GeneralPage general = new GeneralPage(driver);

    public ForgotPassTest() throws IOException {
    }

    @Test
    public void forgotPassword () throws IOException {

        new MainPage(driver).getLoginPage().getForgotPassModal().forgotPassAction().getForgotPassPage()
                .setPasswordChange();

    }


}
