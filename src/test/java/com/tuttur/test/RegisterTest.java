package com.tuttur.test;


import com.tuttur.base.BaseTest;
import com.tuttur.configs.PropertiesFile;
import com.tuttur.page.GeneralPage;
import com.tuttur.page.MainPage;
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
    public void successFullyRegisterTest() throws InterruptedException, IOException {

        util.getSheet("RegisterData");

        new MainPage(driver).accountUpdate()
                .getRegisterPage()
                .isExistBanner()
                .setRegisterForm(1,0)
                .checkMembershipMandatory()
                .clickMembershipApprove()
                .clickSubmit()
                .smsActivation()
                .checkUserText(general.username);

    }

    /**
     * Case 1.1
     * Başarılı üye ol (username kullan fonksiyonu ile)
     */

    @Test
    public void registerWithUseFunctional() throws IOException, InterruptedException {

        util.getSheet("RegisterData");

        new MainPage(driver).accountUpdate()
                .getRegisterPage()
                .isExistBanner()
                .setRegisterForm(2,1)
                .clickMembershipApprove()
                .clickSubmit()
                .smsActivation()
                .checkUserText(general.generateUsernameText.toString());

    }

    /**
     * Case 1.2
     * Mevcut kullanıcı ile register
     * Aynı datalar ile register olunduğunda kullanıcı login olur
     *
     * @throws IOException
     * @throws InterruptedException
     */

    @Test
    public void currentUserRegisterToLogin () throws IOException, InterruptedException {

        util.getSheet("RegisterData");

        new MainPage(driver).getRegisterPage()
                .setRegisterForm(5,0)
                .clickMembershipApprove()
                .clickSubmit();

        new MainPage(driver).checkRegisterLogin();
    }

    /**
     * Case 1.3
     * Mevcut kullanıcı ile register
     * Aynı datalar ile register olup şifre farklı girildiğinde şifremi unuttuma yönlenir.
     */

    @Test
    public void currentUserRegisterToForgotPassword() throws IOException, InterruptedException {

        util.getSheet("RegisterData");

        new MainPage(driver).getRegisterPage()
                .setRegisterForm(6,0)
                .clickMembershipApprove()
                .clickSubmit()
                .checkForgotPasswordModal(6);
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

        new MainPage(driver).getRegisterPage()
                .isExistBanner()
                .setRegisterForm(3,0)
                .clickMembershipApprove()
                .clickSubmit()
                .checkWarningTextOnModal(3,10,11);
    }

    /**in
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
                .inputPatternCheck()
                .checkUsernameCombination();

    }

    @Test
    public void mandatoryFieldCheck () throws IOException, InterruptedException {

        util.getSheet("RegisterData");

        new MainPage(driver).accountUpdate()
                .getRegisterPage()
                .formButtonAndInfoControl()
                .checkMandatoryField()
                .checkMembershipMandatory();



    }

}
