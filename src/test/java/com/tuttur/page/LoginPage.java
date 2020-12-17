package com.tuttur.page;

import com.tuttur.configs.PropertiesFile;
import com.tuttur.constants.LoginPage_Constants;
import static org.junit.Assert.assertTrue;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class LoginPage extends LoginPage_Constants {

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    PropertiesFile prop = new PropertiesFile(driver);

    private void setUsername(String username) throws IOException {
        setObjectBy(USERNAME,username);

    }
    private void setPassword(String password) throws IOException {
        setObjectBy(PASSWORD, password);

    }

    public MainPage login(String user, String password) throws IOException {
        setUsername(user);
        setPassword(password);
        clickObjectBy(REMEMBER_ME);
        clickObjectBy(BUTTON_LOGIN_ON_POPUP);
        return new MainPage(driver);
    }

    public ForgotPassPage getForgotPass () {
        clickObjectBy(FORGOTPASSWORD);
        return new ForgotPassPage(driver);
    }

    public void checkFailLogin () throws IOException {
        assertTrue("Başarısız login hatalı",getElementBy(FIELD_ERROR_TEXT).getText().equals(prop.getObject("fail_login_message")));
    }

    public void checkFailLoginWithGsm () throws IOException {
        assertTrue("Başarısız login hatalı",getElementBy(FIELD_ERROR_TEXT).getText().equals(prop.getObject("fail_login_message_gsm")));
    }

    public void checkFailLoginWithEmail () throws IOException {
        assertTrue("Başarısız login hatalı",getElementBy(FIELD_ERROR_TEXT).getText().equals(prop.getObject("fail_login_message_email")));
    }

    public void checkInputErrorValidations () throws IOException {
        assertTrue("Error mesajı hatalı",getElemenstBy(INPUT_ERROR_TEXT,0).getText().equals(prop.getObject("user_maxLenght_message")));
        assertTrue("Error mesajı hatalı",getElemenstBy(INPUT_ERROR_TEXT,1).getText().equals(prop.getObject("password_maxLenght_message")));
    }

    public void checkMissingInfoText () throws IOException {
        assertTrue("Error mesajı hatalı",getElementBy(FIELD_ERROR_TEXT).getText().equals(prop.getObject("empty_login_message")));
    }

    public void checkRememberMe () throws IOException {
        assertTrue("Beni hatırla başarısız",getElementBy(USERNAME).getText().equals(prop.getObject("username")));
    }

}
