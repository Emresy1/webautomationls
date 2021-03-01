package com.tuttur.page;

import com.tuttur.configs.PropertiesFile;
import com.tuttur.constants.LoginPage_Constants;

import static org.junit.Assert.assertTrue;

import com.tuttur.util.ExcelUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.openqa.selenium.WebDriver;


import java.io.IOException;

public class LoginPage extends LoginPage_Constants{

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    PropertiesFile prop = new PropertiesFile(driver);
    DbQueriesPage db = new DbQueriesPage(driver);


    private void setUsername(String username) throws IOException {
        setObjectBy(USERNAME, username);

    }

    private void setPassword(String password) throws IOException {
        setObjectBy(PASSWORD, password);



    }

    public MainPage login(int rowNumber) throws IOException {

        setUsername(getData(rowNumber, 1));
        setPassword(getData(rowNumber, 2));

        clickObjectBy(REMEMBER_ME);
        clickObjectBy(BUTTON_LOGIN_ON_POPUP);
        return new MainPage(driver);

    }


    public ForgotPassPage getForgotPassModal() throws IOException {

        clickObjectBy(FORGOTPASSWORD);
        return new ForgotPassPage(driver);
    }

    public ForgotPassPage getForgotPassPage() throws IOException {

        int smsLink = db.getValidationCodeInt(prop.getObject("smsLink"), 6);
        String url = "https://ttest:q26RwfyLotHm@alpha.tuttur.com/account/reset-password/code/" + smsLink + "/type/smsLink";
        driver.get(url);

        return new ForgotPassPage(driver);
    }


    public void checkFailLogin(int rowNumber) throws IOException {

        assertTrue(getData(rowNumber, 7), getElementBy(MODAL_ERROR_TEXT).getText()
                .equals(getData(rowNumber, 6)));
    }

    public void checkFailLoginWithGsm(int rowNumber) throws IOException {

        assertTrue(getData(rowNumber, 7), getElementBy(MODAL_ERROR_TEXT).getText()
                .equals(getData(rowNumber, 6)));
    }

    public void checkFailLoginWithEmail(int rowNumber) throws IOException {

        assertTrue(getData(rowNumber, 7), getElementBy(MODAL_ERROR_TEXT).getText()
                .equals(getData(rowNumber, 6)));
    }

    public void checkInputErrorValidations(int rowNumber) throws IOException {

        assertTrue(getData(rowNumber, 7), getElemenstBy(INPUT_ERROR_TEXT, 0).getText()
                .equals(getData(rowNumber, 4)));
        assertTrue(getData(rowNumber, 7), getElemenstBy(INPUT_ERROR_TEXT, 1).getText()
                .equals(getData(rowNumber, 5)));
    }

    public void checkMissingInfoText(int rowNumber) throws IOException {

        assertTrue(getData(rowNumber, 7), getElementBy(MODAL_ERROR_TEXT).getText()
                .equals(getData(rowNumber, 6)));
    }

    public void checkRememberMe() throws IOException {

        assertTrue("Beni hatırla başarısız", getElementBy(USERNAME).getText().equals(prop.getObject("rememberMeUser")));
    }

}
