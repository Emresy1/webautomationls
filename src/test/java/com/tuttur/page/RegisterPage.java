package com.tuttur.page;


import com.tuttur.configs.PropertiesFile;
import com.tuttur.constants.RegisterPage_Constants;
import static org.junit.Assert.assertTrue;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;



public class RegisterPage extends RegisterPage_Constants {

    public RegisterPage(WebDriver driver) throws IOException {
        super(driver);

    }

    Actions action = new Actions(driver);
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
        waitForElement(driver,OPT_WAIT_4_ELEMENT,DATE);
        setObjectsBy(DATE, elementIndex, day);

    }

    public void selectMonth(String month) {

        WebElement months = getElementBy(MONTHS);
        Select select = new Select(months);
        select.selectByVisibleText(month);

    }

    public void setYear(String year, int elementIndex) {

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
        scrollToElement(LASTNAME);

        int count = 0;
        int indexCount = 0;

        if (index == indexCount){

                for (int loopCount = count; loopCount < checkbox.size(); loopCount++) {

                    waitForElementClickable(driver, OPT_WAIT_4_ELEMENT, CHECKBOX);
                    checkbox.get(loopCount).click();

            }
        }

        else {

            for (int loopCount=0; loopCount < 2; loopCount++) {

                clickSubmit();
                waitForElement(driver, OPT_WAIT_4_ELEMENT, WARNING_TEXT_ON_MODAL);

                assertTrue("", getElementBy(WARNING_TEXT_ON_MODAL)
                        .getText().equals(prop.getObject("mandatoryFieldMessage")));

                waitForElementClickable(driver,MAX_WAIT_4_ELEMENT,CHECKBOX);
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


        waitForElement(driver,MAX_WAIT_4_ELEMENT,WARNING_TEXT_ON_MODAL);

        assertTrue("Uyarı mesajı yanlış", getElementBy(WARNING_TEXT_ON_MODAL).getText()
                .replace(")","").equals(text));
    }

    private WebElement modalTitle() {

        return getElementBy(MODAL_TITLE);
    }

    private void gsmPatternCheck() throws IOException {

        WebElement gsm = getElementBy(GSM);

        gsm.sendKeys(getData(4, 7));
        clickObjectBy(REGISTER_DESC);

        assertTrue("Gsm uyarı texti görülmedi", getElemenstBy(INPUT_ERROR_TEXT, 1).getText()
                .equals(prop.getObject("gsmPatternMessage")));

    }

    private void emailPatternCheck() throws IOException {

        WebElement email = getElementBy(EMAIL);

        email.sendKeys(getData(4, 8));
        clickObjectBy(REGISTER_DESC);

        assertTrue("Email uyarı texti görülmedi", getElemenstBy(INPUT_ERROR_TEXT, 2).getText()
                .equals(prop.getObject("emailPatternMessage")));

    }

    private void ssnPatternCheck() throws IOException {

        WebElement ssn = getElementBy(SSN);

        ssn.sendKeys(getData(4, 6));
        clickObjectBy(REGISTER_DESC);

        assertTrue("Tckn uyarı texti görülmedi", getElementBy(INPUT_ERROR_TEXT).getText()
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

        waitForElement(driver,MAX_WAIT_4_ELEMENT,ACTIVATION_FIELD);
        WebElement activationModal = getElementBy(ACTIVATION_FIELD);

        String smsCode = db.getValidationCode(prop.getObject("verifyCode"));
        setObjectBy(ACTIVATION_FIELD, smsCode);
        clickObjectBy(ACTIVATION_BUTTON);

        waitForElementDisappear(activationModal);

        return new WelcomePage(driver);
    }

    public RegisterPage isExistBanner() {

        isElementOnScreen(BANNER);
        return this;
    }

    public RegisterPage checkForgotPasswordModal(int rowNumber) {

        waitForElement(driver, OPT_WAIT_4_ELEMENT, BUTTON_RESET_PASSWORD);

        Assert.assertEquals(getElementBy(MODAL_TITLE).getText(), "Şifremi Unuttum");

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

        assertTrue("Uyarı texti hatalı", getElementBy(WARNING_TEXT_ON_MODAL).getText()
                .replace(")","").equals(prop.getObject("warningTextInvalidInfo")));

        return this;
    }

    private void checkFieldRule(By by, int index) {
        WebElement field = findElements(by).get(index);
        assertTrue("Alan değer kuralı hatalı", field.getText().isEmpty());

    }

    private List<WebElement> formInput() {

        List<WebElement> inputsArray = new ArrayList<>();
        List<WebElement> inputs = findElements(FORM_INPUT);

        int[] index = {0,1,2,3,4,5};
        for (int elementIndex : index) {

            inputsArray.add(inputs.get(elementIndex));
        }

        return inputsArray;
    }

    public RegisterPage checkBirthdateMaxLenght() {
        List<WebElement> inputs = findElements(FORM_INPUT);
        int[] index = {3,4};

        String maxChar = "1234567";


        inputs.get(3).sendKeys(maxChar);
        inputs.get(4).sendKeys(maxChar);


        for (int elementIndex : index){

            int inputLength = inputs.get(elementIndex).getAttribute("value").length();
            String input = Integer.toString(inputLength);

            Assert.assertEquals(input,
                    inputs.get(elementIndex).getAttribute("maxlength"));
        }



        isSelectboxMonth();

        return this;

    }

    private void reopenModal() throws IOException {

        driver.navigate().refresh();
       // new MainPage(driver).getRegisterPage();
    }

    private List<String> propertiesPasswordTexts() throws IOException {

        String[] propWarningText = {prop.getObject("lenghtCheck"),
                prop.getObject("upperLowerLetterCheck"),
                prop.getObject("specialCharacterCheck")};

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

            assertTrue("Uyarı mesajı görülmedi", inputWarningText.get(i).getText()
            .equals(propertiesUsernameText().get(i)));
        }

        return this;
    }

    public RegisterPage checkPasswordCombination() throws IOException, InterruptedException {

        String character = "*";
        setObjectBy(REGISTER_PASSWORD, character);

        scrollToElement(LASTNAME);

        int count = 1;
        int propCount =0;

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

        assertTrue(driver.findElement(PROGRESS_BAR).isDisplayed());

    }

    private void typeRuleDate() throws IOException {

        reopenModal();
        waitForElement(driver,OPT_WAIT_4_ELEMENT,MONTHS);

        WebElement dayInput = getElemenstBy(FORM_INPUT, 3);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + 1;
        String currentDate = Integer.toString(day);

        dayInput.sendKeys(currentDate);

    }

    private void selectRuleMonth() {

        String[] months = {"Ocak", "Şubat", "Mart", "Nisan", "Mayıs", "Haziran", "Temmuz", "Ağustos", "Eylül", "Ekim"
                , "Kasım", "Aralık"};
        List<String> monthList = Arrays.asList(months);


        for (String month : monthList) {
            if (month.equals(monthFormat.format(currentMonth))) {

                selectMonth(month);

                break;
            }
        }
    }

    private void typeYear() {

        WebElement yearInput = getElemenstBy(FORM_INPUT,4);

        int year = Calendar.getInstance().get(Calendar.YEAR) - 18;
        String currentYear = Integer.toString(year);

        yearInput.clear();
        yearInput.sendKeys(currentYear);

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

     assertTrue("18 yaş kontrolü başarısız",getElementBy(INPUT_ERROR_TEXT).getText()
             .equals((prop.getObject("18yearsMessage"))));
    }

    private boolean isSelectboxMonth() {

        WebElement month = getElementBy(BIRTHDATE).findElement(By.tagName("select"));
        return isExistElement(MIN_WAIT_4_ELEMENT, month);
    }


    public RegisterPage checkInvalidValues() {

        waitForElement(driver,OPT_WAIT_4_ELEMENT,FORM_INPUT);

        String[] invalidValues = {"12345","12345","abcdef","abcdef","abcdef","abcdef"};

        List<String> invalidValueList = Arrays.asList(invalidValues);
        int count = 0;

        for (int i = count; i < formInput().size(); i++) {

            formInput().get(i).sendKeys(invalidValueList.get(i));
            checkFieldRule(FORM_INPUT, i);

        }

        return this;

    }

    public RegisterPage checkMandatoryField () throws IOException, InterruptedException {

        String[] info = {"Asım Emre", "Sarıkaya", "17376674056", "20","", "1991"
                        ,"5302214268", "emres@tuttur.com", "Test654321"};

        List<String> registerInfoList = Arrays.asList(info);

        clickObjectBy(BUTTON_CLOSE_COOKİE_BAR);

        for (String registerInfo : registerInfoList) {

            scrollToElement(LASTNAME);
            clickSubmit();

            waitForElement(driver,OPT_WAIT_4_ELEMENT,WARNING_TEXT_ON_MODAL);

            assertTrue("", getElementBy(WARNING_TEXT_ON_MODAL)
                    .getText().equals(prop.getObject("mandatoryFieldMessage")));



            if (findElements(CURSOR_FİELD).size() != 0) {

                getElementBy(CURSOR_FİELD).findElement(FORM_INPUT).sendKeys(registerInfo);

            } else if (isDisplayed(FORM_SELECT_MONTHS)) {

                selectMonth("Nisan");

            }
        }

        return this;
    }
    public RegisterPage isDisplayedPlaceholders(){

        String[] placeholders = {"Ad","Soyad","T.C. Kimlik Numarası","Gün",
                             "Yıl","Cep Telefonu","E-Posta Adresi","Şifre"};
        List<String> placeholderList = Arrays.asList(placeholders);

        List<WebElement> inputPlaceholders = driver.findElements(PLACEHOLDER);

        for (int i=0; i<inputPlaceholders.size(); i++){

            Assert.assertEquals(placeholderList.get(i),inputPlaceholders.get(i).getText());
        }

        return this;
    }


    public RegisterPage formButtonAndInfoControl () {

        waitForElement(driver,OPT_WAIT_4_ELEMENT,SUBMIT);

        assertTrue("Üye ol butonu aktif değil",isEnabled(SUBMIT));
        assertTrue("Zaten üyeyim butonu mevcut değil",isDisplayed(ALREADY_MEMBER));

        List<WebElement> infoText = findElements(INFO_TEXT);

        for (int i=0; i < infoText.size(); i++){

            assertTrue("Checkbox alanları görülmedi", infoText.get(i).isDisplayed());
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
