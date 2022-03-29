package com.tuttur.tutturWeb.web.constants;

import com.tuttur.util.BasePageUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WelcomePage_Constants extends BasePageUtil {

    public WelcomePage_Constants(WebDriver driver) {
        super(driver);
    }


    public By NAME = By.className("welcomePage-content-name");
    public By ICON_WEBSITE = By.className("welcomePage-banner-top-title");
}
