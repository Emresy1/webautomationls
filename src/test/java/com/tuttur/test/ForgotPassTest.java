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

    @Test
    public void forgotPassword () throws IOException {

        base.getSheet("ForgotPassData");

        new MainPage(driver).getLoginPage().getForgotPassModal().forgotPassAction().getForgotPassPage()
                .setPasswordChange();
                /// şifre değiştirme sayfası geliştirmesi tamamlandığında , url güncellenicek. Şuan eski url'e gidiyor

    }


}
