package com.tuttur.test;

import com.tuttur.base.BaseTest;
import com.tuttur.configs.PropertiesFile;
import com.tuttur.page.GeneralPage;
import com.tuttur.page.MainPage;
import com.tuttur.page.RegisterPage;
import org.junit.Test;

import java.io.IOException;

public class RegisterTest extends BaseTest {
    PropertiesFile prop = new PropertiesFile(driver);
    GeneralPage general = new GeneralPage(driver);

    public RegisterTest() throws IOException {
    }

    /**
     * Case 1.0
     * Başarılı üye ol
     *
     * @throws InterruptedException
     * @throws IOException
     */

    @Test
    public void successFullyRegisterTest () throws InterruptedException, IOException {
        new MainPage(driver).getRegisterPage()
                .setRegisterForm().clickSubmit().smsActivation();
                new MainPage(driver).checkUsernameText();
    }
}
