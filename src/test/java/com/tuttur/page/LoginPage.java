package com.tuttur.page;

import com.tuttur.configs.PropertiesFile;
import com.tuttur.constants.LoginPage_Constants;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.io.IOException;


public class LoginPage extends LoginPage_Constants {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    PropertiesFile prop = new PropertiesFile(driver);
    DbQueriesPage db = new DbQueriesPage(driver);


    private void setUsername(String username) {

        setObjectBy(USERNAME, username);

    }

    private void setPassword(String password){
        setObjectBy(PASSWORD, password);


    }


    private void confirmContract() throws InterruptedException {


        if (isExist(OPT_WAIT_4_ELEMENT,CONTRACT_HEADER)) {

            waitForElement(driver, MIN_WAIT_4_ELEMENT, CONTRACT_HEADER);

            String[] contract = {"KULLANICI SÖZLEŞMESI","GIZLILIK SÖZLEŞMESI","AYDINLATMA METNI",
                    "AÇIK RIZA ONAY METNI","KIŞISEL VERI BAŞVURU FORMU"};

            for (String contractName : contract) {

                waitForTextOnElement(driver, OPT_WAIT_4_ELEMENT, CONTRACT_TAB, contractName);

                if (contractName.equals(driver.findElements(CONTRACT_TAB).get(0).getText())) {

                    scrollToElement(CONTRACT_CHECKBOX);

                    clickObjectBy(CONTRACT_CHECKBOX);

                    WebElement buttonAccept = getElementBy(BUTTON_ACCEPT);
                    waitForElement(buttonAccept, MIN_WAIT_4_ELEMENT);

                    clickObjectBy(BUTTON_ACCEPT);


                }
            }
        }
    }


    public LoginPage login(int rowNumber) throws IOException, InterruptedException {

        setUsername(getData(rowNumber, 1));
        setPassword(getData(rowNumber, 2));

        WebElement buttonLogin = getElementBy(By.cssSelector(".medium.primary.loginForm-button"));

        clickObjectBy(REMEMBER_ME);
        clickObjectBy(BUTTON_LOGIN_ON_POPUP);


        if (driver.findElements(MODAL_ERROR_TEXT).size() == 0) {

            waitForElementDisappear(buttonLogin);
            confirmContract();
        }

            return this;
    }

    public MainPage clickButtonLogin () throws IOException {

        clickObjectBy(BUTTON_LOGIN_ON_POPUP);

        return new MainPage (driver);
    }

    public ForgotPassPage getForgotPassModal() throws IOException {

        clickObjectBy(FORGOTPASSWORD);

        return new ForgotPassPage(driver);
    }


    public void checkFailLogin(int rowNumber) throws IOException {

        assertTrue(prop.getObject("failLoginCheck"), getElementBy(MODAL_ERROR_TEXT).isDisplayed());
        //   getText().equals(getData(rowNumber, 6)));
    }

    public void checkFailLoginWithGsm(int rowNumber) throws IOException {

        assertTrue(getData(rowNumber, 7), getElementBy(INPUT_ERROR_TEXT).getText()
                .equals(getData(rowNumber, 6)));
    }

    public void checkFailLoginWithEmail(int rowNumber) throws IOException {

        assertTrue(getData(rowNumber, 7), getElementBy(MODAL_ERROR_TEXT).isDisplayed());
        //   .getText().equals(getData(rowNumber, 6)));
    }

    public void checkInputErrorValidations(int rowNumber) throws IOException {

        assertTrue(prop.getObject("errorMessageCheck"), getElemenstBy(INPUT_ERROR_TEXT, 0).getText()
                .equals(getData(rowNumber, 6)));
    }

    public void checkMissingInfoText(int rowNumber) throws IOException {

        assertTrue(prop.getObject("errorMessageCheck"), getElementBy(MODAL_ERROR_TEXT).getText()
                .equals(getData(rowNumber, 6)));

    }

    public void checkInputInfoText(int rowNumber) throws IOException {

        assertTrue(prop.getObject("errorMessageCheck"), getElementBy(INPUT_ERROR_TEXT).getText()
                .equals(getData(rowNumber, 4)));
        assertTrue(prop.getObject("errorMessageCheck"), getElemenstBy(INPUT_ERROR_TEXT, 1).getText()
                .equals(getData(rowNumber, 5)));

    }

    public void checkRememberMe() throws IOException {

        assertTrue("Beni hatırla başarısız", getElementBy(USERNAME).getText().equals(prop.getObject("rememberMeUser")));
    }

}
