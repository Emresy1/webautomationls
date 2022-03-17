package com.tuttur.page;

import com.tuttur.configs.PropertiesFile;
import com.tuttur.constants.CampaignsPage_Constants;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CampaignsPage extends CampaignsPage_Constants {

    public CampaignsPage(WebDriver driver) {
        super(driver);
    }

    PropertiesFile prop = new PropertiesFile(driver);


    public CampaignsPage checkItemsOnCampaignsPage (String type, String campaignName)
            throws IOException, InterruptedException {

        if (isExist(MIN_WAIT_4_ELEMENT,CAMPAIGN_ITEM)){

            List<WebElement> campaignItem = driver.findElements(CAMPAIGN_ITEM);



            switch (type){

                case "Type1":

                    for (WebElement item: campaignItem) {

                        if (item.findElement(CAMPAIGN_TITLE).getText().equals(campaignName)){

                            boolean isDisplayed = item.findElement(IDDAA_BADGE).isDisplayed() &&
                                                  item.findElement(MP_BADGE).isDisplayed() &&
                                                  item.findElement(SPOR_TOTO_BADGE).isDisplayed() &&
                                                  item.findElement(FOR_YOU_BADGE).isDisplayed() &&
                                                  item.findElement(COUNTDOWN).isDisplayed();

                            assertTrue(isDisplayed);
                            checkCampaignDetail();

                        }

                    }
                    break;

                case "Type2":

                    for (WebElement item: campaignItem) {

                        int size = 0;
                        if (item.findElement(CAMPAIGN_TITLE).getText().equals(campaignName)){

                            boolean isExist = item.findElements(FOR_YOU_BADGE).size() == size &&
                                              item.findElements(GAME_TYPE_BADGES).size() == size;

                            assertTrue(isExist);
                            checkCampaignDetail();

                        }
                    }
                 }
               }
        else {

            boolean isTrue = getElementBy(EMPTY_STATE_ICON).isDisplayed()
                    && getElementBy(EMPTY_STATE_CTA).isDisplayed();
            boolean isEquals = getElementBy(EMPTY_STATE_TITTLE).getText()
                    .equals(prop.getObject("emptyStateTitle")) && getElementBy(EMPTY_STATE_DESC).getText()
                    .equals(prop.getObject("emptyStateDesc"));

            assertTrue(isTrue);
            assertTrue(isEquals);

            clickCtaButton();
            // c2a butonu canlı event varsa canlı bülttene yoksa preye gidecek.
            // kampanyalar sayfasından back yapıldığında hangi ekrandan gidildiyse oraya geri dönmeli.
            // kampaanyalarda paylaş butonu sadece herkese özel kampanyalardaa görmeli

        }

        return this;
    }

    public void isExistCampaignInLogin(String campaignName){

        int size = 0;
        waitForElement(driver,MAX_WAIT_4_ELEMENT,CAMPAIGN_ITEM);

        if (driver.findElements(CAMPAIGN_ITEM).size() != size) {
            List<WebElement> campaignItem = driver.findElements(CAMPAIGN_ITEM);

            for (WebElement item: campaignItem) {

                Assert.assertFalse(item.getText().equals(campaignName));
            }
        }
    }

    public CampaignsPage getCampaignDetail(String campaignName){


        List<WebElement> campaignItems = driver.findElements(CAMPAIGN_ITEM);

        for (WebElement campaign: campaignItems) {


            if (campaign.findElement(CAMPAIGN_NAME).getText().toUpperCase(Locale.ROOT).equals(campaignName)){

                campaign.findElement(CAMPAIGN_ARROW).click();
            }
        }

    return this;
    }
    public void checkCampaignAfterLogout() {

        int size = 0;
        if (driver.findElements(CAMPAIGN_ITEM).size() != size) {

            List<WebElement> campaignItem = driver.findElements(CAMPAIGN_ITEM);

            campaignItem.stream().filter(item -> !item.getText().equals("EMRE LOGIN KAMPANYASI 1"))
                    .collect(Collectors.toList());

            assertTrue(campaignItem.size() == size);
        }
    }
    private void checkCampaignDetail() throws InterruptedException {

        clickObjectBy(BUTTON_CAMPAIGN_DETAIL);
        waitForElement(driver,OPT_WAIT_4_ELEMENT,CAMPAIGN_TITLE);
        scrollToElement(By.className("widgetHeader"));
        getElemenstBy(CAMPAIGN_TITLE,1).isDisplayed();

    }

    private void clickCtaButton () {

        clickObjectsBy(EMPTY_STATE_CTA_B,0);
        assertTrue(driver.getCurrentUrl().contains("bulten"));

    }
}
