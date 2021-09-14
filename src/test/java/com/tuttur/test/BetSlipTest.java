package com.tuttur.test;

import com.tuttur.base.BasePage;
import com.tuttur.base.BaseTest;
import com.tuttur.page.MainPage;
import com.tuttur.util.BasePageUtil;
import org.junit.Test;

import java.io.IOException;

public class BetSlipTest extends BaseTest {

    BasePageUtil base = new BasePageUtil(driver);


    @Test
    public void emptyStateControl () throws IOException, InterruptedException {

        new MainPage(driver).betSlipEmptyStateItemsControl()
                .liveMatchesRedirectControl();

    }

    @Test
    public void ContinuingTabTest () throws IOException, InterruptedException {

        base.getSheet("BetSlipData");

        new MainPage(driver).getLoginPage().login(1,"noncontract")
                .isDisabledContinuingTab();
        new MainPage(driver).logout()
                .getLoginPage().login(2,"noncontract")
                .addEventToBetSlip();

    }
}
