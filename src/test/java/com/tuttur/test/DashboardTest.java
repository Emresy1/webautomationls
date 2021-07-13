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

//        new MainPage(driver).isExistBanner();
//        new MainPage(driver).bannerSlider()
//                .isExistOddOnBanner()
//                .isExistBulletAndArrow()
                new MainPage(driver).addOddToBetslip();

    }


}
