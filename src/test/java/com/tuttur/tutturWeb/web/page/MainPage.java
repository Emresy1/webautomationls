package com.tuttur.tutturWeb.web.page;



import com.tuttur.configs.PropertiesFile;
import com.tuttur.tutturWeb.web.constants.RegisterPage_Constants;
import com.tuttur.util.BasePageUtil;
import com.tuttur.util.ExcelUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import com.tuttur.tutturWeb.web.constants.MainPage_Constants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;



public class MainPage extends MainPage_Constants {


    public MainPage(WebDriver driver) throws IOException {
        super(driver);

    }




    PropertiesFile prop = new PropertiesFile(driver);
    DbQueriesPage db = new DbQueriesPage(driver);
    BasePageUtil base = new BasePageUtil(driver);
    ExcelUtil util = new ExcelUtil(driver);
    Actions action = new Actions(driver);
    JavascriptExecutor js = (JavascriptExecutor) driver;
    GeneralPage general = new GeneralPage(driver);

    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    boolean contains = false;

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

    public MainPage logout() throws InterruptedException {

        scrollToElement(AVATAR);
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

    public RegisterPage getRegisterPageNotUpdate() throws IOException {

        clickObjectBy(REGISTER_BUTTON);
        return new RegisterPage(driver);

    }


    public MainPage checkAccountNo(int rowNumber) throws IOException {

        assertTrue(getElementBy(ACCOUNT_NO).getText()
                        .equals(getData(rowNumber, 3)), prop.getObject("accountNumberCheck"));

        return this;
    }

    public MainPage checkUsernameText(int rowNumber, int cellNumber) throws IOException {


        waitForElement(driver, MIN_WAIT_4_ELEMENT, USERNAMETEXT);

        assertTrue(getElementBy(USERNAMETEXT).getText()
                .equals(getData(rowNumber, cellNumber)),prop.getObject("usernameCheck"));


        return this;
    }

    public MainPage checkUserText(String username) {

        String headerUser = username;
        waitForElement(driver, OPT_WAIT_4_ELEMENT, USERNAMETEXT);

        assertTrue(getElementBy(USERNAMETEXT).getText()
                        .equals(headerUser), "x");

        return this;
    }

    public void isDisplayedUsername() {

        Assert.assertFalse(getElementBy(USERNAMETEXT).getText().isEmpty());
    }

    public MainPage checkRegisterLogin() throws InterruptedException, IOException {

        assertTrue(getElementBy(ACCOUNT_NO).getText()
                        .equals(getData(5, 10)),
                prop.getObject("unsuccessfullyLoginAfterRegister"));
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


        waitForPageLoad();
        js.executeScript("window.scrollBy(0,2000)");

        clickObjectBy(new RegisterPage_Constants(driver).BUTTON_CLOSE_COOKİE_BAR);


        for (int i = 0; i < socialMediaElements().size(); i++) {


            socialMediaElements().get(i).click();

            switchToWindows();

            driver.getCurrentUrl().contains(socialMediaUrl().get(i));


            driver.close();

            switchToWindows();

        }

        return this;

    }


    public MainPage checkRedirectStoreUrl() throws IOException {

        waitForPageLoad();
        js.executeScript("window.scrollBy(0,2000)");

        clickObjectBy(new RegisterPage_Constants(driver).BUTTON_CLOSE_COOKİE_BAR);

        WebElement[] market = {getElementBy(APPLE_MARKET),
                getElementBy(HUAWEI_MARKET),
                getElementBy(GALAXY_STORE)};
        List<WebElement> markets = Arrays.asList(market);

        base.getSheet("NavigationUrl");
        List<String> marketsUrl = util.getRowDataAll(56, 59);

        for (int i = 0; i < markets.size(); i++) {


            markets.get(i).click();
            switchToWindows();

            Assert.assertEquals(driver.getCurrentUrl(), marketsUrl.get(i));

            driver.close();
            switchToWindows();

        }

        return this;
    }

    public void downloadApk() {

        List<WebElement> store = driver.findElements(ANDROID_MARKET);

        WebElement storeElement = store.get(store.size() - 1);

        storeElement.click();
        sleep(5);
    }

    public void checkDownloadedApk() {

        File folder = new File(System.getProperty("user.home") + "/Downloads/");

        File[] listOfFiles = folder.listFiles();

        boolean found = false;
        File file = null;

        for (File listOfFile : listOfFiles) {

            if (listOfFile.isFile()) {

                String fileName = listOfFile.getName();
                System.out.println("File " + listOfFile.getName());

                if (fileName.matches("tutturcom-v3-00.apk")) {

                    file = new File(fileName);
                    found = true;

                    break;
                }
            }
        }

        Assert.assertTrue(found, "Apk bulunamadı");
        file.deleteOnExit();
    }

    private List<String> socialMediaUrl() throws IOException {

        base.getSheet("NavigationUrl");
        List<String> socialMedia = util.getRowDataAll(48, 52);

        return socialMedia;
    }

    private List<WebElement> socialMediaElements() {

        WebElement[] socialMedia = {getElementBy(FACEBOOK),
                getElementBy(TWITTER),
                getElementBy(INSTAGRAM),
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

            List<WebElement> activeOdd = activeOdds.stream()
                    .filter(odd -> !odd.getAttribute("class").contains("eventOdd--locked"))
                    .collect(Collectors.toList());
            System.out.println(activeOdd.size() + "000");

            if (activeOdd.size() > 0) {

                action.moveByOffset(xOffset, yOffset).perform();
                activeOdds.get(0).click();

                String oddMarketName = getElementBy(SELECTED_ODD).getText();
                String betslipMarketName = getElementBy(EVENT_CONTENT_INFO).getText().substring(0, 7).trim();

                boolean isEquals = oddMarketName
                        .substring(0, 7).trim().equals(betslipMarketName) &&
                        oddMarketName.substring(8, 13)
                                .trim().equals(getElementBy(BETSLIP_OUTCOME).getText().trim());

                assertTrue(isEquals);

            }
        }

        //String oddd = getElementBy(SELECTED_ODD).getText().substring(0, 7).trim();
        //String of = getElementBy(EVENT_CONTENT_INFO).getText().substring(0, 7).trim();


        return this;
    }

    public MainPage scrollToWidget(int index) throws InterruptedException {

        sleep(1);
        scrollToElements(By.className("widget-content"), index);

        return this;
    }

    public MainPage isExistWidget(int index, String widgetText) {

        Assert.assertEquals(getElemenstBy(WIDGET_HEADER, index).getText(), widgetText);

        return this;
    }

    public List<WebElement> branchListInWidget(By widgetName) {

        return driver.findElements(widgetName).get(0).findElements(WIDGET_BRANCH);

    }

    public MainPage isExistBranchInWidget(By widgetName) {

        assertTrue(branchListInWidget(widgetName).size() != 0);

        return this;

    }


    public MainPage checkDefaultBranch(By widgetName) {

        waitForElement(driver, MAX_WAIT_4_ELEMENT, widgetName);

        String[] branches = {"FUTBOL",
                "BASKETBOL",
                "TENİS",
                "MASA TENİSİ",
                "VOLEYBOL",
                "BUZ HOKEYİ",
                "HENTBOL",
                "SNOOKER",
                "MOTOR SPORLARI",
                "UZUN VADELİ"};

        String activeTab = getElementBy(WIDGET_ACTIVE_TAB).getText();

        if (getElementBy(TAB_VIEW_LABELS).getText().contains(branches[0])) {

            assertTrue(activeTab.equals(branches[0]));
        } else if (!getElementBy(TAB_VIEW_LABELS).getText().contains(branches[0])) {

            assertTrue(activeTab.equals(branches[1]));
        } else if (!getElementBy(TAB_VIEW_LABELS).getText().contains(branches[1])) {

            assertTrue(activeTab.equals(branches[2]));
        } else if (!getElementBy(TAB_VIEW_LABELS).getText().contains(branches[2])) {

            assertTrue(activeTab.equals(branches[3]));
        }

        return this;
    }

    public List<By> eventRowLiveItems() {

        List<By> items = new ArrayList<>();
        items.add(LEAGUE_FLAG);
        items.add(LEAGUE_CODE);
        items.add(EVENT_TIME);
        items.add(MBC);
        items.add(TEAMS);
        items.add(LIVE_SCORE);
        items.add(LIVE_ICON);
        items.add(LIVE_ODD);
        items.add(EVENT_TOTAL_ODD);

        return items;
    }

    public List<By> eventRowItems() {

        List<By> items = new ArrayList<>();
        items.add(LEAGUE_FLAG);
        items.add(LEAGUE_CODE);
        items.add(EVENT_TIME);
        items.add(MBC);
        items.add(TEAMS);
        items.add(LIVE_ODD);
        items.add(EVENT_TOTAL_ODD);
        return items;

    }

    public MainPage checkEventItemsInBranch(By widgetName, By events, List<By> items, int i, String url)
            throws InterruptedException {


        int branchCount = 1;
        int count = 0;

        if (branchListInWidget(widgetName).size() > branchCount) {

            for (int index = count; index < branchListInWidget(widgetName).size() - 1; ) {

                switch (branchListInWidget(widgetName).get(index).getText().toUpperCase(Locale.ROOT)) {

                    case "FUTBOL":
                    case "HENTBOL":

                        checkItemsOnEventRow(widgetName, events, items);

                        checkStatusName(widgetName);

                        break;
                    case "BASKETBOL":

                        checkItemsOnEventRow(widgetName, events, items);

                        checkStatusName(widgetName);

                        break;
                    case "TENIS":
                    case "VOLEYBOL":
                    case "MASA TENISI":

                        checkItemsOnEventRow(widgetName, events, items);

                        checkStatusName(widgetName);

                        break;

                }

                if (index < branchListInWidget(widgetName).size() - 1) {

                    index++;
                    waitForElement(branchListInWidget(widgetName).get(index), MIN_WAIT_4_ELEMENT);
                    branchListInWidget(widgetName).get(index).click();
                }
            }
            checkRedirectToScreen(i, url);
        } else {

            checkItemsOnEventRow(widgetName, events, items);
            checkStatusName(widgetName);


            checkRedirectToScreen(i, url);
        }


        return this;
    }

    private void clickButtonShowAll(int index) throws InterruptedException {
        scrollToElements(WIDGET_SHOW_ALL, index);
        clickObjectsBy(WIDGET_SHOW_ALL, index);
    }


    private void checkRedirectToScreen(int index, String url) throws InterruptedException {

        clickButtonShowAll(index);
        assertTrue(driver.getCurrentUrl().contains(url));

    }

    public MainPage getDashboard() throws IOException {

        driver.get(prop.getObject("dashboard"));

        return this;
    }

    public EventDetailPage clickTotalOdd(By eventRows, By widgetName) throws InterruptedException {

        List<WebElement> eventRow = driver.findElements(eventRows);

        int count = 0;
        for (int i = count; i < eventRow.size(); i++) {

            if (!eventRow.get(count)
                    .findElements(EVENT_TOTAL_ODD).get(0).getAttribute("class").contains("disabled")) {

                String eventName = eventRow.get(count).findElement(TEAMS).getText();

                scrollToWidget(1);
                eventRow.get(count).findElement(EVENT_TOTAL_ODD).click();

                waitForElement(driver, DEFAULT_WAIT_4_ELEMENT, EVENT_DETAIL_TEAMS);

                Assert.assertEquals(eventName, getElementBy(EVENT_DETAIL_TEAMS).getText());
            }

            count++;
            break;
        }
        if (getElementBy(widgetName).isDisplayed()) {
            int index = 0;

            String eventName = eventRow.get(index).findElement(TEAMS).getText();

            eventRow.get(index).findElements(TEAMS).get(index).click();

            waitForElement(driver, DEFAULT_WAIT_4_ELEMENT, EVENT_DETAIL_TEAMS);

            Assert.assertEquals(eventName, getElementBy(EVENT_DETAIL_TEAMS).getText());
        }

        return new EventDetailPage(driver);
    }


    private void checkItemsOnEventRow(By widgetName, By events, List<By> items) {

        List<WebElement> eventRowListt = driver.findElements(widgetName).get(0).findElements(events);

        for (WebElement eventRow : eventRowListt) {

            for (int i = 0; i < items.size(); i++) {

                //     eventRow.findElement(items.get(i)).isDisplayed();
                waitForElement(eventRow.findElement(items.get(i)), 3);

            }
        }
    }

    public MainPage getFirstBranch(By widgetName) {

        branchListInWidget(widgetName).get(0).click();

        return this;
    }


    private void checkStatusName(By widgetName) {

        List<WebElement> widget = findElements(WIDGET_HEADER);
        WebElement activeTab = findElements(widgetName).get(0).findElement(WIDGET_ACTIVE_TAB);
        List<WebElement> statusName = findElements(EVENT_TIME);


        if (getElementBy(widgetName).getText().contains("CANLI OYNANANLAR")) {

            switch (activeTab.getText()) {

                case "FUTBOL":
                case "HENTBOL":

                    for (String status : statusNameFutbol()) {

                        if (getElementBy(widgetName).findElements(EVENT_TIME).get(0).getText().contains(status)) {

                            contains = true;
                            break;
                        }
                    }

                    break;
                case "BASKETBOL":

                    for (String status : statusNameBasketbol()) {

                        if (getElementBy(widgetName).findElements(EVENT_TIME).get(0).getText().contains(status)) {

                            contains = true;
                            break;
                        }
                    }
                    break;

                case "TENIS":
                case "VOLEYBOL":
                case "MASA TENISI":

                    for (String status : statusNameTenis()) {

                        if (getElementBy(widgetName).findElements(EVENT_TIME).get(0).getText().contains(status)) {

                            contains = true;
                            break;
                        }
                    }
                    break;

            }
        } else if (getElementBy(widgetName).getText().contains("YAKIN ZAMANDA BAŞLAYACAKLAR")) {

            checkStatus(NEAR_FUTURE_WİDGET, statusNameSoonTime());

        } else if (getElementBy(widgetName).getText().contains("POPÜLER MAÇLAR")) {

            checkStatus(POPULAR_WIDGET, statusNamePopular());

        }
    }

    private void checkStatus(By widgetName, String[] statusList) {

        int count = 0;
        for (int i = count; i < getElementBy(widgetName).findElements(EVENT_ROW).size(); i++) {

            for (String status : statusList) {

                if (getElementBy(widgetName).findElements(EVENT_TIME).get(i).getText().contains(status)) {

                    contains = true;

                }
            }
        }
    }


    private String[] statusNameFutbol() {

        String status[] = {"Y", "'", ":", "DA", "UZ"};
        return status;
    }

    private String[] statusNameBasketbol() {

        String status[] = {"P", "UZ", ":", "DA"};
        return status;
    }

    private String[] statusNameTenis() {

        String status[] = {"S", "UZ", ":", "DA"};
        return status;
    }

    private String[] statusNameSoonTime() {

        String status[] = {"DK", "SA", "GÜN"};
        return status;
    }

    private String[] statusNamePopular() {

        String status[] = {"Bgn", "Yrn", ":"};
        return status;
    }


    public MainPage checkTheyWillStartSoonWidget() throws InterruptedException {


        scrollToElement(WIDGET_TEXT);
        isExistWidget(1, "YAKIN ZAMANDA BAŞLAYACAKLAR");
        //   checkDefaultBranch(NEAR_FUTURE_WİDGET, "YAKIN ZAMANDA BAŞLAYACAKLAR");

        return this;
    }

    public CampaignsPage getCampaignsPage() throws IOException {

        try {

            waitForElementDisappear(getElementBy(MODAL_CONTAINER));
        } catch (Exception exp) {

            System.out.println(prop.getObject("contractMessage"));
        } finally {

            clickObjectBy(CAMPAIGNS_ICON);
        }


        return new CampaignsPage(driver);

    }

    public MainPage betSlipEmptyStateItemsControl() throws IOException, InterruptedException {


        boolean isTrue = getElementBy(MYCOUPON_BETSLIP).getText().equals("Kuponum") &&
                getElementBy(BETSLIP_EMPTY_ICON).isDisplayed() &&
                getElementBy(BETSLIP_EMPTY_STATE_TITLE).getText().equals(prop.getObject("emptyStateTitle")) &&
                getElementBy(BETSLIP_EMPTY_STATE_DESC).getText().equals(prop.getObject("emptyStateDesc"));

        assertTrue(isTrue);
        isDisabledContinuingTab();

        return this;

    }

    public void liveMatchesRedirectControl() throws InterruptedException {

        int liveBetCount = Integer.parseInt(getElementBy(LIVEBET_SHORTCUT).getText());

        if (liveBetCount > 0) {

            scrollToElement(LIVEBET_BTN_BETSLIP);
            clickObjectBy(LIVEBET_BTN_BETSLIP);
            waitForElement(driver, MIN_WAIT_4_ELEMENT, BULLETIN_FAV_TAB);
            assertTrue(driver.getCurrentUrl().contains("canli"));

        } else {

            clickObjectBy(LIVEBET_BTN_BETSLIP);
            assertTrue(driver.getCurrentUrl().contains("bulten"));

        }

    }

    public boolean isDisabledContinuingTab() {

        return !getElementBy(CONTINUI_BETSLIP).isEnabled();

    }

    public MainPage addEventToBetSlip(String mbsInfo) throws InterruptedException {

        scrollToWidget(2);
        clickObjectBy(new RegisterPage_Constants(driver).BUTTON_CLOSE_COOKİE_BAR);

        List<WebElement> eventRows = driver.findElement(POPULAR_WIDGET).findElements(EVENT_ROW);

        List<WebElement> mbs = eventRows.stream()
                .filter(eventRow -> eventRow.findElement(SPORTSBOOK_EVENT_ROW_HEADER)
                        .findElement(SPORTSBOOK_EVENT_ROW_INFO)
                        .findElement(MBC_VALUE)
                        .getText().contains(mbsInfo))
                .collect(Collectors.toList());

        int randomIndex = generateRandomInt(mbs.size());


        List<WebElement> actives = mbs.get(randomIndex).findElements(By.className("eventOdd"));


        List<WebElement> outcome = actives.stream()
                .filter(activeOdd -> !activeOdd.getAttribute("class").contains("eventOdd--locked"))
                .collect(Collectors.toList());


        int size = 0;
        if (mbs.size() > size && mbsInfo.equals("1")) {

            outcome.get(0).click();


            WebElement selectedEvent = mbs.get(randomIndex);

            general.eventDate = selectedEvent.findElement(WIDGET_DATE).getText();
            general.eventTime = selectedEvent.findElement(WIDGET_TIME).getText();
            general.outcome = selectedEvent.findElements(OUTCOME).get(size).getText();

            String homeTeam = selectedEvent.findElements(EVENT_TEAMS_NAME).get(0).getText();
            String awayTeam = selectedEvent.findElements(EVENT_TEAMS_NAME).get(1).getText();

            general.eventName = homeTeam + " " + awayTeam;
            general.eventMarketName = selectedEvent.findElement(EVENT_ODD_NAME).getText();


        }

        return this;

    }

    public MainPage checkEventItemsOnBetslip() {


        general.eventCount = getElementBy(EVENT_COUNT_ON_BETSLIP).getText();
        general.totalRatio = totalRatio();
        general.totalAmount = totalAmount();
        general.maxWinAmount = maxWinAmount();
        general.betslipDate = eventDate()[0] + "," + eventDate()[1];


        boolean isEquals = general.eventCount.equals("1")
                && eventDate()[0].contains(general.eventDate)
                && eventDate()[1].contains(general.eventTime)
                && outCome().equals(general.outcome)
                && eventName().equals(general.eventName)
                && eventMarketName().equals(general.eventMarketName);

        assertTrue(isEquals);


        return this;
    }

    public MainPage playCoupon() {

        clickObjectBy(BUTTON_PLAY);

        assertTrue(getElementBy(PLAY_COUPON_INFO).getText().equals("KUPON OYNANIYOR..."));

        waitForElement(driver, MAX_WAIT_4_ELEMENT, BETSLIP_STATE_TITLE);

        String betslipTitle = getElementBy(BETSLIP_STATE).findElement(BETSLIP_STATE_TITLE).getText();

        if (betslipTitle.equals("Kuponun KRALLAR gibi oynandı!")) {

            boolean isEquals = keepMyChoicesButton().getText().equals("SEÇİMLERİMİ SAKLA") &&
                    returnToBulletinButton().getText().equals("BÜLTENE DÖN") &&
                    myCouponButton().getText().equals("KUPONLARIM");

            assertTrue(isEquals);

            clickObjectBy(BUTTON_CLOSE_MODAL);

        } else if (betslipTitle.equals("Oran değişikliği mevcut")) {

              Assert.assertFalse(getElementBy(CHANGE_OUTCOME_TEXT).getText().isEmpty());
              Assert.assertEquals(acceptAndPlayButton().getText(),"KABUL ET VE OYNA");
              Assert.assertEquals(returnToCouponButton().getText(),"KUPONA DÖN");


        } else {
            // error gelecek
        }

        return this;
    }

    public MainPage continuingTabControl() {

        driver.navigate().refresh();
        waitForElementClickable(driver, OPT_WAIT_4_ELEMENT, BTN_LIVE_MATCH_BETSLIP);

        getContinuingTab();


        boolean isEquals = eventCountOnCouponCard().trim().equals(general.eventCount.trim())
                && currentDate().equals(dateOnCouponCard())
                && getTotalRatioOnCouponCard() == (general.totalRatio)
                && getTotalAmountOnCouponCard() == (general.totalAmount);

        assertTrue(isEquals);

        return this;
    }

    public MainPage continuingTabCouponDetails() {


        getCouponDetail();

        waitForElement(driver, OPT_WAIT_4_ELEMENT, PLAY_AGAIN_BTN);

        String[] couponTeamName = getElementBy(COUPON_TEAMS_NAME).getText().split("-");

        double outcome = Double.parseDouble(general.outcome);

        boolean isTrue = getMaxWinAmountInCouponDetail() == general.maxWinAmount &&
                general.eventName.contains(couponTeamName[1]) &&
                getOutcomeInCoupon() == outcome &&
                couponMarketName().equals(general.eventMarketName) &&
                getElementBy(COUPON_DETAIL_DATE).getText().equals(general.betslipDate);

        assertTrue(isTrue);

        return this;


    }

    public WebElement acceptAndPlayButton () {

        return getElementBy(ACCEPT_AND_PLAY_BTN);

    }

    public WebElement returnToCouponButton () {

        return getElementBy(RETURN_TO_COUPON_BTN);

    }

    public WebElement keepMyChoicesButton() {

        return getElementBy(KEEP_MY_CHOICES);
    }

    public WebElement returnToBulletinButton() {

        return getElemenstBy(RETURN_TO_BULLETIN, 0);
    }

    public WebElement myCouponButton() {

        return getElemenstBy(MY_COUPON_BTN, 1);
    }

    private double getOutcomeInCoupon() {

        String couponOutcomeText = getElementBy(By.className("iddaaCouponEventRow-selectedOutcome")).getText();
        double couponOutcome = Double.parseDouble(couponOutcomeText);

        return couponOutcome;
    }

    private String couponMarketName() {

        return getElementBy(COUPON_MARKET_TEXT).getText() + " " + getElementBy(COUPON_ODD_TEXT).getText();
    }

    private double getMaxWinAmountInCouponDetail() {

        String[] maxAmountText = getElementBy(COUPON_MAX_AMOUNT).getText()
                .replace("\n", " ")
                .replace(",", ".")
                .split(" ");
        double maxAmount = Double.parseDouble(maxAmountText[2]);
        return maxAmount;
    }

    private void getCouponDetail() {

        couponCards().get(0).findElement(BETSLIP_COUPON_DETAIL_BTN).click();

    }


    private void getContinuingTab() {

        WebElement ct = getElemenstBy(BETSLIP_TAB, 1);

        action.click(ct).build().perform();
    }

    private String getInfoOnCouponCard(int index) {

        return couponCards().get(0).findElements(COUPON_CARD_INFO).get(index).getText();
    }

    private List<WebElement> couponCards() {

        return driver.findElements(COUPON_CARD);
    }

    private int getTotalAmountOnCouponCard() {

        String totalAmount[] = getInfoOnCouponCard(2).split(",");

        int amount = Integer.parseInt(totalAmount[0]);

        return amount;
    }

    private double getTotalRatioOnCouponCard() {

        String totalRatio = getInfoOnCouponCard(1).replace(",", ".");
        double ratio = Double.parseDouble(totalRatio);

        return ratio;
    }

    private Double maxWinAmount() {

        String gainText = getElementBy(MAX_WIN_AMOUNT).getText()
                .replace(",", ".")
                .replace("TL", "");
        double totalGain = Double.parseDouble(gainText);

        return totalGain;
    }

    private int totalAmount() {

        String amountText[] = getElemenstBy(BETSLIP_VALUE, 1).getText()
                .split(",");

        return Integer.parseInt(amountText[0]);
    }

    private double totalRatio() {

        String ratioText = getElemenstBy(BETSLIP_VALUE, 0).getText().replace(",", ".");

        return Double.parseDouble(ratioText);

    }

    private String dateOnCouponCard() {

        return couponCards().get(0).findElements(COUPON_CARD_INFO).get(0).getText();

    }

    private String eventCountOnCouponCard() {

        return getElementBy(EVENT_COUNT_ON_CARD).getText().replace("Maç", "");
    }

    private String currentDate() {

        return formatter.format(date).replace("-", ".");
    }

    private String outCome() {

        return elementText(BETSLIP_EVENT_ROW, BETSLIP_EVENT_OUTCOME);
    }

    private String[] eventDate() {

        String date = elementText(BETSLIP_EVENT_ROW, BETSLIP_EVENT_DATE);

        String dateArray[] = date.split(",");
        return dateArray;

    }

    private String eventMarketName() {

        return elementText(BETSLIP_EVENT_ROW, BETSLIP_MARKET_TEXT) + " " +
                elementText(BETSLIP_EVENT_ROW, BETSLIP_ODD_TEXT);

    }

    private String eventName() {

        return elementText(BETSLIP_EVENT_ROW, BETSLIP_HOME_TEAM) + " " +
                elementText(BETSLIP_EVENT_ROW, BETSLIP_AWAY_TEAM);

    }


}
