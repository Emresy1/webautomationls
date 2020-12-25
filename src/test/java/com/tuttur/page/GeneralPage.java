package com.tuttur.page;

import com.tuttur.base.BasePage;
import com.tuttur.configs.PropertiesFile;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class GeneralPage extends BasePage {



    public GeneralPage(WebDriver driver) throws IOException {
        super(driver);
    }

    PropertiesFile prop = new PropertiesFile(driver);


    public static String usernameText = "automation" + generateRandomInt(9999);

    public static String username =  usernameText;

    public static String generateUsernameText;

    public static String newPasswordChange = "Test" + generateRandomInt(9999);


    }
