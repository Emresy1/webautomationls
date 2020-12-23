package com.tuttur.util;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class BasePageUtil {

	protected WebDriver driver;

	
	public BasePageUtil(WebDriver driver) {

		this.driver = driver;
	}
	Random random = new Random();
	
	
    public static final int DEFAULT_WAIT_4_ELEMENT = 10;
    public static final int OPT_WAIT_4_ELEMENT = 5;
    public static final int MIN_WAIT_4_ELEMENT = 2;
    public static final int RESEND_CODE_WAIT = 181;
	
	public WebElement getElementBy(By by) {
		return driver.findElement(by);
	}
	
	public WebElement getElemenstBy(By by, int i) {
		return driver.findElements(by).get(i);
	}

	public void waitForElementDisappear(WebElement element){
		WebDriverWait wait = new WebDriverWait(driver,DEFAULT_WAIT_4_ELEMENT);
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	
	public WebElement setObjectBy(By by, String value) {
		WebElement element = getElementBy(by);
		element.clear();
		element.sendKeys(value);
		return element;
	}

	public void assertDisplayed(By element1, By element2, By element3){

		WebElement elm1 = getElementBy(element1);
		WebElement elm2 = getElementBy(element2);
		WebElement elm3 = getElementBy(element3);
		ArrayList<WebElement> elements = new ArrayList<WebElement>();
		elements.add(elm1);
		elements.add(elm2);
		elements.add(elm3);

		int loopCount = 3;
		for (int i=0; i<loopCount; i++){

			Assert.assertTrue("Element görülmedi.", elements.get(i).isDisplayed());
		}

	}

	public WebElement setObjectsBy(By by, int index ,String value ) {
		WebElement element = getElemenstBy(by,index);
		element.sendKeys(value);
		return element;
	}
	public void assertElementsIsEmpty(By element1, By element2, By element3){

		ArrayList<WebElement> elements = new ArrayList<WebElement>();
		int size = 4;

		for (int i=0; i < size; i++){

			List<WebElement> elm1 = driver.findElements(element1);
			List<WebElement> elm2 = driver.findElements(element2);
			List<WebElement> elm3 = driver.findElements(element3);
			elements.add(elm1.get(i));
			elements.add(elm2.get(i));
			elements.add(elm3.get(i));
		}

		int loopCount = 3;
		for (int i=0; i<elements.size(); i++){
			System.out.println("elements : "+ elements.get(i).getText());

			Assert.assertTrue("Element görülmedi.", !elements.get(i).getText().isEmpty());
		}
	}
	public void assertElementIsEmpty(By element1, By element2, By element3){

		ArrayList<WebElement> elements = new ArrayList<WebElement>();
		int size = 4;
		for (int i=0; i < size; i++){
			WebElement elm1 = getElementBy(element1);
			WebElement elm2 = getElementBy(element2);
			WebElement elm3 = getElementBy(element3);
			elements.add(elm1);
			elements.add(elm2);
			elements.add(elm3);
		}


		int loopCount = 3;
		for (int i=0; i<elements.size(); i++){

			Assert.assertTrue("Element görülmedi.", !elements.get(i).getText().isEmpty());
		}
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



	public WebElement clickObjectBy(By by) {
		WebElement element = driver.findElement(by);
		waitForElement(element,OPT_WAIT_4_ELEMENT);
		element.click();
		return element;
	}
    public WebElement clickObjectsBy ( By by,int i) {
		WebElement element = driver.findElements(by).get(i);
		waitForElement(element,OPT_WAIT_4_ELEMENT);
		element.click();
		return element;
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
	
	public void waitForElement(WebDriver driver, int seconds, By elementBy) {
		WebDriverWait wait = new WebDriverWait(driver, seconds, 1000);
		wait.until(ExpectedConditions.elementToBeClickable(elementBy));
	}
	public void waitForString(WebDriver driver, int seconds, By by, String st) {
		WebDriverWait wait = new WebDriverWait(driver, seconds, 1000);
		WebElement element = getElementBy(by);
		wait.until(ExpectedConditions.textToBePresentInElement(element,st));
	}
	public void waitForElement(WebElement elm , int seconds) {
		
		WebDriverWait wait = new WebDriverWait(driver, seconds, 1000);
		wait.until(ExpectedConditions.elementToBeClickable(elm));
	}
	
	public void waitForText(WebDriver driver, int seconds, By by, String value) {
		
		WebElement element = getElementBy(by);
		WebDriverWait wait = new WebDriverWait(driver, seconds, 1000);
		wait.until(ExpectedConditions.textToBePresentInElement(element, value));
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
	
    }