package com.tuttur.test;

import java.io.IOException;

import com.tuttur.base.BaseTest;
import com.tuttur.configs.PropertiesFile;
import com.tuttur.page.LoginPage;
import com.tuttur.page.MainPage;


import com.tuttur.util.BasePageUtil;
import com.tuttur.util.ExcelUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.junit.Test;


public class LoginTest extends BaseTest {

    PropertiesFile prop = new PropertiesFile(driver);
    BasePageUtil base = new BasePageUtil(driver);


    /**
     * Case 1.0
     * Üye numarasıyla başarılı login
     *
     * @throws IOException
     */

    @Test
    public void successfullyLoginWithAccountNo() throws IOException {

        base.getSheet("LoginData");

        new MainPage(driver).getLoginPage()
                .login(1)
                .checkAccountNo(1);
    }

    /**
     * Case 1.1
     * Kullanıcı adı ile başarılı login
     *
     * @throws IOException
     */

    @Test
    public void successfullyLoginWithUserName() throws IOException {

        base.getSheet("LoginData");

        new MainPage(driver).getLoginPage()
                .login(2)
                .checkUsernameText(2);

    }


    /**
     * Case 1.2
     * TCKN  ile başarılı login
     * FİXLENECEK 1111111111111
     */


    @Test
    public void successfulllyLoginWithIdentityNo() throws IOException {

        base.getSheet("LoginData");

        new MainPage(driver).getLoginPage().login(3)
                .checkUsernameText(3);
    }

    /**
     * Case 1.3
     * TCKN ile başarısız login kontrolü
     * FİXLENECEK 111111111
     */
    @Test
    public void failLoginWithSsn() throws IOException {

        base.getSheet("LoginData");

        new MainPage(driver).getLoginPage().login(4);
        new LoginPage(driver).checkFailLogin(4);

    }

    /**
     * Case 1.4
     * Username ile başarısız login kontrolü
     */
    @Test
    public void failLoginWithUsername() throws IOException {

        base.getSheet("LoginData");

        new MainPage(driver).getLoginPage().login(5);
        new LoginPage(driver).checkFailLogin(5);
    }

    /**
     * Case 1.5
     * Account no ile başarısız login kontrolü
     * FİXLENECEK 11111111
     */
    @Test
    public void failLoginWithAccountNo() throws IOException {

        base.getSheet("LoginData");

        //committesttt
        new MainPage(driver).getLoginPage().login(6);
        new LoginPage(driver).checkFailLogin(6);

    }

    /**
     * Case 1.6
     * Email ile başarısız login kontrolü
     * FİXLENECEK 111111
     */
    @Test
    public void failLoginWithEmail() throws IOException {

        base.getSheet("LoginData");

        new MainPage(driver).getLoginPage()
                .login(7);
        new LoginPage(driver).checkFailLoginWithEmail(7);
    }

    /**
     * Case 1.7
     * Telefon numarası ile başarısız login kontrolü
     * FİXLENECEK 1111111111
     */
    @Test
    public void failLoginWithPhoneNumber() throws IOException {

        base.getSheet("LoginData");

        new MainPage(driver).getLoginPage()
                .login(8);
        new LoginPage(driver)
                .checkFailLoginWithGsm(8);
    }

    /**
     * Case 1.8
     * Login inputlarının maksimum değer kontrolü
     * FİXLENECEK 1111111
     *
     * @throws IOException
     */
    @Test
    public void loginInputMaxValueControl() throws IOException {

        base.getSheet("LoginData");

        new MainPage(driver).getLoginPage()
                .login(9);
        new LoginPage(driver).checkInputErrorValidations(9);
    }


    /**
     * Case 1.9
     * Hatalı şifre ile login kontrolü
     *
     * @throws IOException
     */
    @Test
    public void failLoginWithPassword() throws IOException {

        base.getSheet("LoginData");

        new MainPage(driver).getLoginPage()
                .login(10);
        new LoginPage(driver).checkFailLogin(10);
    }


    /**
     * Case 2.0
     * Boş input ile login kontrolü
     * FİXLENECEK 11111111
     *
     * @throws IOException
     */

    @Test
    public void loginWithMissingInfo() throws IOException {


        base.getSheet("LoginData");

        new MainPage(driver).getLoginPage().login(11);
        new LoginPage(driver).checkMissingInfoText(11);

    }

}



