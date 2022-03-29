package com.tuttur.tutturWeb.web.page;

import com.tuttur.tutturWeb.web.constants.Navigation_Constants;
import com.tuttur.util.BasePageUtil;
import com.tuttur.util.ExcelUtil;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class NavigationPage extends Navigation_Constants {
    public NavigationPage(WebDriver driver) {
        super(driver);
    }


    ExcelUtil util = new ExcelUtil(driver);
    BasePageUtil base = new BasePageUtil(driver);

    public NavigationPage checkSubmenuUrl() throws IOException {

        List<WebElement> headerMenus = findElements(HEADER_MAIN_MENU);

        int subMenuIndex = 0;
        int headerCount = 0;
        for (int headerIndex = headerCount; headerIndex < headerMenus.size(); headerIndex++) {

            headerMenus.get(headerIndex).click();
            Assert.assertEquals(subUrl(), headerMenu().get(headerIndex));
           // assertEquals(subUrl(), headerMenu().get(headerIndex));

            List<WebElement> subMenus = findElements(HEADER_SUBMENU);

            if (subMenus.size() != 0) {

                String headerMenuActive = driver.findElement(HEADER_MENU_ACTIVE).getText();

                for (int i = subMenuIndex; i < subMenus.size(); i++) {

                    if (headerMenuActive.equals("İDDAA")) {

                        subMenus.get(i).click();

                        assertTrue(subUrl().contains(betSubmenus().get(i)) ||
                                subUrl().contains(betSubmenus().get(i + 1))         ||
                                subUrl().contains(betSubmenus().get(i+2)));

                    } else if (headerMenuActive.equals("SOSYAL BAHİS")) {

                        subMenus.get(i).click();
                        assertEquals(subUrl(), socialSubMenus().get(i));

                    } else if (headerMenuActive.equals("SPOR TOTO")) {

                        subMenus.get(i).click();
                        assertEquals(subUrl(), sportTotoSubmenus().get(i));

                    } else if (headerMenuActive.equals("TJK")) {

                        subMenus.get(i).click();
                        assertEquals(subUrl(), tjkSubmenus().get(i));

                    }
                }
            }


        }

        return this;

    }


    private String subUrl() {

        return driver.getCurrentUrl().substring(44);

    }

    public boolean checkShortcutMenuUrl() throws IOException {

        boolean isEquals = false;

        int count = 0;
        List<WebElement> shortcutList = findElements(SHORTCUT_MENU);

        for (int i = count; i < shortcutList.size(); i++) {
            shortcutList.get(i).click();

            isEquals = subUrl().contains(shortcutMenus().get(i));
            
        }
        return isEquals;

    }

    private List<String> headerMenu() throws IOException {

        base.getSheet("NavigationUrl");
        List<String> menuUrl = util.getRowDataAll(2, 8);
        return menuUrl;

    }

    private List<String> betSubmenus() throws IOException {

        base.getSheet("NavigationUrl");
        List<String> betUrlData = util.getRowDataAll(8, 20);

        return betUrlData;

    }

    private List<String> socialSubMenus() throws IOException {

        base.getSheet("NavigationUrl");
        List<String> socialUrlData = util.getRowDataAll(23, 30);

        return socialUrlData;

    }

    private List<String> sportTotoSubmenus() throws IOException {

        base.getSheet("NavigationUrl");
        List<String> sportTotoUrlData = util.getRowDataAll(31, 33);

        return sportTotoUrlData;

    }

    private List<String> tjkSubmenus() throws IOException {

        base.getSheet("NavigationUrl");
        List<String> tjkUrlData = util.getRowDataAll(34, 36);

        return tjkUrlData;

    }

    private List<String> shortcutMenus() throws IOException {

        base.getSheet("NavigationUrl");
        List<String> shortcutUrlData = util.getRowDataAll(38, 47);

        return shortcutUrlData;

    }


}
