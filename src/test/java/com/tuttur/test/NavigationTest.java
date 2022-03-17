package com.tuttur.test;

import com.tuttur.base.BaseTest;
import com.tuttur.page.MainPage;
import com.tuttur.page.NavigationPage;
import com.tuttur.util.BasePageUtil;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;


public class NavigationTest extends BaseTest {


    BasePageUtil util = new BasePageUtil(driver);


    /**
     * Case 1.0
     * Sosyal medya yönlendirmeleri
     */

    @Test(priority = 0)
    public void checkRedirectSocialMedia () throws IOException, InterruptedException {

        new MainPage(driver).checkRedirectSocialMediaUrl();

     }

    /**
     * Case 1.1
     * Market yönlendirmeleri
     */

    @Test(priority = 1)
    public void checkRedirectStore() throws IOException {

        new MainPage(driver).checkRedirectStoreUrl()
                .downloadApk();
        new MainPage(driver).checkDownloadedApk();

    }

//    /**
//     * Case 1.2
//     * Header alt menü yönlendirmeleri
//     * @throws IOException
//     */
//
//    @Test
//    public void headerSubmenuRedirect () throws IOException {
//
//        new NavigationPage(driver).checkSubmenuUrl();
//    }

    /**
     * Case 1.3
     * Banner üstü kısayol yönlendirmeleri
     * @throws IOException
     */

    @Test(priority = 2)
    public void checkShortcutMenuRedirect () throws IOException {

        new NavigationPage(driver).checkShortcutMenuUrl();
    }

}


