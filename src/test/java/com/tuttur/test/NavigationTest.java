package com.tuttur.test;

import com.tuttur.base.BaseTest;
import com.tuttur.page.NavigationPage;
import org.junit.Test;

public class NavigationTest extends BaseTest {



    @Test
    public void ıddaaSubmenuClick () {

        new NavigationPage(driver).submenu();

    }




}
