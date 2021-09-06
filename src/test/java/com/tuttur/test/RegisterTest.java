package com.tuttur.test;


import com.tuttur.base.BaseTest;
import com.tuttur.configs.PropertiesFile;
import com.tuttur.page.*;
import com.tuttur.util.BasePageUtil;
import org.junit.Test;

import java.io.IOException;

public class RegisterTest extends BaseTest {

    PropertiesFile prop = new PropertiesFile(driver);
    GeneralPage general = new GeneralPage(driver);
    BasePageUtil util = new BasePageUtil(driver);

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
    public void successfullyRegisterTest() throws InterruptedException, IOException {

        util.getSheet("RegisterData");

        new MainPage(driver).accountUpdate()
                .getRegisterPage()
                .isExistBanner()
                .setRegisterForm(1)
                .clickMembershipApprove(0)
                .clickSubmit()
                .smsActivation()
                .checkNameOnWelcomePage()
                .isDisplayedUsername();

    }

    /**
     * Case 1.1
     * Mevcut kullanıcı ile register
     * Aynı datalar ile register olunduğunda kullanıcı login olur
     *
     * @throws IOException
     * @throws InterruptedException
     */

    @Test
    public void registerFromLoginWithCurrentUser() throws IOException, InterruptedException {

        util.getSheet("RegisterData");

        new MainPage(driver).getRegisterPage()
                .setRegisterForm(5)
                .clickMembershipApprove(0)
                .clickSubmit();

        new MainPage(driver).checkRegisterLogin();
    }

    /**
     * Case 1.2
     * Mevcut kullanıcı ile register
     * Aynı datalar ile register olup şifre farklı girildiğinde şifremi unuttuma yönlenir.
     */

    @Test
    public void registerFromForgotPassWiithCurrentUser() throws IOException, InterruptedException {

        util.getSheet("RegisterData");

        new MainPage(driver).getRegisterPage()
                .setRegisterForm(6)
                .clickMembershipApprove(0)
                .clickSubmit()
                .checkForgotPasswordModal(6);
    }

    /**
     * Case 1.3
     * Bloklu kullanıcıyla register
     */

    @Test
    public void registerWithBlockedUser() throws IOException, InterruptedException {

        util.getSheet("RegisterData");

        new MainPage(driver).getRegisterPage()
                .setRegisterForm(7)
                .clickMembershipApprove(0)
                .clickSubmit()
                .checkFormErrorMessage(prop.getObject("blockeduserMessage"));
    }

    /**
     * Case 2.0
     * Başarısız üye ol (invalid datalar ile)
     * Yanlış isim
     * Yanlış doğum tarihi
     */

    @Test
    public void registerWithInvalidData() throws IOException, InterruptedException {

        util.getSheet("RegisterData");

        new MainPage(driver)
                .getRegisterPage()
                .isExistBanner()
                .setRegisterForm(3)
                .clickMembershipApprove(0)
                .clickSubmit()
                .checkWarningTextOnModal();
    }

    /**
     * in
     * Case 2.1
     * Numerik ve alfanumerik satırların kural kontrolü
     *
     * @throws IOException
     */

    @Test
    public void fieldsRuleCheck() throws IOException, InterruptedException {

        util.getSheet("RegisterData");

        new MainPage(driver).getRegisterPage()
                .checkInvalidValues()
                .checkBirthdateMaxLenght()
                .check18YearsRule()
                .checkPasswordCombination()
                .inputPatternCheck();

    }

    /**
     * Case 2.2
     * Zorunlu inputların kontrolü
     * @throws IOException
     * @throws InterruptedException
     */

    @Test
    public void mandatoryFieldCheck() throws IOException, InterruptedException {

        util.getSheet("RegisterData");

       // new MainPage(driver).accountUpdate()
                new MainPage(driver).getRegisterPage()
                .formButtonAndInfoControl()
                .isDisplayedPlaceholders()
                .checkMandatoryField()
                .clickMembershipApprove(1);

    }

    /**
     * Case 2.2
     * Sistemde mevcut kullanıcı ile, Ad soyad, doğum tarihi ve tckn bilgileri doğru, diğer inputlara
     * random data girilerek login
     */

    @Test
    public void loginWithCompulsoryInput() throws IOException, InterruptedException {

        util.getSheet("RegisterData");

        new MainPage(driver).getRegisterPage()
                .setRegisterForm(8)
                .clickMembershipApprove(0)
                .clickSubmit();
        new MainPage(driver).checkUserText(general.staticUsername);

    }

    /**
     * Case 2.3
     * Kullanıcının ad, soyad ve doğum tarihi eşleşmedi
     */

    @Test
    public void checkCredentialsDoNotMatch() throws IOException, InterruptedException {

        util.getSheet("RegisterData");

        new MainPage(driver).getRegisterPage()
                .setRegisterForm(9)
                .clickMembershipApprove(0)
                .clickSubmit()
                .checkFormErrorMessage(prop.getObject("credentialsDoNotMatch"));
    }

    /**
     * Case 2.4
     * Sistemde kayıtlı cep telefonu ile register
     */

    @Test
    public void checkExistGsm() throws IOException, InterruptedException {

        util.getSheet("RegisterData");

        new MainPage(driver).accountUpdate()
                .getRegisterPage()
                .setRegisterForm(10)
                .clickMembershipApprove(0)
                .clickSubmit()
                .checkFormErrorMessage(prop.getObject("existGsmMessage"));


        // uyaarı texti registerda görülecek şu an şifremi unuttum modalına göre yazıldı.
    }
}
