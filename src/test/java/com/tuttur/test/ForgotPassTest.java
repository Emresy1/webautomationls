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
    public void successForgotPasswordWithSms() throws IOException {

        base.getSheet("ForgotPassData");

        new MainPage(driver).getLoginPage()
                .getForgotPassModal()
                .forgotPassSteps(1)
                .checkboxClick(0)
                .clickButtonSend()
                .buttonCloseClick()
                .getForgotPassPage()
                .setPasswordChange();
        /// şifre değiştirme sayfası geliştirmesi tamamlandığında , url güncellenicek. Şuan eski url'e gidiyor

    }

    /**
     * Case 1.1
     * Hatalı kullanıcı bilgileri ile kontrol
     */
    @Test
    public void failControl() throws IOException {

        base.getSheet("ForgotPassData");

        new MainPage(driver).getLoginPage()
                .getForgotPassModal()
                .forgotPassSteps(2)
                .checkFailMessage(2);
    }

    /**
     * Case 1.2
     * Geçersiz TCKN kontrolü
     */
    @Test
    public void failControlWithInvalidSsn() throws IOException {

        base.getSheet("ForgotPassData");

        new MainPage(driver).getLoginPage()
                .getForgotPassModal()
                .forgotPassSteps(3)
                .checkFailMessageForSsn(3);

    }

    /**
     * Case 1.3
     * Seçim yapmadan link gönderme konrolü
     */

    @Test
    public void unSelectedSendResetLink() throws IOException {

        base.getSheet("ForgotPassData");

        new MainPage(driver).getLoginPage()
                .getForgotPassModal()
                .forgotPassSteps(4)
                .clickButtonSend();
        new ForgotPassPage(driver).checkFailMessage(4);

    }


}
