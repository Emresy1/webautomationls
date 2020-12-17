package com.tuttur.page;

import com.tuttur.configs.PropertiesFile;
import com.tuttur.constants.RegisterPage_Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class RegisterPage extends RegisterPage_Constants {

    public RegisterPage(WebDriver driver) throws IOException {
        super(driver);
    }

    PropertiesFile prop = new PropertiesFile(driver);
    DbQueriesPage db = new DbQueriesPage(driver);
    GeneralPage general = new GeneralPage(driver);

    private void setName() throws IOException {
        setObjectBy(NAME, prop.getObject("name"));

    }

    private void setLastName() throws IOException {
        setObjectBy(LASTNAME, prop.getObject("surname"));

    }

    private void setDay() {
        setObjectsBy(DATE, 2, "20");

    }

    private void selectMonth() {

        WebElement months = getElementBy(MONTHS);
        Select select = new Select(months);
        select.selectByVisibleText("Nisan");

    }

    private void setYear() {

        setObjectsBy(DATE, 3, "1991");

    }

    private void setSsn() throws IOException {

        setObjectBy(SSN, prop.getObject("tckn"));

    }

    private void setGsm() throws IOException {

        setObjectBy(GSM, prop.getObject("phoneNo"));

    }

    private void setEmail() throws IOException {

        setObjectBy(EMAIL, prop.getObject("currentlyEmail"));

    }

    /**
     * Methoda 0 indexi verilirse username fieldına keywordu setleyecek. index 1 olduğunda username fieldı içerisinde
     * yer alan "kullan" fonksiyonu ile kullanıcı kendi username üretmiş olacaktır.
     * @param usernameIndex
     * @throws IOException
     */

    private void setUsername(int usernameIndex) throws IOException {

        boolean click = 0<usernameIndex;
        sleep(2);
        WebElement use = findElements(By.className("medium")).get(3);

        if (click == true) {

            use.click();
            getUsernameText();


        }

        else {

            waitForElement(driver, OPT_WAIT_4_ELEMENT, REGISTER_USERNAME);
            setObjectBy(REGISTER_USERNAME, general.username);
        }
    }

    public String getUsernameText(){

        general.generateUsernameText = getElementBy(By.name("username")).getAttribute("value");

        return general.generateUsernameText;
    }

    private void setPassword() throws IOException {

        setObjectBy(REGISTER_PASSWORD, prop.getObject("newPassword"));

    }

    private void clickMembershipApprove() throws InterruptedException {

        List<WebElement> checkbox = findElements(CHECKBOX);
        scrollToElement(CHECKBOX);
        int count = 0;

        for (int loopCount = count; loopCount < checkbox.size(); loopCount++) {
            waitForElement(driver,OPT_WAIT_4_ELEMENT,CHECKBOX);
            checkbox.get(loopCount).click();

        }
    }

    private void setBirthDate() {

        setDay();
        selectMonth();
        setYear();

    }

    public RegisterPage clickSubmit() {

        clickObjectBy(SUBMIT);
        return this;

    }

    public MainPage smsActivation() throws IOException {

        String smsCode = db.getValidationCode(prop.getObject("verifyCode"));
        setObjectBy(ACTIVATION_FIELD,smsCode);
        clickObjectBy(ACTIVATION_BUTTON);
        return new MainPage(driver);

    }

    public RegisterPage setRegisterForm(int usernameIndex) throws IOException, InterruptedException {


        setName();
        setLastName();
        setBirthDate();
        setSsn();
        setGsm();
        setEmail();
        setUsername(usernameIndex);
        setPassword();
        clickMembershipApprove();

        return this;
    }

}
