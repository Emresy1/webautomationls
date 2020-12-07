package com.tuttur.page;



import com.tuttur.configs.PropertiesFile;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.tuttur.constants.MainPage_Constants;

import java.io.IOException;


public class MainPage extends MainPage_Constants{

	public MainPage(WebDriver driver) {
		super(driver);
	}

	PropertiesFile prop = new PropertiesFile(driver);



	public LoginPage getLoginPage(){
		clickObjectBy(BUTTON_LOGIN);
		return new LoginPage(driver);
	}

	public MainPage checkAccountNo() throws IOException {

		Assert.assertTrue("Account numarası doğru değil", getElementBy(ACCOUNT_NO).getText().equals(prop.getObject("account_no")));
		System.out.println("Account numarası ile login, başarılı şekilde geçti");
		return this;
	}
	public MainPage checkUsernameText() throws IOException {
		Assert.assertTrue("Kullanıcı adı doğru değil",getElementBy(USERNAMETEXT).getText().equals(prop.getObject("username")));
		return  this;
	}


}
	
	
