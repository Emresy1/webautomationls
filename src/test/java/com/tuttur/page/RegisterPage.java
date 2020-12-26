package com.tuttur.page;

import com.tuttur.configs.PropertiesFile;
import com.tuttur.constants.RegisterPage_Constants;
import com.tuttur.util.BasePageUtil;
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




    private RegisterPage setName(String name) throws IOException {

        setObjectBy(NAME, name);
        return this;

    }

    private RegisterPage setLastName(String surname) throws IOException {

        setObjectBy(LASTNAME, surname);
        return this;

    }

    private void setDay(String day, int elementIndex) {
        setObjectsBy(DATE, elementIndex, day);

    }

    private void selectMonth(String month) {

        WebElement months = getElementBy(MONTHS);
        Select select = new Select(months);
        select.selectByVisibleText(month);

    }

    private void setYear(String year, int elementIndex) {

        setObjectsBy(DATE, elementIndex, year);

    }

    private RegisterPage setSsn(String ssn) throws IOException {

        setObjectBy(SSN, ssn);
        return this;

    }

    private RegisterPage setGsm(String gsm) throws IOException {

        setObjectBy(GSM,gsm);
        return this;

    }

    private RegisterPage setEmail(String email) throws IOException {

        setObjectBy(EMAIL,email);
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

    private RegisterPage setPassword(String password) throws IOException {

        setObjectBy(REGISTER_PASSWORD, password);
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

    public RegisterPage setBirthDate(int rowNumber, int dayIndex, int yearIndex) {

        setDay(getData(rowNumber,3),dayIndex);
        selectMonth(getData(rowNumber,4));
        setYear(getData(rowNumber,5),yearIndex);
        return this;

    }

    private RegisterPage clickSubmit() {

        clickObjectBy(SUBMIT);
        return this;

    }

    public MainPage smsActivation() throws IOException {

        String smsCode = db.getValidationCode(prop.getObject("verifyCode"));
        setObjectBy(ACTIVATION_FIELD, smsCode);
        clickObjectBy(ACTIVATION_BUTTON);
        return new MainPage(driver);

    }
    public RegisterPage isExistBanner(){

        isElementOnScreen(BANNER);
        return this;
    }
    public RegisterPage setRegisterForm(int rowNumber, int usernameIndex) throws IOException, InterruptedException {

        setName(getData(rowNumber,1));
        setLastName(getData(rowNumber,2));
        setBirthDate(rowNumber,2,3);
        setSsn(getData(rowNumber,6));
        setGsm(getData(rowNumber,7));
        setEmail(getData(rowNumber,8));
        setUsername(usernameIndex);
        setPassword(getData(rowNumber,9));
        clickMembershipApprove();
        clickSubmit();

        return this;
    }

    public RegisterPage checkWarningTextOnModal(int rowNumber, int assertCell ,int warningTextCell){

        Assert.assertTrue(getData(rowNumber,assertCell), getElementBy(WARNING_TEXT_ON_MODAL).getText()
        .equals(getData(rowNumber,warningTextCell)));

        return this;
    }

    private void checkFieldRule(By by, int index) {
        WebElement field = findElements(by).get(index);
        Assert.assertTrue("Alan değer kuralı hatalı", field.getText().isEmpty());

    }

    private List<WebElement> formInput() {

        List<WebElement> inputsArray = new ArrayList<>();
        List<WebElement> inputs = findElements(FORM_INPUT);

        int [] index = {0,1,4,5};
        for (int elementIndex: index) {

            inputsArray.add(inputs.get(elementIndex));
        }

        return inputsArray;
    }


    public RegisterPage checkInvalidValues() {


        String[] invalidValues = {getData(4,1), getData(4,2),
                getData(4,6), getData(4,7),};

        List<String> invalidValueList = Arrays.asList(invalidValues);
        int count = 0;

        for (int i = count; i < formInput().size(); i++) {

            formInput().get(i).sendKeys(invalidValueList.get(i));
            checkFieldRule(FORM_INPUT,i);

        }
        return this;

    }



}
