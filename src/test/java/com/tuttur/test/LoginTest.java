package com.tuttur.test;

import java.io.IOException;
import org.junit.Test;
import com.tuttur.base.BaseTest;
import com.tuttur.configs.PropertiesFile;
import com.tuttur.page.MainPage;
import org.openqa.selenium.By;

public class LoginTest extends BaseTest{
	PropertiesFile prop = new PropertiesFile(driver);

	/**
	 * Case 1.0
	 * Üye numarasıyla başarılı login
	 * @throws IOException
	 */

	@Test
	public void successfullyLoginWithAccountNo() throws IOException {

		new MainPage(driver).getLoginPage().successfullyLogin().checkAccountNo();
        System.out.println("emre");
        System.out.println("test");

	}

 }
