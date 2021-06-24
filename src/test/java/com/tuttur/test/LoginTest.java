package com.tuttur.test;

import java.io.IOException;

import com.tuttur.base.BaseTest;
import com.tuttur.configs.PropertiesFile;
import com.tuttur.page.DbQueriesPage;
import com.tuttur.page.LoginPage;
import com.tuttur.page.MainPage;


import com.tuttur.util.BasePageUtil;
import com.tuttur.util.ExcelUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.junit.Test;


public class LoginTest extends BaseTest {

    PropertiesFile prop = new PropertiesFile(driver);
    BasePageUtil base = new BasePageUtil(driver);
    DbQueriesPage db = new DbQueriesPage(driver);


    /**
     * Case 1.0
     * Üye numarasıyla başarılı login
     *
     * @throws IOException
     */

    @Test
    public void successfullyLoginWithAccountNo() throws IOException, InterruptedException {

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
    public void successfullyLoginWithUserName() throws IOException, InterruptedException {

        base.getSheet("LoginData");

        new MainPage(driver).getLoginPage()
                .login(2)
                .checkUsernameText(2);

    }


    /**
     * Case 1.2
     * TCKN  ile başarılı login
     */


    @Test
    public void successfulllyLoginWithIdentityNo() throws IOException, InterruptedException {

        base.getSheet("LoginData");

        new MainPage(driver).getLoginPage().login(3)
                .checkUsernameText(3);
    }

    /**
     * Case 1.3
     * TCKN ile başarısız login kontrolü
     * assert mesajı düzeltilecek !!!!!
     */
    @Test
    public void failLoginWithSsn() throws IOException, InterruptedException {

        base.getSheet("LoginData");

        new MainPage(driver).getLoginPage().login(4);
        new LoginPage(driver).checkFailLogin(4);

    }

    /**
     * Case 1.4
     * Username ile başarısız login kontrolü
     */
    @Test
    public void failLoginWithUsername() throws IOException, InterruptedException {

        base.getSheet("LoginData");

        new MainPage(driver).getLoginPage().login(5);
        new LoginPage(driver).checkFailLogin(5);
    }

    /**
     * Case 1.5
     * Account no ile başarısız login kontrolü
     * assert mesajı düzeltilecek
     */
    @Test
    public void failLoginWithAccountNo() throws IOException, InterruptedException {

        base.getSheet("LoginData");

        //committesttt
        new MainPage(driver).getLoginPage().login(6);
        new LoginPage(driver).checkFailLogin(6);

    }

    /**
     * Case 1.6
     * Email ile başarısız login kontrolü
     * assert mesajı düzeltilecek
     */
    @Test
    public void failLoginWithEmail() throws IOException, InterruptedException {

        base.getSheet("LoginData");

        new MainPage(driver).getLoginPage()
                .login(7);
        new LoginPage(driver).checkFailLoginWithEmail(7);
    }

    /**
     * Case 1.7
     * Telefon numarası ile başarısız login kontrolü
     *
     */
    @Test
    public void failLoginWithPhoneNumber() throws IOException, InterruptedException {

        base.getSheet("LoginData");

        new MainPage(driver).getLoginPage().login(8);
        new LoginPage(driver).checkFailLoginWithGsm(8);
    }

    /**
     * Case 1.8
     * Login inputlarının maksimum değer kontrolü
     *
     *
     * @throws IOException
     */
    @Test
    public void loginInputMaxValueControl() throws IOException, InterruptedException {

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
    public void failLoginWithPassword() throws IOException, InterruptedException {

        base.getSheet("LoginData");

        new MainPage(driver).getLoginPage()
                .login(10);
        new LoginPage(driver).checkFailLogin(10);
    }


    /**
     * Case 2.0
     * Boş input ile login kontrolü
     * @throws IOException
     */

    @Test
    public void loginWithMissingInfo() throws IOException, InterruptedException {

        base.getSheet("LoginData");

        new MainPage(driver).getLoginPage().login(11);
        new LoginPage(driver).checkMissingInfoText(11);

    }
    /**
     * Case 2.1
     * Input uyarı mesajı kontrolü
     *
     */

    @Test
    public void LoginInputMessageControl () throws IOException, InterruptedException {

        base.getSheet ("LoginData");
        new MainPage(driver).getLoginPage().login(12);
        new LoginPage(driver).checkInputInfoText(12);

    }

    /**
     * Case 2.1
     * Sözleşmeler
     */
    @Test
    public void confirmContract() throws IOException, InterruptedException {

        base.getSheet("LoginData");
        db.executeQuery(prop.getObject("deleteContract"));

        new MainPage(driver).getLoginPage().login(13);
               // .checkUsernameText(13);


    }
}



