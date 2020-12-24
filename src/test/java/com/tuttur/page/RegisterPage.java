package com.tuttur.page;

import com.tuttur.configs.PropertiesFile;
import com.tuttur.constants.RegisterPage_Constants;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RegisterPage extends RegisterPage_Constants {

    public RegisterPage(WebDriver driver) throws IOException {
        super(driver);
    }

    PropertiesFile prop = new PropertiesFile(driver);
    DbQueriesPage db = new DbQueriesPage(driver);
    GeneralPage general = new GeneralPage(driver);

    public RegisterPage setName(int rowNumber) throws IOException {
        setObjectBy(NAME, getData(rowNumber,1));
        return this;

    }

    public RegisterPage setLastName(String surname) throws IOException {

        setObjectBy(LASTNAME, surname);
        return this;

    }

    private void setDay(String day) {
        setObjectsBy(DATE, 2,day);

    }

    private void selectMonth(String month) {

        WebElement months = getElementBy(MONTHS);
        Select select = new Select(months);
        select.selectByVisibleText(month);

    }

    private void setYear(String year) {

        setObjectsBy(DATE, 3, year);

    }

    public RegisterPage setSsn( String tckn) throws IOException {

        setObjectBy(SSN, tckn);
        return this;

    }

    public RegisterPage setGsm(String gsm) throws IOException {

        setObjectBy(GSM,gsm);
        return this;

    }

    public RegisterPage setEmail(String email) throws IOException {

        setObjectBy(EMAIL, email);
        return this;

    }

    /**
     * Methoda 0 indexi verilirse username fieldına keywordu setleyecek. index 1 olduğunda username fieldı içerisinde
     * yer alan "kullan" fonksiyonu ile kullanıcı kendi username üretmiş olacaktır.
     *
     * @param usernameIndex
     * @throws IOException
     */

    public RegisterPage setUsername(int usernameIndex) throws IOException {

        boolean click = 0 < usernameIndex;
        sleep(2);
        WebElement use = findElements(By.className("medium")).get(3);

        if (click == true) {

            use.click();
            getUsernameText();

        } else {

            waitForElement(driver, OPT_WAIT_4_ELEMENT, REGISTER_USERNAME);
            setObjectBy(REGISTER_USERNAME, general.username);
        }
        return this;
    }

    public String getUsernameText() {

        general.generateUsernameText = getElementBy(By.name("username")).getAttribute("value");

        return general.generateUsernameText;
    }

    public RegisterPage setPassword(String password) throws IOException {

        setObjectBy(REGISTER_PASSWORD, password);
        return this;

    }

    public RegisterPage clickMembershipApprove() throws InterruptedException {

        List<WebElement> checkbox = findElements(CHECKBOX);
        scrollToElement(CHECKBOX);
        int count = 0;

        for (int loopCount = count; loopCount < checkbox.size(); loopCount++) {
            waitForElement(driver, OPT_WAIT_4_ELEMENT, CHECKBOX);
            checkbox.get(loopCount).click();

        }
        return this;
    }

    public RegisterPage setBirthDate(String day, String month,String year) {

        setDay(day);
        selectMonth(month);
        setYear(year);
        return this;

    }

    public RegisterPage clickSubmit() {

        clickObjectBy(SUBMIT);
        return this;

    }

    public MainPage smsActivation() throws IOException {

        String smsCode = db.getValidationCode(prop.getObject("verifyCode"));
        setObjectBy(ACTIVATION_FIELD, smsCode);
        clickObjectBy(ACTIVATION_BUTTON);
        return new MainPage(driver);

    }


    private void checkFieldRule(By by, int index) {
        WebElement field = findElements(by).get(index);
        Assert.assertTrue("Alan değer kuralı hatalı", field.getText().isEmpty());

    }

    private List<WebElement> formInput() {

        List<WebElement> inputsArray = new ArrayList<>();
        List<WebElement> inputs = findElements(FORM_INPUT);
        inputsArray.add(inputs.get(0));
        inputsArray.add(inputs.get(1));
        inputsArray.add(inputs.get(4));
        inputsArray.add(inputs.get(5));
        return inputsArray;
    }


    public RegisterPage setInvalidValue() {


        String[] invalidValues = {"123", "123", "ASDFGH", "ASDFGH",};
        List<String> invalidValueList = Arrays.asList(invalidValues);
        int count = 0;

        for (int i = count; i < formInput().size(); i++) {
            formInput().get(i).sendKeys(invalidValueList.get(i));
            checkFieldRule(FORM_INPUT,i);

        }
        return this;

    }



}
