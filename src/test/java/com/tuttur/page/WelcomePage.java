package com.tuttur.page;

import com.tuttur.constants.WelcomePage_Constants;
import static  org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.io.IOException;


public class WelcomePage extends WelcomePage_Constants {

    public WelcomePage(WebDriver driver) {
        super(driver);
    }

    public MainPage checkNameOnWelcomePage() throws IOException {

        String nameLastName = getData(1,1).toUpperCase()
                + " " + getData(1,2).toUpperCase();

       assertTrue("İsim ve soyisim eşleşmedi",
               nameLastName.equals(getElementBy(NAME).getText().substring(11)));

        clickObjectBy(ICON_WEBSITE);

        return new MainPage(driver);
    }

}
