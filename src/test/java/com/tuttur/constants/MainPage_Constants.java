package com.tuttur.constants;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.tuttur.util.BasePageUtil;

public class MainPage_Constants extends BasePageUtil{

	public MainPage_Constants(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public By BUTTON_LOGIN = By.cssSelector(".headerTop-content-loginLink.px-35.mr-10");
	public By ACCOUNT_NO = By.id("userId");
	public By USERNAMETEXT = By.className("headerTop-userMenu-userNameText");
	public By AVATAR = By.className("headerTop-userMenu-avatar");
	public By DROPDOWN_MENU = By.cssSelector(".userDropMenu.visible li");
	public By REGISTER_BUTTON = By.cssSelector(".headerTop-content-registerLink.px-35");
	public By FACEBOOK = By.className("footerContent-socials-facebook");
	public By TWITTER = By.className("footerContent-socials-twitter");
	public By INSTAGRAM = By.className("footerContent-socials-instagram");
	public By YOUTUBE =By.className("footerContent-socials-youtube");
	public By APPLE_MARKET = By.className("footerContentDownloads-appStore");
	public By ANDROID_MARKET = By.className("footerContentDownloads-androidImg");
	public By GALAXY_STORE = By.className("footerContentDownloads-samsungStore");
	public By HUAWEI_STORE = By.className("footerContentDownloads-huaweiStore");
	public By HEADER_IDDAA_BUTTON = By.cssSelector(".headerMenu-item");
	public By IDDAA_SUBMENU = By.className("headerMenu-subMenu-item");
	public By BANNER = By.className("slider-item-img");
	public By BANNER_NORMAL = By.cssSelector(".slider.slider--normal");
	public By BANNER_NEXT = By.cssSelector(".slider.slider--next");
	public By ODD_ON_BANNER = By.className("slider-item-market");
	public By ARROWS = By.cssSelector(".medium.slider-control.slider-control");
	public By SLIDER_BULLET = By.className("slider-bullet");
	public By TOTAL_ODDS = By.className("eventTotalOdds");
	public By BLOCKED_ODD_ON_BANNER = By.className("eventOdd--locked");
	public By ACTIVE_ODD_ON_BANNER = By.cssSelector(".eventOdd.eventOdd--ratio-up.eventOdd--oddNumber");
	public By ODD_OUTCOME_BANNER = By.className("eventOdd");
	public By BETSLIP_EVENT_ROW = By.className("betslipEventRow-contentInfo");
	public By BANNER_OUTCOME = By.className("eventOdd-outcome");
	public By SELECTED_ODD = By.className("eventOdd--selected");
	public By EVENT_CONTENT_INFO = By.className("betslipEventRow-contentInfo");
	public By BETSLIP_OUTCOME = By.className("betslipEventRow-oddItem");
	public By WIDGET_HEADER = By.className("widgetHeader");
	public By LIVE_WIDGET = By.cssSelector(".widget.mostPlayedWidget");
	public By WIDGET_BRANCH = By.className("tabView-tabButton");
	public By FUTBOL_EVENT_ROW = By.cssSelector(".sportsbookEventRow.futbol");
	public By LIVE_EVENT = By.className("sportsbookEventRow--liveMatch");
	public By LEAGUE_FLAG = By.className("sportsbookEventRow-league-flag");
	public By LEAGUE_CODE = By.className("sportsbookEventRow-league-code");
	public By STATUS_PLAYING = By.cssSelector(".status.playing");
	public By MBC = By.className("eventMbc");
	public By TEAMS = By.className("sportsbookEventRow-header-teams");
	public By WIDGET_TEXT = By.className("widgetHeader-text");
	public By LIVE_SCORE = By.cssSelector(".eventScore.live");
	public By LIVE_ICON = By.className("icon-canli_basladi_nocanvas");
	public By LIVE_ODD = By.className("sportsbookEventOdds");
	public By EVENT_TOTAL_ODD = By.className("eventTotalOdds");
	public By LIVE_MATCH_EVENT_ROW = By.cssSelector(".sportsbookEventRow.futbol.sportsbookEventRow--liveMatch");
	public By EVENT_DETAIL_TEAMS = By.className("eventDetailTeams");
	public By NEAR_FUTURE_WİDGET = By.cssSelector(".widget.nearFutureWidget");
	public By POPULAR_WIDGET = By.cssSelector(".widget.popularEventsWidget");
	public By EVENT_ROW = By.className("sportsbookEventRow");
	public By EVENT_TIME = By.className("eventTime");
	public By WIDGET_ACTIVE_TAB = By.cssSelector(".tabView-tabButton.tabView-tabButton-active");
	public By WIDGET_SHOW_ALL = By.className("dashboardWidgetView-showAll");
	public By CAMPAIGNS_ICON = By.className("icon-campaigns");
	public By CAMPAIGNS_BADGE = By.className("campaignBadge-countText");
	public By CAMPAIGNS_CTA = By.className("headerTop-ctaButtons-button");
	public By CAMPAIGNS_EMPTY_STATE = By.className("emptyState");
	public By EMPTY_STATE_ICON = By.className("emptyState-icon");
	public By EMPTY_STATE_TITTLE = By.className("emptyState-title");
	public By EMPTY_STATE_DESC = By.className("emptyState-description");
	public By EMPTY_STATE_CTA = By.className("emptyState-primaryButtons");
	public By EMPTY_STATE_CTA_B = By.cssSelector(".medium.primary");




	public By SOCIAL_FOOTER = By.className("footerContent-socials");















}
