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
		
	








	
	

	

}
