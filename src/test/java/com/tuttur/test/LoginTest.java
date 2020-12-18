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

        new MainPage(driver).getLoginPage()
                .login(prop.getObject("account_no"),(prop.getObject("usernamePass"))).checkAccountNo();
    }

    /**
     * Case 1.1
     * Kullanıcı adı ile başarılı login
     * @throws IOException
     */
    @Test
    public void successfullyLoginWithUserName() throws IOException {
        new MainPage(driver).getLoginPage()
                .login(prop.getObject("username"),(prop.getObject("usernamePass")))
                .checkUsernameText(prop.getObject("username"));
    }

    /**
     * Case 1.2
     * TCKN  ile başarılı login
     */
    @Test
    public void successfulllyLoginWithIdentityNo() throws IOException {
        new MainPage(driver).getLoginPage().login(prop.getObject("login_ssn"),(prop.getObject("password")))
                .checkUsernameText(prop.getObject("ssnUser"));
    }

    /**
     * Case 1.3
     * TCKN ile başarısız login kontrolü
     */
    @Test
    public void failLoginWithSsn() throws IOException {
        new MainPage(driver).getLoginPage().login(prop.getObject("invalidSsn"),(prop.getObject("password")));
        new LoginPage(driver).checkFailLogin();
    }

    /**
     * Case 1.4
     * Username ile başarısız login kontrolü
     */
    @Test
    public void failLoginWithUsername() throws IOException {
        new MainPage(driver).getLoginPage().login(prop.getObject("invalidUsername"),(prop.getObject("password")));
        new LoginPage(driver).checkFailLogin();
    }

    /**
     * Case 1.5
     * Account no ile başarısız login kontrolü
     */
    @Test
    public void failLoginWithAccountNo() throws IOException {
        new MainPage(driver).getLoginPage().login(prop.getObject("invalidAccountNo"),(prop.getObject("password")));
        new LoginPage(driver).checkFailLogin();
    }

    /**
     * Case 1.6
     * Email ile başarısız login kontrolü
     */
    @Test
    public void failLoginWithEmail() throws IOException {

        new MainPage(driver).getLoginPage()
                .login(prop.getObject("loginEmail"),(prop.getObject("password")));
        new LoginPage(driver).checkFailLoginWithEmail();
    }

    /**
     * Case 1.7
     * Telefon numarası ile başarısız login kontrolü
     */
    @Test
    public void failLoginWithPhoneNumber() throws IOException {

        new MainPage(driver).getLoginPage()
                .login(prop.getObject("invalidPhone"),(prop.getObject("password")));
        new LoginPage(driver)
                .checkFailLoginWithGsm();
    }

    /**
     * Case 1.8
     * Login inputlarının maksimum değer kontrolü
     * @throws IOException
     */
    @Test
    public void loginInputMaxValueControl() throws IOException {
        new MainPage(driver).getLoginPage()
                .login(prop.getObject("user_max_value"),(prop.getObject("password_max_value")));
        new LoginPage(driver).checkInputErrorValidations();
    }

    /**
     * Case 1.9
     * Hatalı şifre ile login kontrolü
     * @throws IOException
     */
    @Test
    public void failLoginWithPassword() throws IOException {
        new MainPage(driver).getLoginPage()
                .login(prop.getObject("username"),(prop.getObject("invalidPassword")));
        new LoginPage(driver).checkFailLogin();
    }

    /**
     * Case 2.0
     * Boş input ile login kontrolü
     * @throws IOException
     */

    @Test
    public void loginWithMissingInfo () throws IOException {
        new MainPage(driver).getLoginPage().login("","");
        new LoginPage(driver).checkMissingInfoText();
    }


}



