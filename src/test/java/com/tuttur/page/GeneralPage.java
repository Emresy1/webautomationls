package com.tuttur.page;

import com.tuttur.base.BasePage;
import org.openqa.selenium.WebDriver;
import java.io.IOException;

public class GeneralPage extends BasePage {

    DbQueriesPage db = new DbQueriesPage(driver);

    public GeneralPage(WebDriver driver) throws IOException {
        super(driver);


    }



    public String usernameText = "automation" + generateRandomInt(9999);

    public static String username;

    public static String generateUsernameText;

    public static String newPasswordChange = "TestPass" + generateRandomInt(9999);

    public static String usernamePlaceholder;

    public static String refreshUsername;

    public static String staticUsername = "betult787885";


    public int campaignId(int code){

        String campaign = "UPDATE application.campaign SET status = false WHERE id="+code+";";
        return db.getValidationCodeInt(campaign,1);

    }

    }
