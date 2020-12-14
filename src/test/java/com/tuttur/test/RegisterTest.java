package com.tuttur.test;

import com.tuttur.base.BaseTest;
import com.tuttur.configs.PropertiesFile;
import com.tuttur.page.MainPage;
import com.tuttur.page.RegisterPage;
import org.junit.Test;

import java.io.IOException;

public class RegisterTest extends BaseTest {
    PropertiesFile prop = new PropertiesFile(driver);



    @Test
    public void successFullyRegisterTest () throws InterruptedException, IOException {
        new MainPage(driver).getRegisterPage().setRegisterForm().clickSubmit().smsActivation().checkUsernameText(new RegisterPage(driver).usernameText());

    }
}
