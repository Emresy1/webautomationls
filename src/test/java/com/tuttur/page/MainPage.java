package com.tuttur.page;


import com.tuttur.configs.PropertiesFile;

import com.tuttur.util.BasePageUtil;
import com.tuttur.util.ExcelUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.tuttur.constants.MainPage_Constants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


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


    public MainPage checkAccountNo(int rowNumber) throws IOException {

        assertTrue(prop.getObject("accountNumberCheck"), getElementBy(ACCOUNT_NO).getText()
                .equals(getData(rowNumber, 3)));

        return this;
    }

    public MainPage checkUsernameText(int rowNumber, int cellNumber) throws IOException {

        waitForElement(driver, MIN_WAIT_4_ELEMENT, USERNAMETEXT);

        assertTrue(prop.getObject("usernameCheck"), getElementBy(USERNAMETEXT).getText()
                .equals(getData(rowNumber, cellNumber)));

        return this;
    }

    public MainPage checkUserText(String username) {

        String headerUser = username;
        waitForElement(driver, OPT_WAIT_4_ELEMENT, USERNAMETEXT);

        assertTrue("x", getElementBy(USERNAMETEXT).getText()
                .equals(headerUser));

        return this;
    }

    public void isDisplayedUsername() {

        Assert.assertFalse(getElementBy(USERNAMETEXT).getText().isEmpty());
    }

    public MainPage checkRegisterLogin() throws InterruptedException, IOException {

        assertTrue(prop.getObject("unsuccessfullyLoginAfterRegister"), getElementBy(ACCOUNT_NO).getText()
                .equals(getData(5, 10)));
        return this;
    }

    public NavigationPage checkSubmenuUrl() {


        List<WebElement> headerMenus = findElements(HEADER_IDDAA_BUTTON);


        int subCount = 0;
        int headerCount = 0;

        for (int headerIndex = headerCount; headerIndex < headerMenus.size(); headerIndex++) {

            action.moveToElement(headerMenus.get(headerIndex)).build().perform();

            List<WebElement> subMenus = findElements(IDDAA_SUBMENU);

            for (int i = subCount; i < subMenus.size(); i++) {

                subMenus.get(i).click();
                WebElement element = subMenus.get(i);
                String Url = element.findElement(By.tagName("a")).getAttribute("href");
                System.out.println(Url);
            }
        }

        return new NavigationPage(driver);
    }

    public MainPage checkRedirectSocialMediaUrl() throws InterruptedException, IOException {


        sleep(4);
        scrollToElement(SOCIAL_FOOTER);

        for (int i = 0; i < socialMediaElements().size(); i++) {


            socialMediaElements().get(i).click();

            switchToWindows();

            Assert.assertTrue(driver.getCurrentUrl().contains(socialMediaUrl().get(i)));
            //Assert.assertEquals(driver.getCurrentUrl(), socialMediaUrl().get(i));

            driver.close();

            switchToWindows();

        }

        return this;

    }


    public MainPage checkRedirectStoreUrl() throws IOException, InterruptedException {

        sleep(3);
        scrollToElement(APPLE_MARKET);

        WebElement[] market = {getElementBy(APPLE_MARKET), getElementBy(GALAXY_STORE)
                , getElementBy(HUAWEI_STORE)};
        List<WebElement> markets = Arrays.asList(market);

        base.getSheet("NavigationUrl");
        List<String> marketsUrl = util.getRowDataAll(55, 58);

        for (int i = 0; i < markets.size(); i++) {

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
        List<String> socialMedia = util.getRowDataAll(48, 52);

        return socialMedia;
    }

    private List<WebElement> socialMediaElements() {

        WebElement[] socialMedia = {getElementBy(FACEBOOK), getElementBy(TWITTER), getElementBy(INSTAGRAM),
                getElementBy(YOUTUBE)};
        List<WebElement> social = Arrays.asList(socialMedia);

        return social;
    }

    public boolean isExistBanner() {

        return isExist(MIN_WAIT_4_ELEMENT, BANNER);
    }

    private List<WebElement> banners() {

        return driver.findElements(BANNER);
    }

    private List<WebElement> dynamicBanners() {

        return driver.findElements(ODD_ON_BANNER);
    }

    public MainPage bannerSlider() {

        int bannerCount = 1;

        if (banners().size() > bannerCount) {
            for (int i = 0; i < banners().size(); i++) {

                getElementBy(BANNER_NEXT).isDisplayed();
                waitForElement(driver, MIN_WAIT_4_ELEMENT, BANNER_NORMAL);

            }
        }
        return this;
    }

    public MainPage isExistOddOnBanner() {

        int count = 0;

        for (int bannerIndex = count; bannerIndex < banners().size(); bannerIndex++) {

            if (banners().get(bannerIndex).findElements(ODD_ON_BANNER).size() != 0) {

                banners().get(bannerIndex).findElement(TOTAL_ODDS).isDisplayed();

                for (int oddIndex = 0; oddIndex < dynamicBanners().size(); oddIndex++) {

                    dynamicBanners().get(oddIndex).isDisplayed();
                }
            }
        }
        return this;
    }

    public MainPage isExistBulletAndArrow() {

        int arrowCount = 2;
        int bannerCount = 0;

        if (driver.findElements(BANNER).size() != bannerCount) {


            assertTrue(driver.findElements(ARROWS).size() == arrowCount);
            isExist(MIN_WAIT_4_ELEMENT, SLIDER_BULLET);

        }

        return this;
    }


    public MainPage addOddToBetslip() {

        int xOffset = 1268;
        int yOffset = 410;


        for (WebElement dynamicBanner : dynamicBanners()) {

            List<WebElement> activeOdds = dynamicBanner.findElements(ODD_OUTCOME_BANNER);

            activeOdds.stream().filter(odd -> !odd.getAttribute("class").contains("eventOdd--locked"));

            if (activeOdds.size() > 0) {

                action.moveByOffset(xOffset, yOffset).perform();
                activeOdds.get(0).click();

            }
        }

        String oddd = getElementBy(SELECTED_ODD).getText().substring(0, 7).trim();
        String of = getElementBy(EVENT_CONTENT_INFO).getText().substring(0, 7).trim();
         String oddMarketName = getElementBy(SELECTED_ODD).getText();
         String betslipMarketName = getElementBy(EVENT_CONTENT_INFO).getText().substring(0,7).trim();


         Assert.assertEquals(oddMarketName.substring(0,7).trim(),betslipMarketName);
         Assert.assertEquals(oddMarketName.substring(8,13).trim(),getElementBy(BETSLIP_OUTCOME).getText().trim());

        return this;
     }

     public MainPage isExistWidget(int index, String widgetText){

        Assert.assertEquals(getElemenstBy(WIDGET_HEADER,index).getText(),widgetText);

        return this;
     }

     public List<WebElement> branchListInWidget(By widgetName){

       return driver.findElements(widgetName).get(0).findElements(WIDGET_BRANCH);

     }
     public MainPage isExistBranchInWidget(By widgetName){

        assertTrue(branchListInWidget(widgetName).size() != 0);

        return this;

     }


     public MainPage checkDefaultBranch(By widgetName ,String branch){

        Assert.assertEquals(branchListInWidget(widgetName).get(0).getText(),branch);

        return this;
    }

     public List<By> eventRowLiveItems(){

        List<By> items = new ArrayList<>();
        items.add(LEAGUE_FLAG);
        items.add(LEAGUE_CODE);
        items.add(STATUS_PLAYING);
        items.add(MBC);
        items.add(TEAMS);
        items.add(LIVE_SCORE);
        items.add(LIVE_ICON);
        items.add(LIVE_ODD);
        items.add(EVENT_TOTAL_ODD);

        return items;
     }

     public List<By> eventRowItems () {

         List<By> items = new ArrayList<>();
         items.add(LEAGUE_FLAG);
         items.add(LEAGUE_CODE);
         items.add(EVENT_TIME);
         items.add(MBC);
        // items.add(TEAMS);
       //  items.add(LIVE_ODD);
        // items.add(EVENT_TOTAL_ODD);
         return items;
     }

     public MainPage checkEventItemsInBranch(By widgetName, By events , List<By> items) throws InterruptedException {



         int count = 0;

         for (int index = count; index < branchListInWidget(widgetName).size()-1;) {

             switch (branchListInWidget(widgetName).get(index).getText().toUpperCase(Locale.ROOT)) {

                 case "FUTBOL":
                 case "HENTBOL":

                     checkItemsOnEventRow(widgetName,events,items);

                     checkStatusName("Y","'",":");

                     break;
                 case "BASKETBOL":

                     checkItemsOnEventRow(widgetName,events,items);

                     checkStatusName("P","UZ",":");

                     break;
                 case "TENIS":
                 case "VOLEYBOL":
                 case "MASA TENISI":

                     checkItemsOnEventRow(widgetName,events,items);

                     checkStatusName("S","UZ",":");

                     break;

             }

             if (index < branchListInWidget(widgetName).size()-1){

                 index++;
                 branchListInWidget(widgetName).get(index).click();

             }
        }
        return this;
     }


    public EventDetailPage clickTotalOdd(){

        List<WebElement> eventRow = driver.findElements(LIVE_MATCH_EVENT_ROW);

        int count =0;
        for (int i=count; i < eventRow.size(); i++){

            if (!eventRow.get(count)
                    .findElements(EVENT_TOTAL_ODD).get(0).getAttribute("class").contains("disabled")){

                String eventName = eventRow.get(count).findElement(TEAMS).getText();

                eventRow.get(count).findElement(EVENT_TOTAL_ODD).click();

                waitForElement(driver, DEFAULT_WAIT_4_ELEMENT, EVENT_DETAIL_TEAMS);

                Assert.assertEquals(eventName, getElementBy(EVENT_DETAIL_TEAMS).getText());
            }

            count++;
        }
        if (getElementBy(LIVE_WIDGET).isDisplayed()){
            int index = 0;

            String eventName = eventRow.get(index).findElement(TEAMS).getText();

            eventRow.get(index).findElements(TEAMS).get(index).click();

            waitForElement(driver, DEFAULT_WAIT_4_ELEMENT, EVENT_DETAIL_TEAMS);

            Assert.assertEquals(eventName, getElementBy(EVENT_DETAIL_TEAMS).getText());
        }

        return new EventDetailPage(driver);
    }



    private void checkItemsOnEventRow( By widgetName , By events,List<By> items) {

        List<WebElement> eventRowListt = driver.findElements(widgetName).get(0).findElements(events);

        for (WebElement eventRow : eventRowListt) {

            for (int i = 0; i < items.size(); i++) {

                Assert.assertFalse(eventRow.findElements(items.get(i)).equals(null));
            }
        }
    }

    public MainPage getFutbolBranch(By widgetName){

        branchListInWidget(widgetName).get(0).click();

        return this;
    }

    private void checkStatusName(String status1, String status2, String status3){

        String statusName = getElementBy(eventRowItems().get(2)).getText();

        assertTrue(statusName.contains(status1) || statusName.contains(status2) ||
                statusName.contains(status3));
    }


     public MainPage checkTheyWillStartSoonWidget () throws InterruptedException {



        scrollToElement(WIDGET_TEXT);
        isExistWidget(1,"YAKIN ZAMANDA BAŞLAYACAKLAR");
        checkDefaultBranch(NEAR_FUTURE_WİDGET,"YAKIN ZAMANDA BAŞLAYACAKLAR");




       return this;
     }




}
