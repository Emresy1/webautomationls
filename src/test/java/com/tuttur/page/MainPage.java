package com.tuttur.page;


import com.tuttur.configs.PropertiesFile;

import com.tuttur.util.BasePageUtil;
import com.tuttur.util.ExcelUtil;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.tuttur.constants.MainPage_Constants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class MainPage extends MainPage_Constants {

    public MainPage(WebDriver driver) throws IOException {
        super(driver);
    }

    PropertiesFile prop = new PropertiesFile(driver);
    DbQueriesPage db = new DbQueriesPage(driver);
    BasePageUtil base = new BasePageUtil(driver);
    ExcelUtil util = new ExcelUtil(driver);
    Actions action = new Actions(driver);

    public void dropdownMenu(String menu) {

        List<WebElement> dropdown = driver.findElements(DROPDOWN_MENU);

        for (WebElement element : dropdown) {

            String menuItem = element.findElement(By.tagName("a")).getText();

            if (menuItem.contains(menu)) {

                element.click();
            }
        }
    }

    public LoginPage getLoginPage() {

        clickObjectBy(BUTTON_LOGIN);

        return new LoginPage(driver);
    }

    public MainPage logout() {

        clickObjectBy(AVATAR);
        dropdownMenu("Çıkış");

        return this;
    }

    public MainPage accountUpdate() throws IOException {

        db.executeQuery(prop.getObject("accountUpdate"));

        return this;
    }

    public RegisterPage getRegisterPage() throws IOException {

        clickObjectBy(REGISTER_BUTTON);

        return new RegisterPage(driver);
    }

    public RegisterPage getRegisterPageNotUpdate () throws IOException {

        clickObjectBy(REGISTER_BUTTON);
        return new RegisterPage(driver);

    }


    public MainPage checkAccountNo( int rowNumber) throws IOException {

        assertTrue(prop.getObject("accountNumberCheck"),getElementBy(ACCOUNT_NO).getText()
                .equals(getData(rowNumber,3)));

        return this;
    }

    public MainPage checkUsernameText(int rowNumber, int cellNumber) throws IOException {

        waitForElement(driver,MIN_WAIT_4_ELEMENT,USERNAMETEXT);

        assertTrue(prop.getObject("usernameCheck"), getElementBy(USERNAMETEXT).getText()
                .equals(getData(rowNumber,cellNumber)));

        return this;
    }
    public MainPage checkUserText(String username){

        String headerUser = username;
        waitForElement(driver,OPT_WAIT_4_ELEMENT,USERNAMETEXT);

        assertTrue("x", getElementBy(USERNAMETEXT).getText()
        .equals(headerUser));

        return this;
    }
    public void isDisplayedUsername(){

        Assert.assertFalse(getElementBy(USERNAMETEXT).getText().isEmpty());
    }

    public MainPage checkRegisterLogin () throws InterruptedException, IOException {

        assertTrue(prop.getObject("unsuccessfullyLoginAfterRegister"), getElementBy(ACCOUNT_NO).getText()
                .equals(getData(5,10)));
        return this;
    }

    public NavigationPage checkSubmenuUrl() {


        List<WebElement> headerMenus = findElements(HEADER_IDDAA_BUTTON);


        int subCount = 0;
        int headerCount = 0;

        for (int headerIndex = headerCount ; headerIndex < headerMenus.size() ; headerIndex++) {

            action.moveToElement(headerMenus.get(headerIndex)).build().perform();

            List<WebElement> subMenus = findElements(IDDAA_SUBMENU);

            for (int i = subCount; i < subMenus.size();i++) {

                subMenus.get(i).click();
                WebElement element = subMenus.get(i);
                String Url = element.findElement(By.tagName("a")).getAttribute("href");
                System.out.println(Url);
            }
        }

        return new NavigationPage(driver);
    }

    public MainPage checkRedirectSocialMediaUrl() throws InterruptedException, IOException {



        scrollToElement(FACEBOOK);

        for (int i=0; i<socialMediaElements().size(); i++){

            socialMediaElements().get(i).click();

              switchToWindows();

            Assert.assertEquals(driver.getCurrentUrl(), socialMediaUrl().get(i));

            driver.close();

              switchToWindows();

        }

        return this;

     }


     public MainPage checkRedirectStoreUrl() throws IOException {

        WebElement[] market = {getElementBy(APPLE_MARKET),getElementBy(ANDROID_MARKET)};
        List<WebElement> markets = Arrays.asList(market);

        base.getSheet("NavigationUrl");
        List<String> marketsUrl = util.getRowDataAll(49,51);

        for (int i=0; i< markets.size(); i++){

            markets.get(i).click();
            switchToWindows();

            Assert.assertEquals(driver.getCurrentUrl(), marketsUrl.get(i));

            driver.close();
            switchToWindows();

        }

        return this;
     }

     private List<String> socialMediaUrl() throws IOException {

        base.getSheet("NavigationUrl");
        List<String> socialMedia = util.getRowDataAll(42,46);

        return socialMedia;
     }

     private List<WebElement> socialMediaElements(){

         WebElement[] socialMedia = {getElementBy(FACEBOOK),getElementBy(TWITTER),getElementBy(INSTAGRAM),
                 getElementBy(YOUTUBE)};
         List<WebElement> social = Arrays.asList(socialMedia);

         return social;
     }

   }
