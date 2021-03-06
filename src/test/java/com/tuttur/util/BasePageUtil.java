package com.tuttur.util;


import io.appium.java_client.AppiumDriver;

import io.appium.java_client.AppiumFluentWait;
import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class BasePageUtil {

	public WebDriver driver;
	public AppiumDriver appiumDriver;



	public int amount;


	public BasePageUtil(WebDriver driver) {

		this.driver = driver;

	}

	public BasePageUtil(AppiumDriver appiumDriver){
		this.appiumDriver = appiumDriver;
	}


	Random random = new Random();

    public static final int DEFAULT_WAIT_4_ELEMENT = 10;
    public static final int OPT_WAIT_4_ELEMENT = 5;
    public static final int MIN_WAIT_4_ELEMENT = 2;
    public static final int MAX_WAIT_4_ELEMENT = 20;
    public static final int RESEND_CODE_WAIT = 181;


    public boolean isDisplayed ( By by) {

    	return getElementBy(by).isDisplayed();

	}

	public boolean isEnabled (By by) {

    	return getElementBy(by).isEnabled();

	}

    public String getAttribute (By by,String attributeName) {

    	return driver.findElement(by).getAttribute(attributeName);
	}

	
	public WebElement getElementBy(By by) {

		return driver.findElement(by);
	}
	
	public WebElement getElemenstBy(By by, int i) {

		return driver.findElements(by).get(i);
	}

	public void waitForElementDisappear(WebElement element){

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(element));

	}
	public int elementSize(By by){

    	return driver.findElements(by).size();
	}

	// Uygulamalarda belirlenen element ekranda görülene dek bekler.
	public void waitForPresenceOfBy(AppiumDriver appiumDriver, By by){

		WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}


	public void waitForInvisibility(WebElement webElement, int maxSeconds) {

		Long startTime = System.currentTimeMillis();

		try {
			while (System.currentTimeMillis() - startTime < maxSeconds * 1000 && webElement.isDisplayed()) {}
		} catch (StaleElementReferenceException e) {
			return;
		}
	}

	public String getData (int rowNumber , int cellNumber) {

		return ExcelUtil.getCellData(rowNumber, cellNumber);

	}

	public void  setData (String value , int rowNumber,int cellNumber) {

    	 ExcelUtil.setCellData(value,rowNumber,cellNumber);
	}


	public void getSheet ( String sheetName) {
		ExcelUtil.setExcelFileSheet(sheetName);
	}

	public void setObjectBy(By by, String value) {
		WebElement element = getElementBy(by);
		element.clear();
		element.sendKeys(value);
	}

	public void setObjectsBy(By by, int index ,String value ) {

		WebElement element = getElemenstBy(by,index);
		element.sendKeys(value);

	}

	
	public boolean isElementOnScreen(By by) {
		
		WebElement elm = getElementBy(by);
		if (elm.isDisplayed()) {
			return true;
		}
		else {
			return false;
		}
	}

	public void clickObjectBy(By by) {

		WebElement element = driver.findElement(by);
		waitForElement(element,OPT_WAIT_4_ELEMENT);
		element.click();

	}

    public void clickObjectsBy ( By by,int i) {

		WebElement element = driver.findElements(by).get(i);
		waitForElement(element,OPT_WAIT_4_ELEMENT);
		element.click();

	}

	public List <WebElement> findElements (By by) {

		List <WebElement> elements = driver.findElements(by);
		return elements;

	}

	public void javaScriptClicker(WebDriver driver, WebElement element) {

		JavascriptExecutor jse = ((JavascriptExecutor) driver);
		jse.executeScript("var evt = document.createEvent('MouseEvents');"
				+ "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
				+ "arguments[0].dispatchEvent(evt);", element);
	}
	
	public void scrollToElement(By by) throws InterruptedException {
		WebElement element = driver.findElement(by);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
	}

	public void scrollToElements (By by,int i) throws InterruptedException {

    	WebElement element = driver.findElements(by).get(i);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
	}

	
	public void waitForElement(WebDriver driver, int seconds, By elementBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000));
		wait.until(ExpectedConditions.presenceOfElementLocated(elementBy));
	}
	public void waitForElementClickable(WebDriver driver, int seconds, By elementBy){

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000));
		wait.until(ExpectedConditions.elementToBeClickable(elementBy));
	}

	public void waitForPageLoad(){

		ExpectedCondition<Boolean> pageLoadCondition = new
				ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver driver) {
						return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
					}
				};
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(pageLoadCondition);

	}


	public void waitForElement(WebElement elm , int seconds) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(seconds));
		wait.until(ExpectedConditions.elementToBeClickable(elm));
	}
	
	public void waitForTextOnElement(WebDriver driver, int seconds, By by, String value) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(by, value));
	}
	
	public void switchToWindows() {
		
		Set<String> allWindows = driver.getWindowHandles();
		for(String currentWindow : allWindows){
		    driver.switchTo().window(currentWindow);

		}
	}

	public void selectRandomBirthdayElements(By by, int i) {
		
		WebElement dropdownDates = getElemenstBy(by, i);
		Select dates = new Select(dropdownDates);
		List<WebElement> dateList = dates.getOptions();
		int dateObj = dateList.size();
		int dateSelect = random.nextInt(dateObj);
		dates.selectByIndex(dateSelect);
		System.out.println(dropdownDates.getAttribute("value"));
		
	}
	
	public void selectRandomBirthdayElement(By by) {
		
		WebElement dropdownDates = getElementBy(by);
		Select dates = new Select(dropdownDates);
		List<WebElement> dateList = dates.getOptions();
		int dateObj = dateList.size();
		int dateSelect = random.nextInt(dateObj);
		dates.selectByIndex(dateSelect);
		System.out.println(dropdownDates.getAttribute("value"));
	}

	public void selectDropdownElementIndex(WebElement elm, int i) {

		Select select = new Select(elm);
		select.selectByIndex(i);
	}



     public void selectDropdownElementValue(WebElement elm) {

		Select select = new Select(elm);
		String value = select.getOptions().get(1).getText();
		select.selectByValue(value);

	}
	
	public boolean isExist(int seconds, By by) {
		try {
			waitForElement(driver, seconds, by);
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
      }
	
	public boolean isExistElement (int seconds, WebElement elm) {
		
		try {
			waitForElement(elm, seconds);
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}	
	}

	
	public void sleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


    public static int generateRandomInt(int upperRange){
    	
      Random random = new Random();
    return random.nextInt(upperRange);
      }


	public void ssnSendKeysJs(By by , String ssnNo){
		JavascriptExecutor jse = ((JavascriptExecutor)driver);
		WebElement ssn = driver.findElement(by);
		jse.executeScript("arguments[0].value="+ssnNo+";", ssn);
	}

	public String elementText (By by, By by2) {

    	return driver.findElement(by).findElement(by2).getText();

	}

    }