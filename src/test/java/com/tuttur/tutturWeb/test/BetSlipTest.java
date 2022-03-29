package com.tuttur.tutturWeb.test;

import com.tuttur.tutturWeb.base.BaseTest;
import com.tuttur.util.BasePageUtil;

public class BetSlipTest extends BaseTest {

    BasePageUtil base = new BasePageUtil(driver);


//    /**
//     * Case 1.0
//     * Betslip empty state
//     * @throws IOException
//     * @throws InterruptedException
//     */
//
//    @Test
//    public void emptyStateControl () throws IOException, InterruptedException {
//
//        new MainPage(driver).betSlipEmptyStateItemsControl()
//                .liveMatchesRedirectControl();
//
//    }
//
//    /**
//     * Case 2.0
//     * Devam eden tabı
//     * @throws IOException
//     * @throws InterruptedException
//     */
//
//    @Test
//    public void ContinuingTabTest () throws IOException, InterruptedException {
//
//        base.getSheet("BetSlipData");
//
//        new MainPage(driver).getLoginPage()
//                .login(1,"non-contract")
//                .isDisabledContinuingTab();
//        new MainPage(driver).logout()
//                .getLoginPage()
//                .login(2,"non-contract")
//                .addEventToBetSlip("1")
//                .checkEventItemsOnBetslip()
//                .playCoupon()
//                .continuingTabControl()
//                .continuingTabCouponDetails();
//
//    }
}
