package com.tuttur.test;

import com.tuttur.base.BaseTest;
import com.tuttur.configs.PropertiesFile;
import com.tuttur.page.GeneralPage;
import com.tuttur.page.MainPage;
import com.tuttur.page.RegisterPage;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class RegisterTest extends BaseTest {
    PropertiesFile prop = new PropertiesFile(driver);
    GeneralPage general = new GeneralPage(driver);
    RegisterPage register = new RegisterPage(driver);

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
                .setRegisterForm(0)
                .clickSubmit()
                .smsActivation();
                new MainPage(driver).checkUsernameText(general.username);

    }

    /**
     * Case 1.1
     * Başarılı üye ol (username kullan fonksiyonu ile)
     */

    @Test
    public void registerWithUseFunctionality() throws IOException, InterruptedException {
        new MainPage(driver).getRegisterPage()
                .setRegisterForm(1).clickSubmit().smsActivation();
        new MainPage(driver).checkUsernameText(general.generateUsernameText.toString());

    }

    /**
     * Case 2.0
     * Başarısız üye ol (invalid datalar ile)
     *
     */

    @Test
    public void registerWithInvalidData() throws IOException {

        new MainPage(driver).getRegisterPage();
    }
}
