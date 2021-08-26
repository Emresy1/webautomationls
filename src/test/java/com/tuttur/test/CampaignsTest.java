package com.tuttur.test;

import com.tuttur.base.BaseTest;
import com.tuttur.configs.PropertiesFile;
import com.tuttur.page.CampaignsPage;
import com.tuttur.page.DbQueriesPage;
import com.tuttur.page.MainPage;
import com.tuttur.util.BasePageUtil;
import org.junit.Test;

import java.io.IOException;

public class CampaignsTest extends BaseTest {

    BasePageUtil base = new BasePageUtil(driver);
    DbQueriesPage db = new DbQueriesPage(driver);
    PropertiesFile prop = new PropertiesFile(driver);

    String deleteCampaignQuery = "UPDATE application.campaign SET status = false WHERE id=";
    String loginCampaign = "EMRE LOGIN KAMPANYASI";
    String logoutCampaign = "EMRE LOGOUT KAMPANYASI";



    /**
     * Case 1.0
     * Kampanyalar empty state
     *
     * @throws IOException
     * @throws InterruptedException
     */

    @Test
    public void campaignsEmptyStateTest() throws IOException, InterruptedException {

                base.getSheet("LoginData");
                db.executeQuery(prop.getObject("deleteCampaigns"));

                new MainPage(driver).getLoginPage()
                        .login(2,"non-contract")
                        .getCampaignsPage()
                        .checkItemsOnCampaignsPage("non-type","");

    }

    /**
     * Case 2.0
     * Oyun tipi : Tüm oyun tipleri ve sana özel badge
     * C2a : Göster
     * Countdown : Göster
     * Gösterilecek kitle : Login
     */

   @Test
   public void campaignTypeOneTest() throws IOException, InterruptedException {

       base.getSheet("LoginData");
       int campaignId = db.getValidationCodeInt(prop.getObject("addCampaignTypeOne"),1);


       new MainPage(driver).getLoginPage()
                           .login(2,"non-contract")
                           .getCampaignsPage()
                           .checkItemsOnCampaignsPage("Type1",loginCampaign +"1");
               new MainPage(driver).logout()
                           .getCampaignsPage()
                           .checkCampaignAfterLogout();

       db.executeQuery(deleteCampaignQuery + campaignId+";");

   }

    /**
     * Case 2.1
     * Oyun tipi : Yok
     * C2a : Kampanyaya Katıl
     * Countdown : Gösterme
     * Gösterilecek kitle : Login
     */

    @Test
    public void campaignTypeTwoTest() throws IOException, InterruptedException {

        base.getSheet("LoginData");
        int campaignId = db.getValidationCodeInt(prop.getObject("addCampaignTypeTwo"),1);

        new MainPage(driver).getLoginPage()
                            .login(2,"non-contract")
                            .getCampaignsPage()
                            .checkItemsOnCampaignsPage("Type2",loginCampaign +"2");

        db.executeQuery(deleteCampaignQuery + campaignId+";");

    }

    /**
     * Case 3.0
     * Oyun tipi : Tüm oyun tipleri ve sana özel badge
     * C2a : Kampanyaya katıl
     * Countdown : Göster
     * Gösterilecek kitle : Logout
     */

    @Test
    public void campaignTypeThreeTest() throws IOException, InterruptedException {

        MainPage main = new MainPage(driver);
        base.getSheet("LoginData");

        int campaignId = db.getValidationCodeInt(prop.getObject("addCampaignTypeThree"),1);

                                main.getCampaignsPage()
                                .checkItemsOnCampaignsPage("Type1",logoutCampaign + "1");
                                main.getLoginPage()
                                .login(2,"non-contract");
                                new CampaignsPage(driver).isExistCampaignInLogin(logoutCampaign + "1");

        db.executeQuery(deleteCampaignQuery + campaignId+";");
    }

    /**
     * Case 3.1
     * Oyun tipi : Yok
     * C2a : Göster
     * Countdown : Gösterme
     * Gösterilecek kitle : Logout
     */

    @Test
    public void campaignTypeFourTest(){

    }
}
