package com.tuttur.test;

import com.tuttur.base.BaseTest;
import com.tuttur.page.MainPage;

import org.junit.Test;


import java.io.IOException;

public class NavigationTest extends BaseTest {


    /**
     * Case 1.0
     * Sosyal medya yönlendirmeleri
     */

    @Test
    public void checkRedirectSocialMedia () throws IOException, InterruptedException {

        new MainPage(driver).checkRedirectSocialMediaUrl();

     }

    /**
     * Case 1.1
     * Market yönlendirmeleri
     */

    @Test
    public void checkRedirectStore() throws IOException {

        new MainPage(driver).checkRedirectStoreUrl();

    }
}


