package com.tuttur.page;

import com.tuttur.base.BasePage;
import com.tuttur.constants.Navigation_Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class NavigationPage extends Navigation_Constants {
    public NavigationPage(WebDriver driver) {
        super(driver);
    }


    public NavigationPage submenu() {

        //clickObjectsBy(HEADER_IDDAA_BUTTON,0);
        List<WebElement> headerMenus = findElements(HEADER_IDDAA_BUTTON);
        List<WebElement> subMenus = findElements(IDDAA_SUBMENU);

        int subCount = 0;
        int headerCount = 0;
        for (int h = headerCount ; h < headerMenus.size() ; h++) {

            headerMenus.get(h).click();
            for (int i = subCount; i < subMenus.size();i++) {
                subMenus.get(i).click();
                WebElement element = subMenus.get(i);
                String Url = element.findElement(By.tagName("a")).getAttribute("href");
                System.out.println(Url);
            }
        }

       return this;

    }


}
