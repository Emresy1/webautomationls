package com.tuttur.page;


import com.tuttur.configs.PropertiesFile;

import com.tuttur.util.ExcelUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.tuttur.constants.MainPage_Constants;
import org.openqa.selenium.WebElement;


import java.io.IOException;
import java.util.List;


public class MainPage extends MainPage_Constants {

    public MainPage(WebDriver driver) throws IOException {
        super(driver);
    }

    PropertiesFile prop = new PropertiesFile(driver);
    DbQueriesPage db = new DbQueriesPage(driver);
    GeneralPage general = new GeneralPage(driver);

    public void dropdownMenu(String menu) {
        List<WebElement> dropdown = driver.findElements(DROPDOWN_MENU);

        for (WebElement element : dropdown) {
            String menuItem = element.findElement(By.tagName("a")).getText();

            if (menuItem.contains(menu)) {
                element.click();
            }
        }
    }

    public LoginPage getLoginPage() {
        clickObjectBy(BUTTON_LOGIN);
        return new LoginPage(driver);
    }

    public MainPage logout() {
        clickObjectBy(AVATAR);
        dropdownMenu("Çıkış");
        return this;
    }

    public MainPage accountUpdate() throws IOException {

        db.executeQuery(prop.getObject("accountUpdate"));

        return this;
    }

    public RegisterPage getRegisterPage() throws IOException {

        clickObjectBy(REGISTER_BUTTON);

        return new RegisterPage(driver);
    }

    public RegisterPage getRegisterPageNotUpdate () throws IOException {

        clickObjectBy(REGISTER_BUTTON);
        return new RegisterPage(driver);

    }

    public MainPage checkAccountNo( int rowNumber) throws IOException {

        assertTrue(getData(rowNumber,7),getElementBy(ACCOUNT_NO).getText()
                .equals(getData(rowNumber,3)));
        return this;
    }

    public MainPage checkUsernameText( int rowNumber) throws IOException {


        assertTrue(getData(rowNumber,7), getElementBy(USERNAMETEXT).getText()
                .equals(getData(rowNumber,3)));

        return this;
    }
    public MainPage checkUserText(String username){

        String headerUser = username;
        Assert.assertTrue("Kullanıcı adı görülmedi", getElementBy(USERNAMETEXT).getText()
        .equals(headerUser));

        return this;
    }

    public MainPage checkRegisterLogin () throws InterruptedException, IOException {
        assertTrue(getData(5,10), getElementBy(ACCOUNT_NO).getText().equals(getData(5,12)));
        return this;
    }

}
	
	
