package com.tuttur.constants;

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
	public By APPLE_MARKET = By.className("footerContentDownloads-iosImg");
	public By ANDROID_MARKET = By.className("footerContentDownloads-androidImg");
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
















}
