package com.tuttur.test;

import java.io.IOException;

import com.tuttur.page.LoginPage;
import org.junit.Test;
import com.tuttur.base.BaseTest;
import com.tuttur.configs.PropertiesFile;
import com.tuttur.page.MainPage;
import org.openqa.selenium.By;

public class LoginTest extends BaseTest {
    PropertiesFile prop = new PropertiesFile(driver);

    /**
     * Case 1.0
     * Üye numarasıyla başarılı login
     * @throws IOException
     */
    @Test
    public void successfullyLoginWithAccountNo() throws IOException {

        new MainPage(driver).getLoginPage().login(prop.getObject("account_no")).checkAccountNo();
    }
    /**
     * Case 1.1
     * Kullanıcı adı ile başarılı login
     * @throws IOException
     */
    @Test
    public void successfullyLoginWithUserName() throws IOException {
        new MainPage(driver).getLoginPage().login(prop.getObject("username")).checkUsernameText();
    }
    /**
     * Case 1.2
     * TCKN  ile başarılı login
     */
    @Test
    public void successfulllyLoginWithIdentityNo() throws IOException {
        new MainPage(driver).getLoginPage().login(prop.getObject("login_ssn")).checkUsernameText();
    }
    /**
     * Case 1.3
     * Başarısız login kontrolü
     */
    @Test
    public void failLogin() throws IOException {
        new MainPage(driver).getLoginPage().login(prop.getObject("login_ssn"));
        new LoginPage(driver).checkFailLogin();

    }
}



