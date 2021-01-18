package com.tuttur.page;

import com.tuttur.base.BasePage;
import com.tuttur.configs.PropertiesFile;
import com.tuttur.constants.Navigation_Constants;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class NavigationPage extends Navigation_Constants {
    public NavigationPage(WebDriver driver) {
        super(driver);
    }

    Actions actions = new Actions(driver);
    PropertiesFile prop = new PropertiesFile(driver);

    public NavigationPage checkSubmenuUrl() throws IOException {

        List<WebElement> headerMenus = findElements(HEADER_MAIN_MENU);

        int subCount = 0;
        int headerCount = 0;
        for (int h = headerCount; h < headerMenus.size(); h++) {

            headerMenus.get(h).click();
            Assert.assertEquals(driver.getCurrentUrl().substring(44), headerMenu()[h]);

            List<WebElement> subMenus = findElements(HEADER_SUBMENU);


            if (subMenus.size() != 0) {

                WebElement headerMenuActive = driver.findElement(HEADER_MENU_ACTIVE);

                for (int i = subCount; i < subMenus.size(); i++) {

                    if (headerMenuActive.getText().equals("İDDAA")) {

                        subMenus.get(i).click();
                        Assert.assertEquals(driver.getCurrentUrl().substring(44), betSubmenus()[i]);

                    } else if (headerMenuActive.getText().equals("SOSYAL BAHİS")) {

                        subMenus.get(i).click();
                        Assert.assertEquals(driver.getCurrentUrl().substring(44), socialSubMenus()[i]);

                    } else if (headerMenuActive.getText().equals("SPOR TOTO")) {

                        subMenus.get(i).click();
                        Assert.assertEquals(driver.getCurrentUrl().substring(44), sportTotoSubmenus()[i]);

                    } else if (headerMenuActive.getText().equals("TJK")) {

                        subMenus.get(i).click();
                        Assert.assertEquals(driver.getCurrentUrl().substring(44), tjkSubmenus()[i]);

                    }

                }
            }


        }

        return this;

    }

    public NavigationPage checkShortcutMenuUrl () throws IOException {

        int count = 0;
        List<WebElement> shortcutList = findElements(SHORTCUT_MENU);

        for (int i = count; i < shortcutList.size();i++) {
            shortcutList.get(i).click();

            Assert.assertEquals("Url hatalı",driver.getCurrentUrl().substring(44),shortcutMenus()[i]);
        }
        return this;

    }
    private String[] headerMenu() throws IOException {

        String[] menus = {prop.getObject("bet"), prop.getObject("socialBet"),
                prop.getObject("sportToto")
                , prop.getObject("tjk"), prop.getObject("campaigns")};
        return menus;

    }

    private String[] betSubmenus() throws IOException {

        String[] betUrlData = {prop.getObject("liveBet"), prop.getObject("football"),
                prop.getObject("pingPong"),
                prop.getObject("basketball")
                , prop.getObject("tennis")
                , prop.getObject("iceHokey"), prop.getObject("handball")
                , prop.getObject("volleyball")
                //, prop.getObject("snooker")
                , prop.getObject("longTerm")};

        return betUrlData;
    }

    private String[] socialSubMenus() throws IOException {

        String[] socialUrlData = {prop.getObject("theBest")
                , prop.getObject("allPosts"), prop.getObject("mostPlayed"), prop.getObject("kingsOfBet")};


        return socialUrlData;

    }

    private String[] sportTotoSubmenus() throws IOException {

        String[] sportTotoUrlData = {prop.getObject("sportToto"), prop.getObject("sportTotoResults")};


        return sportTotoUrlData;

    }

    private String[] tjkSubmenus() throws IOException {

        String[] tjkUrlData = {prop.getObject("tjk"),
                prop.getObject("tjkResults")};

        return tjkUrlData;

    }

    private String[] shortcutMenus() throws IOException {

        String[] shortcutUrlData = {prop.getObject("liveBet"), prop.getObject("football"),
                prop.getObject("pingPong"),
                prop.getObject("basketball"),
                prop.getObject("socialBet"),
                prop.getObject("tutturAnalysis")};

        return shortcutUrlData;

    }


}
