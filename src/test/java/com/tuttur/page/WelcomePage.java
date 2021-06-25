package com.tuttur.page;

import com.tuttur.constants.WelcomePage_Constants;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import java.io.IOException;


public class WelcomePage extends WelcomePage_Constants {

    public WelcomePage(WebDriver driver) {
        super(driver);
    }

    public MainPage checkNameOnWelcomePage() throws IOException {

        String nameLastName = getData(1,1).toUpperCase()
                + " " + getData(1,2).toUpperCase();

        Assert.assertTrue("İsim ve soyisim eşleşmedi", nameLastName.equals(getElementBy(NAME).getText()));

        clickObjectBy(ICON_WEBSITE);

        return new MainPage(driver);
    }

}
