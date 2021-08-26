package com.tuttur.test;

import java.io.IOException;

import com.tuttur.base.BaseTest;
import com.tuttur.configs.PropertiesFile;
import com.tuttur.page.DbQueriesPage;
import com.tuttur.page.LoginPage;
import com.tuttur.page.MainPage;
import com.tuttur.util.BasePageUtil;
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
                .login(1,"non-contract")
                .checkAccountNo(1);
    }

    /**
     * Case 1.1
     * Kullanıcı adı ile başarılı login
     *
     * @throws IOException
     */

    @Test
    public void successfullyLoginWithUsername() throws IOException, InterruptedException {

        base.getSheet("LoginData");

        new MainPage(driver).getLoginPage()
                .login(2,"non-contract")
                .checkUsernameText(2,4);
    }


//    /**
//     * Case 1.2
//     * TCKN  ile başarılı login
//     */
//
//
//    @Test
//    public void successfulllyLoginWithSsn() throws IOException, InterruptedException {
//
//        base.getSheet("LoginData");
//
//        new MainPage(driver).getLoginPage()
//                .login(3,"non-contract")
//                .checkUsernameText(3,4);
//    }

    /**
     * Case 1.3
     * TCKN ile başarısız login kontrolü
     *
     */
    @Test
    public void unsuccessfullyLoginWithSsn() throws IOException, InterruptedException {

        base.getSheet("LoginData");

        new MainPage(driver).getLoginPage()
                .login(4,"non-contract");
        new LoginPage(driver)
                .checkWarningMessageOnModal(prop.getObject("canNotEmptyInputMessage"));

    }

    /**
     * Case 1.4
     * Username ile başarısız login kontrolü
     */
    @Test
    public void unsuccessfullyLoginWithUsername() throws IOException, InterruptedException {

        base.getSheet("LoginData");

        new MainPage(driver).getLoginPage()
                .login(5,"non-contract");
        new LoginPage(driver).checkWarningMessageOnModal(prop.getObject("unmatchingInfo"));
    }

    /**
     * Case 1.5
     * Account no ile başarısız login kontrolü
     *
     */
    @Test
    public void unsuccessfullyLoginWithAccountNo() throws IOException, InterruptedException {

        base.getSheet("LoginData");

        new MainPage(driver).getLoginPage()
                .login(6,"non-contract");
        new LoginPage(driver).checkWarningMessageOnModal(prop.getObject("canNotEmptyInputMessage"));

    }

    /**
     * Case 1.6
     * Email ile başarısız login kontrolü
     *
     */
    @Test
    public void unsuccessfullyLoginWithEmail() throws IOException, InterruptedException {

        base.getSheet("LoginData");

        new MainPage(driver).getLoginPage()
                .login(7,"non-contract");
        new LoginPage(driver).checkInputErrorMessages(0,prop.getObject("entryValidInfo"));
    }

    /**
     * Case 1.7
     * Telefon numarası ile başarısız login kontrolü
     *
     */
    @Test
    public void unsuccessfullyLoginWithPhoneNumber() throws IOException, InterruptedException {

        base.getSheet("LoginData");

        new MainPage(driver).getLoginPage()
                .login(8,"non-contract");
        new LoginPage(driver)
                .checkInputErrorMessages(0,prop.getObject("entryValidInfo"));
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
                .login(9,"non-contract");
        new LoginPage(driver)
                .checkInputErrorMessages(0,prop.getObject("entryValidInfo"));
    }

    /**
     * Case 1.9
     * Hatalı şifre ile login kontrolü
     *
     * @throws IOException
     */
    @Test
    public void unsuccessfullyLoginWithPassword() throws IOException, InterruptedException {

        base.getSheet("LoginData");

        new MainPage(driver).getLoginPage()
                .login(10,"non-contract");
        new LoginPage(driver).checkWarningMessageOnModal(prop.getObject("unmatchingInfo"));
    }


    /**
     * Case 2.0
     * Boş input ile login kontrolü
     * @throws IOException
     */

    @Test
    public void loginWithMissingInfo() throws IOException, InterruptedException {

        base.getSheet("LoginData");

        new MainPage(driver).getLoginPage()
                .login(11,"non-contract");
        new LoginPage(driver).checkWarningMessageOnModal(prop.getObject("canNotEmptyInputMessage"));


    }
    /**
     * Case 2.1
     * Input uyarı mesajı kontrolü
     *
     */

    @Test
    public void loginInputMessageControl () throws IOException, InterruptedException {

        base.getSheet ("LoginData");
        new MainPage(driver).getLoginPage()
                .login(12,"non-contract");
        new LoginPage(driver).checkInputInfoText();

    }

    /**
     * Case 2.2
     * Sözleşmeler
     */
    @Test
    public void confirmContracts() throws IOException, InterruptedException {

        base.getSheet("LoginData");
        db.executeQuery(prop.getObject("deleteContract"));

        new MainPage(driver).getLoginPage()
                .login(13,"contracts")
                .checkUsernameText(13,4);
    }

    /**
     * Case 2.3
     * Tek sözleşme onaylama
     */
    @Test
    public void confirmSingleContract() throws IOException, InterruptedException {

        base.getSheet("LoginData");
        db.executeQuery(prop.getObject("deleteMultipleContract"));

        new MainPage(driver).getLoginPage()
                .login(14,"contract")
                .checkUsernameText(14,4);
    }

}



