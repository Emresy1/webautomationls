package com.tuttur.page;

import com.tuttur.constants.WelcomePage_Constants;
import static  org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
