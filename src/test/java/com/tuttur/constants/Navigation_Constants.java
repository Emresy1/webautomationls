package com.tuttur.constants;

import com.tuttur.util.BasePageUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.swing.plaf.PanelUI;

public class Navigation_Constants extends BasePageUtil {
    public Navigation_Constants(WebDriver driver) {
        super(driver);
    }

    public By HEADER_MAIN_MENU = By.cssSelector(".headerMenu-item");
    public By HEADER_SUBMENU = By.className("headerMenu-subMenu-item");
    public By HEADER_MENU_ACTIVE= By.cssSelector(".headerMenu-item.active");
    public By SHORTCUT_MENU = By.cssSelector(".branches li");
    public By TUTTUR_ANALYSIS = By.className("secondaryMenu-tutturAnaliz");
    public By POPULAR_COUPONS = By.className("secondaryMenu-popularCoupons");

}
