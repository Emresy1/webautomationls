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
	public By AVATAR = By.className("profileImg");
	public By DROPDOWN_MENU = By.cssSelector(".userDropMenu.visible li");
	public By REGISTER_BUTTON = By.cssSelector(".headerTop-content-registerLink.px-35");
	public By FACEBOOK = By.className("footerContent-socials-facebook");
	public By TWITTER = By.className("footerContent-socials-twitter");
	public By INSTAGRAM = By.className("footerContent-socials-instagram");
	public By YOUTUBE =By.className("footerContent-socials-youtube");
	public By APPLE_MARKET = By.className("footerContentDownloads-appStore");
	public By ANDROID_MARKET = By.className("footerContentDownloads-playStore");
	public By HUAWEI_MARKET = By.className("footerContentDownloads-huaweiStore");
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
	public By BLOCKED_ODD = By.className("eventOdd--locked");
	public By ACTIVE_ODD_ON_BANNER = By.cssSelector(".eventOdd.eventOdd--ratio-up.eventOdd--oddNumber");
	public By ODD_OUTCOME_BANNER = By.className("eventOdd");
	public By BANNER_OUTCOME = By.className("eventOdd-outcome");
	public By SELECTED_ODD = By.className("eventOdd--selected");
	public By EVENT_CONTENT_INFO = By.className("betslipEventRow-contentInfo");
	public By BETSLIP_OUTCOME = By.className("betslipEventRow-oddItem");
	public By WIDGET_HEADER = By.className("widgetHeader");
	public By LIVE_WIDGET = By.cssSelector(".widget.mostPlayedWidget");
	public By WIDGET_BRANCH = By.className("tabView-tabButton");
	public By FUTBOL_EVENT_ROW = By.cssSelector(".sportsbookEventRow.futbol");
	public By SPORTSBOOK_EVENT_ROW_HEADER = By.className("sportsbookEventRow-header");
	public By SPORTSBOOK_EVENT_ROW_INFO = By.className("sportsbookEventRow-header-info");
	public By LIVE_EVENT = By.className("sportsbookEventRow--liveMatch");
	public By LEAGUE_FLAG = By.className("sportsbookEventRow-league-flag");
	public By LEAGUE_CODE = By.className("sportsbookEventRow-league-code");
	public By STATUS_PLAYING = By.cssSelector(".status.playing");
	public By MBC = By.className("eventMbc");
	public By MBC_VALUE = By.className("mbc-value");
	public By OUTCOME = By.className("eventOdd-outcome");
	public By EVENT_COUNT_ON_BETSLIP = By.className("betslip-header-eventCount");
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
	public By TAB_VIEW_LABELS = By.className("tabView-tabLabels");
	public By MODAL_CONTAINER = By.className("modal-container");
	public By WIDGET_SHOW_ALL = By.className("dashboardWidgetView-showAll");
	public By CAMPAIGNS_ICON = By.className("header-icon-campaigns");
	public By TICKET_ICON = By.className("icon-tickets");
	public By CAMPAIGNS_BADGE = By.className("campaignBadge-countText");
	public By CAMPAIGNS_CTA = By.className("headerTop-ctaButtons-button");
	public By CAMPAIGNS_EMPTY_STATE = By.className("emptyState");
	public By MYCOUPON_BETSLIP = By.id("tabButtonActive");
    public By BETSLIP_EMPTY_STATE_TITLE = By.className("betslipState-title");
    public By BETSLIP_EMPTY_STATE_DESC = By.className("betslipState-description");
    public By LIVEBET_SHORTCUT = By.className("branches-img");
    public By LIVEBET_BTN_BETSLIP = By.cssSelector(".medium.primary.button.undefined");
    public By BULLETIN_FAV_TAB = By.className("sportsbookInPlayFiltersRow-filterButtons");
    public By CONTINUI_BETSLIP = By.id("tabButton");
    public By BETSLIP_EMPTY_ICON = By.cssSelector(".betslipState-icon.empty");
    public By EVENT_TEAMS_NAME = By.className("sportsbookEventRow-header-team");
    public By BETSLIP_EVENT_ROW = By.className("betslipEventRow");
    public By BETSLIP_EVENT_DATE = By.className("betslipEventRow-timeText");
    public By BETSLIP_EVENT_OUTCOME = By.className("betslipEventRow-oddItem");
    public By BETSLIP_TEAMS_NAME = By.className("betslipEventRow-teamText");
    public By BETSLIP_MARKET_TEXT = By.className("betslipEventRow-marketText");
    public By BETSLIP_ODD_TEXT = By.className("betslipEventRow-oddText");
    public By BETSLIP_HOME_TEAM = By.cssSelector(".betslipEventRow-teamText-team.home");
    public By BETSLIP_AWAY_TEAM = By.cssSelector(".betslipEventRow-teamText-team.away");
    public By BUTTON_PLAY = By.cssSelector(".medium.primary.betslipButtonsContainer-play");
    public By PLAY_COUPON_INFO = By.className("betslip-loadingWrapper-container");
    public By BETSLIP_STATE = By.className("betslipState");
    public By BETSLIP_STATE_TITLE = By.className("betslipState-title");
    public By BUTTON_CLOSE_MODAL = By.cssSelector(".medium.betslipState-close");
    public By BETSLIP_TAB = By.className("betslipMyBetsContainer-tabTexts");
    public By COUPON_CARD = By.className("iddaaCouponItem-card");
    public By COUPON_CARD_INFO = By.className("iddaaCouponSummary-summaryColumnText");
    public By EVENT_COUNT_ON_CARD = By.className("iddaaCouponHeader-eventCount");
    public By EVENT_ODD_NAME = By.className("eventOdd-name");
    public By MAX_GAIN = By.className("betslipInfoContainer-infoBox-top-totalOutcome");
    public By BETSLIP_VALUE = By.className("betslipInfoContainer-infoBox-top");
    public By WIDGET_DATE = By.className("date");
    public By WIDGET_TIME = By.className("time");







	public By SOCIAL_FOOTER = By.className("footerContent-socials");















}
