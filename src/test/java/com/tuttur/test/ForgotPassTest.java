package com.tuttur.test;

import com.tuttur.base.BaseTest;
import com.tuttur.configs.PropertiesFile;
import com.tuttur.page.*;
import com.tuttur.util.BasePageUtil;
import org.junit.Test;


import java.io.IOException;

public class ForgotPassTest extends BaseTest {

    PropertiesFile prop = new PropertiesFile(driver);
    GeneralPage general = new GeneralPage(driver);
    BasePageUtil base = new BasePageUtil(driver);

    public ForgotPassTest() throws IOException {
    }


    /**
     * Case 1.0
     * Başarılı şifremi unuttum akışı
     *
     * @throws IOException
     */
    @Test
    public void successForgotPasswordWithSms() throws IOException, InterruptedException {

        base.getSheet("ForgotPassData");

        new MainPage(driver).getLoginPage()
                .getForgotPassModal()
                .setUserInfo(1)
                .checkboxClick(0)
                .clickButtonSend()
                .setVerifyCode()
                .changePassword();
        new LoginPage(driver).login(1, "non-contract");
    }
    //  new MainPage(driver).checkUsernameText(1)--- csv cell alanı güncelleneicek , setdata methodu ile

    @Test
    public void inputPublicControls() throws IOException {

        base.getSheet("ForgotPassData");

        new MainPage(driver).getLoginPage()
                .getForgotPassModal()
                .invalidSsnControls()
                .invalidBirthdateControls();


    }

    /**
     * Case 1.1
     * Hatalı kullanıcı bilgileri ile kontrol
     */
    @Test
    public void inalidBirthdayTest() throws IOException {

        base.getSheet("ForgotPassData");

        new MainPage(driver).getLoginPage()
                .getForgotPassModal()
                .setUserInfo(2)
                .checkFailMessage();
    }

    /**
     * Case 1.2
     * Geçersiz TCKN kontrolü
     */
    @Test
    public void invalidSsnTest() throws IOException {

        base.getSheet("ForgotPassData");

        new MainPage(driver).getLoginPage()
                .getForgotPassModal()
                .setUserInfo(3)
                .checkFailMessageForSsn();

    }


}
