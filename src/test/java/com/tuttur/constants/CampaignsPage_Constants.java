package com.tuttur.constants;

import com.tuttur.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CampaignsPage_Constants extends BasePage {

    public CampaignsPage_Constants(WebDriver driver) {
        super(driver);
    }

    public By EMPTY_STATE_ICON = By.className("emptyState-icon");
    public By EMPTY_STATE_TITTLE = By.className("emptyState-title");
    public By EMPTY_STATE_DESC = By.className("emptyState-description");
    public By EMPTY_STATE_CTA = By.className("emptyState-primaryButtons");
    public By EMPTY_STATE_CTA_B = By.cssSelector(".medium.primary");
    public By CAMPAIGN_ITEM = By.className("campaignItem");
    public By EMPTY_STATE = By.className("emptyState");
    public By IDDAA_BADGE = By.className("iddaaBadge");
    public By MP_BADGE = By.className("mpBadge");
    public By SPOR_TOTO_BADGE = By.className("sporTotoBadge");
    public By FOR_YOU_BADGE = By.className("campaignForYouContainer");
    public By CAMPAIGN_NAME = By.cssSelector(".campaignTitle.campaignTitle-lineClamp");
    public By COUNTDOWN = By.className("campaignTimeContent");
    public By BUTTON_CAMPAIGN_DETAIL = By.cssSelector(".medium.primary.campaignItem-button");
    public By CAMPAIGN_TITLE = By.className("campaignTitle");
    public By GAME_TYPE_BADGES = By.className("campaignForYouContainer");
    public By CAMPAIGN_ARROW = By.cssSelector(".inboxItem-title.line-clamp.line-clamp-title");
}
