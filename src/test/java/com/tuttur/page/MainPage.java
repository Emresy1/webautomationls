package com.tuttur.page;


import com.tuttur.configs.PropertiesFile;
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
    public RegisterPage getRegisterPage() throws IOException {
        db.executeQuery(prop.getObject("accountUpdate"));
        clickObjectBy(REGISTER_BUTTON);
        return new RegisterPage(driver);
    }

    public RegisterPage getRegisterPageNotUpdate () throws IOException {

        clickObjectBy(REGISTER_BUTTON);
        return new RegisterPage(driver);

    }

    public MainPage checkAccountNo() throws IOException {

        Assert.assertTrue("Account numarası doğru değil", getElementBy(ACCOUNT_NO).getText().equals(prop.getObject("account_no")));
        System.out.println("Account numarası ile login, başarılı şekilde geçti");
        return this;
    }

    public MainPage checkUsernameText(String username) throws IOException {

        String headerUser = username;
        Assert.assertTrue("Kullanıcı adı doğru değil", getElementBy(USERNAMETEXT).getText().equals(headerUser));

        return this;
    }

    public MainPage checkRegisterLogin () throws InterruptedException, IOException {
        Assert.assertTrue("register sonrası login başarısız",getElementBy(ACCOUNT_NO).getText().equals(prop.getObject("currentMemberNo")));
        return this;
    }


}
	
	
