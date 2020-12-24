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



    private RegisterPage setName(int rowNumber) throws IOException {
        setObjectBy(NAME, getData(rowNumber,1));
        return this;

    }

    private RegisterPage setLastName(int rowNumber) throws IOException {

        setObjectBy(LASTNAME, getData(rowNumber,2));
        return this;

    }

    private void setDay(int rowNumber) {
        setObjectsBy(DATE, 2,getData(rowNumber,3));

    }

    private void selectMonth(int rowNumber) {

        WebElement months = getElementBy(MONTHS);
        Select select = new Select(months);
        select.selectByVisibleText(getData(rowNumber,4));

    }

    private void setYear(int rowNumber) {

        setObjectsBy(DATE, 3, getData(rowNumber,5));

    }

    private RegisterPage setSsn(int rowNumber) throws IOException {

        setObjectBy(SSN, getData(rowNumber,6));
        return this;

    }

    private RegisterPage setGsm(int rowNumber) throws IOException {

        setObjectBy(GSM,getData(rowNumber,7));
        return this;

    }

    private RegisterPage setEmail(int rowNumber) throws IOException {

        setObjectBy(EMAIL, getData(rowNumber,8));
        return this;

    }

    /**
     * Methoda 0 indexi verilirse username fieldına keywordu setleyecek. index 1 olduğunda username fieldı içerisinde
     * yer alan "kullan" fonksiyonu ile kullanıcı kendi username üretmiş olacaktır.
     *
     * @param usernameIndex
     * @throws IOException
     */

    private RegisterPage setUsername(int usernameIndex) throws IOException {

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

    private RegisterPage setPassword(int rowNumber) throws IOException {

        setObjectBy(REGISTER_PASSWORD, getData(rowNumber,9));
        return this;

    }

    private RegisterPage clickMembershipApprove() throws InterruptedException {

        List<WebElement> checkbox = findElements(CHECKBOX);
        scrollToElement(CHECKBOX);
        int count = 0;

        for (int loopCount = count; loopCount < checkbox.size(); loopCount++) {
            waitForElement(driver, OPT_WAIT_4_ELEMENT, CHECKBOX);
            checkbox.get(loopCount).click();

        }
        return this;
    }

    private RegisterPage setBirthDate(int rowNumber) {

        setDay(rowNumber);
        selectMonth(rowNumber);
        setYear(rowNumber);
        return this;

    }

    private RegisterPage clickSubmit() {

        clickObjectBy(SUBMIT);
        return this;

    }

    private MainPage smsActivation() throws IOException {

        String smsCode = db.getValidationCode(prop.getObject("verifyCode"));
        setObjectBy(ACTIVATION_FIELD, smsCode);
        clickObjectBy(ACTIVATION_BUTTON);
        return new MainPage(driver);

    }
    public MainPage setRegisterForm(int rowNumber, int usernameIndex) throws IOException, InterruptedException {
        setName(rowNumber);
        setLastName(rowNumber);
        setBirthDate(rowNumber);
        setSsn(rowNumber);
        setGsm(rowNumber);
        setEmail(rowNumber);
        setUsername(usernameIndex);
        setPassword(rowNumber);
        clickMembershipApprove();
        clickSubmit();
        smsActivation();

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
