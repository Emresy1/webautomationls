package com.tuttur.base.web;


import com.tuttur.configs.PropertiesFile;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


public class BasePage {

	public static String baseUrl = "https://ttest:q26RwfyLotHm@alpha.tuttur.com";

	public static final String USERNAME = "emresarkaya_DBS1ib";
	public static final String ACCESS_KEY = "x1ZVYdxWwVSuDpnsbtRY";
	public static final String KEY = USERNAME + ":" + ACCESS_KEY;

	public WebDriver driver;
	public String seleniumServerUrl = "http://127.0.0.1:4444";
	public ChromeOptions options;

	public static final String testDataExcelFileName = "TestData.xlsx";
	public static String runPlatform = null;
	//Platform.getCurrent().toString().equalsIgnoreCase("MAC");
	

	@BeforeTest
	public void setUp() throws Exception{
		URL serverUrl = new URL(seleniumServerUrl);
		PropertiesFile prop = new PropertiesFile();

		 //String path = System.getProperty("user.dir");
		 prop.getProperties();

		//DesiredCapabilities capabilities = new DesiredCapabilities();
		
		System.out.print("pplaatformmmmmmmmmmmmm: "+runPlatform);
		if (runPlatform.equalsIgnoreCase("web")) {

			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();

			//System.setProperty("webdriver.chrome.driver", "properties/driver/chromedriver");
			options = new ChromeOptions();
			//chromePrefs.put("profile.default_content_settings.popups", 0);
			//chromePrefs.put("download.default_directory", System.getProperty("user.home"));
			//options.merge(capabilities);
			//options.setExperimentalOption("prefs", chromePrefs);
			//driver = new ChromeDriver();

		} else if (runPlatform.equalsIgnoreCase("mweb")) {

			System.setProperty("webdriver.chrome.driver", "properties/driver/chromedriver");

			options = new ChromeOptions();
			//chromeOptions.merge(capabilities);

			Map<String, Object> deviceMetrics = new HashMap<>();
			deviceMetrics.put("height", 812);
			deviceMetrics.put("width", 375);
			deviceMetrics.put("pixelRatio", 3.0);
			Map<String, Object> mobileEmulation = new HashMap<>();
			mobileEmulation.put("deviceMetrics", deviceMetrics);
			mobileEmulation.put("userAgent",
							"Mozilla/5.0 (Linux; Android 8.0.0;" +
							"Pixel 2 XL Build/OPD1.170816.004) " +
							"AppleWebKit/537.36 (KHTML,like Gecko) " +
							"Chrome/100.0.4896.60 Mobile Safari/537.36");

			options.setExperimentalOption("mobileEmulation", mobileEmulation);
			//driver = new ChromeDriver(chromeOptions);
		}
		else{
		/*
		String downloadFilepath = System.getProperty("user.dir");
		HashMap<String, Object> chromePrefs = new HashMap<>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);

		ChromeOptions options = new ChromeOptions();
		System.setProperty("webdriver.chrome.driver", "properties/driver/linux");
		options.addArguments("--no-sandbox");
		options.addArguments("--headless");
		options.addArguments("disable-gpu");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--window-size=1920x1080");
		options.merge(capabilities);
		driver = new ChromeDriver(options);
		Point point = new Point(-1000, 0);
		*/
		System.out.print("Unknown parameters");
		}
		//driver = new ChromeDriver(options);
		driver = new RemoteWebDriver(serverUrl,options);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(baseUrl);
	}

	@AfterTest
	public void tearDown() {

		driver.quit();

	}

}
