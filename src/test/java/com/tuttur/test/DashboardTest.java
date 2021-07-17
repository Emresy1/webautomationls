package com.tuttur.test;

import com.tuttur.base.BaseTest;
import com.tuttur.page.MainPage;
import org.junit.Test;

import java.io.IOException;

public class DashboardTest extends BaseTest {


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
    public void liveWidgetTest() throws IOException, InterruptedException {

        new MainPage(driver).isExistLiveWidget()
                .isExistBranchInWidget()
                .checkDefaultBranch("FUTBOL")
                .checkEventItemsInBranch()
                .getFutbolBranch()
                .clickTotalOdd();



    }


}
