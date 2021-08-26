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


            String[] contract = {"KULLANICI SÖZLEŞMESI","GIZLILIK SÖZLEŞMESI","AYDINLATMA METNI",
                    "AÇIK RIZA ONAY METNI","KIŞISEL VERI BAŞVURU FORMU"};

            for (String contractName : contract) {

                waitForTextOnElement(driver, OPT_WAIT_4_ELEMENT, CONTRACT_TAB_ACTIVE, contractName);

                if (contractName.equals(driver.findElements(CONTRACT_TAB_ACTIVE).get(0).getText())) {

                    scrollToElement(CONTRACT_CHECKBOX);

                    clickObjectBy(CONTRACT_CHECKBOX);

                    WebElement buttonAccept = getElementBy(BUTTON_ACCEPT);
                    waitForElement(buttonAccept, MIN_WAIT_4_ELEMENT);

                    clickObjectBy(BUTTON_ACCEPT);


                }
            }
        }

    public MainPage login(int rowNumber, String contract) throws IOException, InterruptedException {


        setUsername(getData(rowNumber, 1));
        setPassword(getData(rowNumber, 2));

        WebElement buttonLogin = getElementBy(BUTTON_LOGIN_ON_POPUP);

        clickObjectBy(REMEMBER_ME);
        clickObjectBy(BUTTON_LOGIN_ON_POPUP);


        switch (contract)
        {
            case "contracts":

                waitForElementDisappear(buttonLogin);
                confirmContract();
                break;

            case "contract":

                waitForElementDisappear(buttonLogin);

                clickObjectBy(CONTRACT_CHECKBOX);
                clickObjectBy(BUTTON_ACCEPT);

                break;
        }

            return new MainPage(driver);
    }


    public ForgotPassPage getForgotPassModal() throws IOException {

        clickObjectBy(FORGOTPASSWORD);

        return new ForgotPassPage(driver);
    }


    public void checkWarningMessageOnModal(String message) throws IOException {

        assertTrue(prop.getObject("failLoginCheck"), getElementBy(MODAL_ERROR_TEXT)
           .getText().equals(message));
    }

    public LoginPage checkInputErrorMessages(int index, String message) throws IOException {

        assertTrue(prop.getObject("errorMessageCheck"), getElemenstBy(INPUT_ERROR_TEXT, index).getText()
                .equals(message));
        return this;
    }


    public void checkInputInfoText() throws IOException {

        checkInputErrorMessages(0,prop.getObject("entryValidInfo"));
        checkInputErrorMessages(1,prop.getObject("min6characters"));

    }


}
