package com.tuttur.test;

import com.tuttur.base.BaseTest;
import com.tuttur.page.MainPage;
import org.junit.Test;

import java.io.IOException;

public class BetSlipTest extends BaseTest {


    @Test
    public void emptyStateControl () throws IOException, InterruptedException {

        new MainPage(driver).betSlipEmptyStateControl();

    }
}
