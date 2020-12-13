package com.tuttur.test;

import com.tuttur.base.BaseTest;
import com.tuttur.configs.PropertiesFile;
import com.tuttur.page.MainPage;
import com.tuttur.page.RegisterPage;
import org.junit.Test;

public class RegisterTest extends BaseTest {
    PropertiesFile prop = new PropertiesFile(driver);



    @Test
    public void register (){
        new MainPage(driver).getRegisterPage();
        new RegisterPage(driver).setName();

    }
}
