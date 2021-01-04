package com.tuttur.constants;

import com.tuttur.util.BasePageUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.swing.plaf.PanelUI;

public class Navigation_Constants extends BasePageUtil {
    public Navigation_Constants(WebDriver driver) {
        super(driver);
    }

    public By HEADER_IDDAA_BUTTON = By.cssSelector(".headerMenu-item");
    public By IDDAA_SUBMENU = By.className("headerMenu-subMenu-item");

}
