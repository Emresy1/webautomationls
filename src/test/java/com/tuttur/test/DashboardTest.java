package com.tuttur.test;

import com.tuttur.base.BaseTest;
import com.tuttur.constants.MainPage_Constants;
import com.tuttur.page.EventDetailPage;
import com.tuttur.page.MainPage;
import org.junit.Test;


import java.io.IOException;

public class DashboardTest extends BaseTest {

    MainPage_Constants cons = new MainPage_Constants(driver);


    /**
     * Case 1.0
     * Banner
     */

    @Test
    public void bannerTest() throws IOException {

        new MainPage(driver).isExistBanner();
        new MainPage(driver).bannerSlider()
                .isExistOddOnBanner()
                .isExistBulletAndArrow()
                .addOddToBetslip();
        // BANNER ELEMENTLERİNE İLGİLİ EVENTIDLERİN EKLENMESİ

    }

    /**
     * Case 2.0
     * Canlı oynananlar widget
     */

    @Test
    public void liveWidgetTest() throws InterruptedException, IOException {

        new MainPage(driver).isExistWidget(0, "CANLI OYNANANLAR")
                .isExistBranchInWidget(cons.LIVE_WIDGET)
                .checkDefaultBranch(cons.LIVE_WIDGET, "FUTBOL")
                .scrollToElement(cons.LIVE_WIDGET);
        new MainPage(driver).checkEventItemsInBranch(cons.LIVE_WIDGET, cons.LIVE_EVENT, new MainPage(driver).eventRowLiveItems(),0)
                .getFutbolBranch(cons.LIVE_WIDGET)
                .clickTotalOdd(cons.LIVE_MATCH_EVENT_ROW, cons.LIVE_WIDGET);

    }

    @Test
    public void soonStartWidget() throws IOException, InterruptedException {


        new MainPage(driver).scrollToWidget(cons.NEAR_FUTURE_WİDGET)
                .isExistWidget(1, "YAKIN ZAMANDA BAŞLAYACAKLAR")
                .checkDefaultBranch(cons.NEAR_FUTURE_WİDGET, "FUTBOL")
                .checkEventItemsInBranch(cons.NEAR_FUTURE_WİDGET, cons.EVENT_ROW, new MainPage(driver).eventRowItems(),1)
                .clickTotalOdd(cons.EVENT_ROW, cons.NEAR_FUTURE_WİDGET);

    }

    @Test
    public void popularWidgetTest() throws IOException, InterruptedException {

        new MainPage(driver).scrollToWidget(cons.POPULAR_WIDGET)
                .isExistWidget(2, "POPÜLER MAÇLAR")
                .checkDefaultBranch(cons.POPULAR_WIDGET, "FUTBOL")
                .checkEventItemsInBranch(cons.POPULAR_WIDGET, cons.EVENT_ROW, new MainPage(driver).eventRowItems(),2);


    }

}

