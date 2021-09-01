package com.tuttur.test;

import com.tuttur.base.BaseTest;
import com.tuttur.configs.PropertiesFile;
import com.tuttur.constants.MainPage_Constants;
import com.tuttur.page.MainPage;
import com.tuttur.util.BasePageUtil;
import org.junit.Test;


import java.io.IOException;

public class DashboardTest extends BaseTest {

    MainPage_Constants cons = new MainPage_Constants(driver);
    PropertiesFile prop = new PropertiesFile(driver);

//
//    /**
//     * Case 1.0
//     * Banner
//     */
//
//    @Test
//    public void bannerTest() throws IOException {
//        MainPage main = new MainPage(driver);
//
//                 main.isExistBanner();
//                 main.bannerSlider()
//                .isExistOddOnBanner()
//                .isExistBulletAndArrow()
//                .addOddToBetslip();
//        // BANNER ELEMENTLERİNE İLGİLİ EVENTIDLERİN EKLENMESİ
//
//    }
//
//    /**
//     * Case 2.0
//     * Canlı oynananlar widget
//     */
//
//    @Test
//    public void liveWidgetTest() throws InterruptedException, IOException {
//        MainPage main = new MainPage(driver);
//
//                main.isExistWidget(0, "CANLI OYNANANLAR")
//                .isExistBranchInWidget(cons.LIVE_WIDGET)
//                .checkDefaultBranch(cons.LIVE_WIDGET, "FUTBOL")
//                .scrollToElement(cons.LIVE_WIDGET);
//                main.checkEventItemsInBranch(cons.LIVE_WIDGET, cons.LIVE_EVENT,
//                main.eventRowLiveItems(),0, prop.getObject("liveBulletin"))
//                .getDashboard()
//                .clickTotalOdd(cons.LIVE_MATCH_EVENT_ROW, cons.LIVE_WIDGET);
//
//    }
//
//    /**
//     * Case 3.0
//     * Yakın Zamanda Başlayacaklar
//     * @throws IOException
//     * @throws InterruptedException
//     */
//
//    @Test
//    public void newFutureWidgetTest() throws IOException, InterruptedException {
//        MainPage main = new MainPage(driver);
//
//
//                main.scrollToWidget(1)
//                .isExistWidget(1, "YAKIN ZAMANDA BAŞLAYACAKLAR")
//                .checkDefaultBranch(cons.NEAR_FUTURE_WİDGET, "FUTBOL")
//                .checkEventItemsInBranch(cons.NEAR_FUTURE_WİDGET, cons.EVENT_ROW,
//                        main.eventRowItems(),1, prop.getObject("bulletin"))
//                .getDashboard()
//                .clickTotalOdd(cons.EVENT_ROW, cons.NEAR_FUTURE_WİDGET);
//
//    }
//
//    /**
//     * Case 4.0
//     * Popüler
//     * @throws IOException
//     * @throws InterruptedException
//     */
//
//    @Test
//    public void popularWidgetTest() throws IOException, InterruptedException {
//        MainPage main = new MainPage(driver);
//
//                main.scrollToWidget(2)
//                .isExistWidget(2, "POPÜLER MAÇLAR")
//                .checkDefaultBranch(cons.POPULAR_WIDGET, "FUTBOL")
//                .checkEventItemsInBranch(cons.POPULAR_WIDGET, cons.EVENT_ROW,
//                        main.eventRowItems(),2, prop.getObject("popular"))
//                .getDashboard()
//                .clickTotalOdd(cons.EVENT_ROW, cons.POPULAR_WIDGET);
//
//
//    }

  }

