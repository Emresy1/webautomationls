package com.tuttur.page;

import com.tuttur.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class GeneralPage extends BasePage {



    public GeneralPage(WebDriver driver) throws IOException {
        super(driver);


    }


    public String usernameText = "automation" + generateRandomInt(9999);

    public static String username;

    public static String generateUsernameText;

    public static String newPasswordChange = "Test" + generateRandomInt(9999);

    public static String usernamePlaceholder;

    public static String refreshUsername;

    public static String staticUsername = "betult39";



    }
