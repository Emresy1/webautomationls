package com.tuttur.page;

import com.tuttur.base.BasePage;
import com.tuttur.configs.PropertiesFile;
import com.tuttur.constants.Navigation_Constants;
import com.tuttur.util.BasePageUtil;
import com.tuttur.util.ExcelUtil;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class NavigationPage extends Navigation_Constants {
    public NavigationPage(WebDriver driver) {
        super(driver);
    }

    Actions actions = new Actions(driver);
    PropertiesFile prop = new PropertiesFile(driver);
    ExcelUtil util = new ExcelUtil(driver);
    BasePageUtil base = new BasePageUtil(driver);

    public NavigationPage checkSubmenuUrl() throws IOException {

        List<WebElement> headerMenus = findElements(HEADER_MAIN_MENU);

        int subCount = 0;
        int headerCount = 0;
        for (int h = headerCount; h < headerMenus.size(); h++) {

            headerMenus.get(h).click();
            Assert.assertEquals(driver.getCurrentUrl().substring(44), headerMenu().get(h));

            List<WebElement> subMenus = findElements(HEADER_SUBMENU);


            if (subMenus.size() != 0) {

                WebElement headerMenuActive = driver.findElement(HEADER_MENU_ACTIVE);

                for (int i = subCount; i < subMenus.size(); i++) {

                    if (headerMenuActive.getText().equals("İDDAA")) {

                        subMenus.get(i).click();
                        Assert.assertEquals(driver.getCurrentUrl().substring(44), betSubmenus().get(i));

                    } else if (headerMenuActive.getText().equals("SOSYAL BAHİS")) {

                        subMenus.get(i).click();
                        Assert.assertEquals(driver.getCurrentUrl().substring(44), socialSubMenus().get(i));

                    } else if (headerMenuActive.getText().equals("SPOR TOTO")) {

                        subMenus.get(i).click();
                        Assert.assertEquals(driver.getCurrentUrl().substring(44), sportTotoSubmenus().get(i));

                    } else if (headerMenuActive.getText().equals("TJK")) {

                        subMenus.get(i).click();
                        Assert.assertEquals(driver.getCurrentUrl().substring(44), tjkSubmenus().get(i));

                    }

                }
            }


        }

        return this;

    }

    public NavigationPage checkShortcutMenuUrl() throws IOException {

        int count = 0;
        List<WebElement> shortcutList = findElements(SHORTCUT_MENU);

        for (int i = count; i < shortcutList.size(); i++) {
            shortcutList.get(i).click();

            Assert.assertEquals("Url hatalı", driver.getCurrentUrl().substring(44), shortcutMenus().get(i));
        }
        return this;

    }

    private List<String> headerMenu() throws IOException {

        base.getSheet("NavigationUrl");
        List<String> menuUrl = util.getRowDataAll(2, 7);
        return menuUrl;

    }

    private List<String> betSubmenus() throws IOException {

        base.getSheet("NavigationUrl");
        List<String> betUrlData = util.getRowDataAll(8, 18);

        return betUrlData;

    }

    private List<String> socialSubMenus() throws IOException {

        base.getSheet("NavigationUrl");
        List<String> socialUrlData = util.getRowDataAll(21, 25);

        return socialUrlData;

    }

    private List<String> sportTotoSubmenus() throws IOException {

        base.getSheet("NavigationUrl");
        List<String> sportTotoUrlData = util.getRowDataAll(26,28);

        return sportTotoUrlData;

    }

    private List<String> tjkSubmenus() throws IOException {

        base.getSheet("NavigationUrl");
        List<String> tjkUrlData = util.getRowDataAll(29,31);

        return tjkUrlData;

    }

    private List<String> shortcutMenus() throws IOException {

        base.getSheet("NavigationUrl");
        List<String> shortcutUrlData = util.getRowDataAll(32,38);

        return shortcutUrlData;

    }


}
