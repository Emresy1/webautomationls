package com.tuttur.test;

import com.tuttur.base.BaseTest;
import com.tuttur.configs.PropertiesFile;
import com.tuttur.page.DbQueriesPage;
import com.tuttur.page.GeneralPage;
import com.tuttur.page.LoginPage;
import com.tuttur.page.MainPage;
import com.tuttur.util.BasePageUtil;
import org.junit.Test;

import java.io.IOException;

public class CampaignsTest extends BaseTest {

    BasePageUtil base = new BasePageUtil(driver);
    DbQueriesPage db = new DbQueriesPage(driver);
    PropertiesFile prop = new PropertiesFile(driver);

    String deleteCampaignQuery = "UPDATE application.campaign SET status = false WHERE id=";



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
                db.executeQuery(prop.getObject("deleteCampaign"));
                // burda tüm kampanyaları silecek query gerekiyor


                new MainPage(driver).getLoginPage()
                        .login(2,"non-contract")
                        .getCampaignsPage()
                        .checkItemsOnCampaignsPage("non-type");

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
                           .getCampaignDetail("Emre Login Kampanyası 1")
                           .checkItemsOnCampaignsPage("Type1");
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
                            .checkItemsOnCampaignsPage("Type2");

        db.executeQuery(deleteCampaignQuery + campaignId+";");


    }
}
