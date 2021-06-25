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

    private String attributeValue = "value";


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

        setObjectBy(GSM, gsm);
        return this;

    }

    private RegisterPage setEmail(String email) throws IOException {

        setObjectBy(EMAIL, email);
        return this;

    }

    /**
     * Methoda 0 indexi verilirse username fieldına keywordu setleyecek. index 1 olduğunda username fieldı içerisinde
     * yer alan "kullan" fonksiyonu ile, index 2 olduğunda ise önerilen username yeni bir username ile değişecek
     * ve kullanıcı kendi username üretmiş olacaktır.
     *
     * @param usernameSelection
     * @throws IOException
     */

    private RegisterPage setUsername(String usernameSelection) throws IOException {

        sleep(2);
        List<WebElement> use = findElements(USERNAME_FUNCTION);

        switch (usernameSelection){

            case "username-function":
                use.get(3).click();
                getUsernameText();

                break;

            case "username-type":

                waitForElement(driver, OPT_WAIT_4_ELEMENT, REGISTER_USERNAME);
                setObjectBy(REGISTER_USERNAME, general.usernameText);

                general.username = getAttribute(REGISTER_USERNAME,attributeValue);


                break;

            default:

                String placeholder = getAttribute(REGISTER_USERNAME,"placeholder").substring(10);

                general.usernamePlaceholder = placeholder;

                use.get(2).click();

                WebElement loading = getElementBy(USERNAME_LOADING);
                waitForInvisibility(loading,OPT_WAIT_4_ELEMENT);

                Assert.assertNotEquals(general.usernamePlaceholder,
                        getAttribute(REGISTER_USERNAME,"placeholder").substring(10));
                use.get(3).click();

                general.refreshUsername = getAttribute(REGISTER_USERNAME,"placeholder").substring(10);


        }

        return this;
    }

    public String getUsernameText() {

        general.generateUsernameText = getAttribute(REGISTER_USERNAME,attributeValue);

        return general.generateUsernameText;
    }

    private RegisterPage setPassword(String password) throws IOException {

        setObjectBy(REGISTER_PASSWORD, password);
        return this;

    }

    public RegisterPage clickMembershipApprove(int index) throws InterruptedException, IOException {

        List<WebElement> checkbox = findElements(CHECKBOX);
        scrollToElement(CHECKBOX);

        int count = 0;
        int indexCount = 0;

        if (index == indexCount){

                for (int loopCount = count; loopCount < checkbox.size(); loopCount++) {

                    waitForElement(driver, OPT_WAIT_4_ELEMENT, CHECKBOX);
                    checkbox.get(loopCount).click();

            }
        }

        else {

            for (int loopCount=0; loopCount < 2; loopCount++) {

                waitForElement(driver, OPT_WAIT_4_ELEMENT, CHECKBOX);
                clickSubmit();

                Assert.assertTrue("", getElementBy(WARNING_TEXT_ON_MODAL)
                        .getText().equals(prop.getObject("mandatoryFieldMessage")));

                checkbox.get(loopCount).click();
            }
           }

        return this;
    }


    public RegisterPage inputPatternCheck() throws IOException {

        reopenModal();
        ssnPatternCheck();
        gsmPatternCheck();
        emailPatternCheck();

        return this;
    }

    public void checkFormErrorMessage(String text){

        waitForElement(driver,OPT_WAIT_4_ELEMENT,WARNING_TEXT_ON_MODAL);

        Assert.assertTrue("Uyarı mesajı yanlış", getElementBy(WARNING_TEXT_ON_MODAL).getText()
                .replace(")","").equals(text));
    }

    private WebElement modalTitle() {

        return getElementBy(MODAL_TITLE);
    }

    private void gsmPatternCheck() throws IOException {

        WebElement gsm = getElementBy(GSM);

        gsm.sendKeys(getData(4, 7));
        modalTitle().click();

        Assert.assertTrue("Gsm uyarı texti görülmedi", getElemenstBy(INPUT_ERROR_TEXT, 1).getText()
                .equals(prop.getObject("gsmPatternMessage")));

    }

    private void emailPatternCheck() throws IOException {

        WebElement email = getElementBy(EMAIL);

        email.sendKeys(getData(4, 8));
        modalTitle().click();

        Assert.assertTrue("Email uyarı texti görülmedi", getElemenstBy(INPUT_ERROR_TEXT, 2).getText()
                .equals(prop.getObject("emailPatternMessage")));

    }

    private void ssnPatternCheck() throws IOException {

        WebElement ssn = getElementBy(SSN);

        ssn.sendKeys(getData(4, 6));
        modalTitle().click();

        Assert.assertTrue("Tckn uyarı texti görülmedi", getElementBy(INPUT_ERROR_TEXT).getText()
                .equals(prop.getObject("ssnPatternMessage")));


    }

    public RegisterPage setBirthDate(int rowNumber, int dayIndex, int yearIndex) {

        setDay(getData(rowNumber, 3), dayIndex);
        selectMonth(getData(rowNumber, 4));
        setYear(getData(rowNumber, 5), yearIndex);
        return this;

    }

    public RegisterPage clickSubmit() {

        clickObjectBy(SUBMIT);
        return this;

    }

    public WelcomePage smsActivation() throws IOException {

        WebElement activatiionModal = getElementBy(ACTIVATION_FIELD);

        String smsCode = db.getValidationCode(prop.getObject("verifyCode"));
        setObjectBy(ACTIVATION_FIELD, smsCode);
        clickObjectBy(ACTIVATION_BUTTON);

        waitForElementDisappear(activatiionModal);

        return new WelcomePage(driver);
    }

    public RegisterPage isExistBanner() {

        isElementOnScreen(BANNER);
        return this;
    }

    public RegisterPage checkForgotPasswordModal(int rowNumber) {

        waitForElement(driver, OPT_WAIT_4_ELEMENT, BUTTON_RESET_PASSWORD);

        Assert.assertEquals(getElementBy(MODAL_TITLE).getText(), getData(rowNumber, 13));

        return this;
    }

    public RegisterPage setRegisterForm(int rowNumber) throws IOException, InterruptedException {

        setName(getData(rowNumber, 1));
        setLastName(getData(rowNumber, 2));
        setSsn(getData(rowNumber, 6));
        setBirthDate(rowNumber, 1, 2);
        setGsm(getData(rowNumber, 7));
        setEmail(getData(rowNumber, 8));
        //setUsername(usernameSelection);
        setPassword(getData(rowNumber, 9));

        return this;
    }

    public RegisterPage checkWarningTextOnModal() throws IOException {

        Assert.assertTrue("Uyarı texti hatalı", getElementBy(WARNING_TEXT_ON_MODAL).getText()
                .replace(")","").equals(prop.getObject("warningTextInvalidInfo")));

        return this;
    }

    private void checkFieldRule(By by, int index) {
        WebElement field = findElements(by).get(index);
        Assert.assertTrue("Alan değer kuralı hatalı", field.getText().isEmpty());

    }

    private List<WebElement> formInput() {

        List<WebElement> inputsArray = new ArrayList<>();
        List<WebElement> inputs = findElements(FORM_INPUT);

        int[] index = {0, 1, 4, 5};
        for (int elementIndex : index) {

            inputsArray.add(inputs.get(elementIndex));
        }

        return inputsArray;
    }

    public RegisterPage checkBirthdateMaxLenght() {
        List<WebElement> inputs = findElements(FORM_INPUT);

        String maxChar = "12345";


        inputs.get(2).sendKeys(maxChar);
        inputs.get(3).sendKeys(maxChar);

        Assert.assertEquals(inputs.get(2).getAttribute(attributeValue).length(), 2);
        Assert.assertEquals(inputs.get(3).getAttribute(attributeValue).length(), 4);

        isSelectboxMonth();

        return this;

    }

    private void reopenModal() throws IOException {

        clickObjectBy(CLOSE_MODAL);
        new MainPage(driver).getRegisterPage();
    }

    private List<String> propertiesPasswordTexts() throws IOException {

        String[] propWarningText = {prop.getObject("letterCheck"), prop.getObject("numberCheck"),
                prop.getObject("lenghtCheck"), prop.getObject("specialCharacterCheck"),
                prop.getObject("upperLowerLetterCheck")};

        List<String> warningTexts = Arrays.asList(propWarningText);

        return warningTexts;

    }

    private List<WebElement> warningTexts() {

        List<WebElement> warningTexts = findElements(WARNING_TEXT);
        return warningTexts;

    }

    public RegisterPage checkUsernameCombination() throws IOException, InterruptedException {

        String character = "123";

        setObjectBy(REGISTER_USERNAME,character);
        scrollToElement(NAME);

        List<WebElement> inputWarningText = findElements(INPUT_ERROR_TEXT);

        int count =0;

        for (int i=count; i< inputWarningText.size(); i++){

            Assert.assertTrue("Uyarı mesajı görülmedi", inputWarningText.get(i).getText()
            .equals(propertiesUsernameText().get(i)));
        }

        return this;
    }

    public RegisterPage checkPasswordCombination() throws IOException, InterruptedException {

        String character = "*";
        setObjectBy(REGISTER_PASSWORD, character);

        scrollToElement(LASTNAME);

        int count = 1;

        for (int i = count; i < warningTexts().size(); i++) {

                Assert.assertEquals(warningTexts().get(i).getText(), propertiesPasswordTexts().get(i-1));
            }

        typeCorrectPassword();

        return this;
    }

    private void typeCorrectPassword() throws IOException, InterruptedException {

        String newPassword = "afgs3dsd-Ab";

        reopenModal();
        scrollToElement(LASTNAME);
        setObjectBy(REGISTER_PASSWORD, newPassword);


        try {
            Assert.assertTrue("Uyarı texti kaybolmadı", !isDisplayed(WARNING_TEXT));
        } catch (Exception exp) {
            exp.printStackTrace();
        }

    }

    private void typeRuleDate() throws IOException {

        reopenModal();

        WebElement falseDate = getElemenstBy(FORM_INPUT, 2);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + 1;
        String currentDate = Integer.toString(day);

        falseDate.sendKeys(currentDate);

    }

    private void selectRuleMonth() {

        String[] months = {"Ocak", "Şubat", "Mart", "Nisan", "Mayıs", "Haziran", "Temmuz", "Ağustos", "Eylül", "Ekim"
                , "Kasım", "Aralık"};
        List<String> monthList = Arrays.asList(months);


        for (String month : monthList) {
            if (month.equals(monthFormat.format(currentMonth))) {

                selectMonth(month);

            }
        }
    }

    private void typeYear() {

        int year = Calendar.getInstance().get(Calendar.YEAR) - 18;
        String currentYear = Integer.toString(year);

        setObjectsBy(FORM_INPUT, 3, currentYear);

    }

    public RegisterPage check18YearsRule() throws IOException {

        typeRuleDate();
        selectRuleMonth();
        typeYear();
        check18YearsMessage();

            /*
            Gün ve Ay inputların 18 yaş kuralı yok fixlendiğinde assert edilecek
             */

        return this;
    }

    private void check18YearsMessage () throws IOException {

     Assert.assertTrue("18 yaş kontrolü başarısız",getElementBy(INPUT_ERROR_TEXT).getText()
             .equals((prop.getObject("18yearsMessage"))));
    }

    private boolean isSelectboxMonth() {

        WebElement month = getElementBy(BIRTHDATE).findElement(By.tagName("select"));
        return isExistElement(MIN_WAIT_4_ELEMENT, month);
    }

    public RegisterPage checkInvalidValues() {

        String[] invalidValues = {"12345", "12345", "abcdef", "abcdef"};

        List<String> invalidValueList = Arrays.asList(invalidValues);
        int count = 0;

        for (int i = count; i < formInput().size(); i++) {

            formInput().get(i).sendKeys(invalidValueList.get(i));
            checkFieldRule(FORM_INPUT, i);

        }

        return this;

    }

    public RegisterPage checkMandatoryField () throws IOException, InterruptedException {

        String[] info = {"Asım Emre", "Sarıkaya", "20", "", "1991"
                , "17376674056", "5302214268", "emres@tuttur.com", "Automation1421", "Test654321"};
        List<String> registerInfoList = Arrays.asList(info);


        for (String registerInfo : registerInfoList) {

            scrollToElement(SUBMIT);
            clickSubmit();
            Assert.assertTrue("", getElementBy(WARNING_TEXT_ON_MODAL)
                    .getText().equals(prop.getObject("mandatoryFieldMessage")));

            if (findElements(CURSOR_FİELD).size() != 0) {

                getElementBy(CURSOR_FİELD).findElement(FORM_INPUT).sendKeys(registerInfo);

            } else if (isDisplayed(FORM_SELECT_MONTHS)) {

                selectMonth("Nisan");

            }
        }

        return this;
    }


    public RegisterPage formButtonAndInfoControl () {

        Assert.assertTrue("Üye ol butonu aktif değil",isEnabled(SUBMIT));
        Assert.assertTrue("Zaten üyeyim butonu mevcut değil",isDisplayed(ALREADY_MEMBER));

        List<WebElement> infoText = findElements(INFO_TEXT);
        for (int i=0; i < infoText.size(); i++){

            Assert.assertTrue("Bilgilendirme mesajı görülmedi", infoText.get(i).isDisplayed());
        }

        return this;
    }

    private List<String> propertiesUsernameText() throws IOException {

        String[] usernameText = {prop.getObject("minEightCharacter"),prop.getObject("minOneLetter"),
        prop.getObject("consecutiveNumber")};

        List<String> warningText = Arrays.asList(usernameText);

        return warningText;
    }



}
