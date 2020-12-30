package com.tuttur.page;

import com.tuttur.configs.PropertiesFile;
import com.tuttur.constants.RegisterPage_Constants;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;


public class RegisterPage extends RegisterPage_Constants {

    public RegisterPage(WebDriver driver) throws IOException {
        super(driver);
    }

    PropertiesFile prop = new PropertiesFile(driver);
    DbQueriesPage db = new DbQueriesPage(driver);
    GeneralPage general = new GeneralPage(driver);
    SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", new Locale("tr"));
    Date currentMonth = new Date();
    LocalDate currentDate = LocalDate.now();
    Actions action = new Actions(driver);






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

    public RegisterPage checkForgotPasswordModal(int rowNumber){

        waitForElement(driver,OPT_WAIT_4_ELEMENT,BUTTON_RESET_PASSWORD);

        Assert.assertEquals(getElementBy(FORGOT_PASS_TITLE).getText(), getData(rowNumber,13));

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

    public RegisterPage checkBirthdateMaxLenght(){
        List<WebElement> inputs = findElements(FORM_INPUT);

        String maxChar = "12345";


        inputs.get(2).sendKeys(maxChar);
        inputs.get(3).sendKeys(maxChar);

        Assert.assertEquals(inputs.get(2).getAttribute("value").length(),2);
        Assert.assertEquals(inputs.get(3).getAttribute("value").length(),4);

        isSelectboxMonth();

        return this;

    }

    private void reopenModal() throws IOException {

        clickObjectBy(CLOSE_MODAL);
        new MainPage(driver).getRegisterPage();
    }

    private List<String> propertiesWarningTexts() throws IOException {

        String[] propWarningText = {prop.getObject("letterCheck"),prop.getObject("numberCheck"),
                prop.getObject("lenghtCheck"), prop.getObject("specialCharacterCheck"),
                prop.getObject("upperLowerLetterCheck")};

        List<String> warningTexts = Arrays.asList(propWarningText);

        return warningTexts;

    }

    private List<WebElement> warningTexts(){

        List<WebElement> warningTexts = findElements(WARNING_TEXT);
        return warningTexts;

    }

    public RegisterPage checkPasswordCombination() throws IOException, InterruptedException {

        String character = "*";
        setObjectBy(REGISTER_PASSWORD,character);

        scrollToElement(LASTNAME);

        int count =0;

            for (int i=count; i< warningTexts().size(); i++){

                Assert.assertEquals(warningTexts().get(i).getText(), propertiesWarningTexts().get(i));
            }

            typeCorrectPassword();


        return this;
    }
    private void typeCorrectPassword() throws IOException, InterruptedException {

        String newPassword = "afgs3dsd-Ab";

        reopenModal();
        scrollToElement(LASTNAME);
        setObjectBy(REGISTER_PASSWORD,newPassword);


        try {
           Assert.assertTrue("Uyarı texti kaybolmadı", !getElementBy(WARNING_TEXT).isDisplayed());
        }

        catch (Exception exp){
            exp.printStackTrace();
        }

    }

    private void typeRuleDate() throws IOException {

        reopenModal();

        WebElement falseDate = getElemenstBy(FORM_INPUT, 2);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) +1;
        String currentDate = Integer.toString(day);

        falseDate.sendKeys(currentDate);



    }
    private void selectRuleMonth() {

        String[] months = {"Ocak","Şubat","Mart","Nisan","Mayıs","Haziran","Temmuz","Ağustos","Eylül","Ekim"
        ,"Kasım","Aralık"};
        List<String> monthList = Arrays.asList(months);


        for (String month: monthList) {
            if (month.equals(monthFormat.format(currentMonth))){

               selectMonth(month);

            }
        }
    }

    private void typeYear(){

        int year = Calendar.getInstance().get(Calendar.YEAR);
        String currentYear = Integer.toString(year);

        setObjectsBy(FORM_INPUT,3,currentYear);

    }

    public RegisterPage check18YearsRule() throws IOException {

        typeRuleDate();
        selectRuleMonth();
        typeYear();
            /*
            Gün ve Ay inputların 18 yaş kuralı yok fixlendiğinde assert edilecek
             */

        return this;
    }

    private boolean isSelectboxMonth(){

        WebElement month = getElementBy(BIRTHDATE).findElement(By.tagName("select"));
        return isExistElement(MIN_WAIT_4_ELEMENT,month);
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

        setObjectBy(EMAIL, getData(4,8));
        clickObjectBy(REGISTER_USERNAME);

        waitForElement(driver,OPT_WAIT_4_ELEMENT,INPUT_ERROR_TEXT);
        Assert.assertEquals(getElementBy(INPUT_ERROR_TEXT).getText(),getData(4,11));

        return this;

    }



}
