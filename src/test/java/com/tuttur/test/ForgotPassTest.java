package com.tuttur.test;

import com.tuttur.base.BaseTest;
import com.tuttur.configs.PropertiesFile;
import com.tuttur.page.*;
import com.tuttur.util.BasePageUtil;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


import java.io.IOException;

public class ForgotPassTest extends BaseTest {


    PropertiesFile prop = new PropertiesFile(driver);
    BasePageUtil base = new BasePageUtil(driver);
    //MainPage main = new MainPage(driver);


//    /**
//     * Case 1.0
//     * Başarılı şifremi unuttum akışı gsm
//     *
//     * @throws IOException
//     */
//    @Test
//    public void successForgotPasswordWithSms() throws IOException, InterruptedException {
//
//        ForgotPassPage fp = new ForgotPassPage(driver);
//        base.getSheet("ForgotPassData");
//
//                new MainPage(driver).getLoginPage()
//                .getForgotPassModal()
//                .setUserInfo(1)
//                .clickResetPassword()
//                .checkboxClick(0)
//                .clickButtonSend()
//                .setVerifyCode(fp.verifyCode(prop.getData(1,8)))
//                .changePassword(1,2);
//        new LoginPage(driver).login(1, "non-contract")
//                .checkUsernameText(1 ,6);
//    }
//
//
//    /**
//     * Case 1.1
//     * Başarılı şifremi unuttum akışı e-posta
//     *
//     * @throws IOException
//     */
//    @Test
//
//    public void successForgotPasswordWithEmail() throws IOException, InterruptedException {
//
//        ForgotPassPage fp = new ForgotPassPage(driver);
//        base.getSheet("ForgotPassData");
//
//        new MainPage(driver).getLoginPage()
//                .getForgotPassModal()
//                .setUserInfo(2)
//                .clickResetPassword()
//                .checkboxClick(1)
//                .clickButtonSend()
//                .setVerifyCode(prop.getObject("verifyCodeEmail"))
//                .changePassword(2,2);
//        new LoginPage(driver).login(2, "non-contract")
//                .checkUsernameText(2,6);
//    }
//
//
//    /**
//     * Case 1.2
//     * Geçersiz bilgiler ile input kontrolleri
//     *
//     * @throws IOException
//     */
//    @Test
//    public void inputPublicControls() throws IOException {
//
//        base.getSheet("ForgotPassData");
//
//        new MainPage(driver).getLoginPage()
//                .getForgotPassModal()
//                .invalidSsnWithYearControls()
//                .invalidBirthdateControls();
//    }
//
//    /**
//     * Case 1.3
//     * Eşleşmeyen şifre kontrolü
//     */
//    @Test
//    public void unmatchPasswordControl() throws IOException {
//
//        ForgotPassPage fp = new ForgotPassPage(driver);
//        base.getSheet("ForgotPassData");
//
//        new MainPage(driver).getLoginPage()
//                .getForgotPassModal()
//                .setUserInfo(4)
//                .clickResetPassword()
//                .checkboxClick(0)
//                .clickButtonSend()
//                .setVerifyCode(fp.verifyCode(prop.getData(4,8)))
//                .checkUnmatchPassword();
//    }
//
////    /**
////     * Case 1.3
////     * Verify code
////     */
////    @Test
////    public void verifyCodeTest() throws IOException {
////
////        base.getSheet("ForgotPassData");
////
////        new MainPage(driver).getLoginPage()
////                .getForgotPassModal()
////                .setUserInfo(5)
////                .clickResetPassword()
////                .checkboxClick(0)
////                .clickButtonSend()
////                .countdown();
////    }


}
