package com.tuttur.tutturWeb.web.page;

import com.tuttur.tutturWeb.web.constants.WelcomePage_Constants;
import static  org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;


public class WelcomePage extends WelcomePage_Constants {

    public WelcomePage(WebDriver driver) {
        super(driver);
    }

    public MainPage checkNameOnWelcomePage() throws IOException {

        clickObjectBy(By.className("toastMessage-close"));

        String nameLastName = getData(1,1).toUpperCase()
                + " " + getData(1,2).toUpperCase();

       assertTrue(nameLastName.equals(getElementBy(NAME).getText().substring(11)), "İsim ve soyisim eşleşmedi");


        clickObjectBy(ICON_WEBSITE);

        return new MainPage(driver);
    }

}
