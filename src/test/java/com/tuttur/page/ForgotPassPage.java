package com.tuttur.page;

import com.tuttur.base.BaseTest;
import com.tuttur.configs.PropertiesFile;
import com.tuttur.constants.ForgotPass_Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertTrue;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ForgotPassPage extends ForgotPass_Constants {
    public ForgotPassPage(WebDriver driver) throws IOException {
        super(driver);
    }


    Actions actions = new Actions(driver);
    DbQueriesPage db = new DbQueriesPage(driver);
    PropertiesFile prop = new PropertiesFile(driver);
    GeneralPage general = new GeneralPage(driver);

    private void setSSN() {
        setObjectBy(SSN, "17376674056");
    }

    private void setBirthDate () {

        List <WebElement> selectColomun = findElements(BIRTHDATE);
        String[] date = {"20","Nisan","1991"};
        List <String> dateList = Arrays.asList(date);
        int count = 0;

        for (int i = count; i < selectColomun.size();i++) {
            Select select = new Select(selectColomun.get(i));
            select.selectByVisibleText(dateList.get(i));
        }

    }

    /**
     * Şifremi unuttum ekranındaki checkboxlar için
     * İndex = 1 sms checkbox
     * index = 2 mail checkbox
     *
     * @param index
     * @return
     */
    private WebElement checkbox(int index) {

        return findElements(SEND_SMS_CHECKBOX).get(index);
    }


    public LoginPage forgotPassAction() {
        setSSN();
        setBirthDate();
        waitForElement(driver, MIN_WAIT_4_ELEMENT, RESET_PASS);
        clickObjectBy(RESET_PASS);
        sleep(2);
        checkbox(1).click();
        clickObjectBy(By.cssSelector(".medium.primary.w-100"));
        sleep(1);
        buttonClose().click();
        return new LoginPage(driver);

    }

    public WebElement buttonClose() {

        waitForElement(driver, OPT_WAIT_4_ELEMENT, By.cssSelector(".medium.primary"));
        return getElemenstBy(By.cssSelector(".medium.primary"), 1);
    }

    public ForgotPassPage setPasswordChange() throws IOException {

        setObjectBy(PASSWORD_INPUT, general.newPasswordChange);
        clickObjectBy(CHANGE_BUTTON);
        switchToWindows();
        checkPasswordChange();
        clickObjectBy(LOGIN_BUTTON);

        return this;

    }




    private void checkPasswordChange() throws IOException {

        assertTrue("şifre değişikliği başarısız", getElementBy(SUCCESS_MESSAGE)
                .getText().substring(0, 26).equals(prop.getObject("success_pass_change_message")));

    }

}


