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
import org.testng.annotations.*;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


public class BaseTest {

	public static String baseUrl = "https://ttest:q26RwfyLotHm@alpha.tuttur.com";

	   public static final String USERNAME = "emresarkaya_DBS1ib";
	   public static final String ACCESS_KEY = "x1ZVYdxWwVSuDpnsbtRY";
	   public static final String KEY = USERNAME + ":" + ACCESS_KEY;

	public WebDriver driver;

	public static final String testDataExcelFileName = "TestData.xlsx";
	public static String browserName = null;




	@BeforeTest
	public void setUp() throws Exception {

		PropertiesFile prop = new PropertiesFile();


		 String path = System.getProperty("user.dir");
		 prop.getProperties();

		DesiredCapabilities capabilities = new DesiredCapabilities();
		URL serverurl = new URL("http://127.0.0.1:9515");

		if (StringUtils.isEmpty(System.getProperty("key"))) {

			if (Platform.getCurrent().toString().equalsIgnoreCase("MAC")) {

				if (browserName.equalsIgnoreCase("chrome")) {

					HashMap<String, Object> chromePrefs = new HashMap<String, Object>();

					System.setProperty("webdriver.chrome.driver", "properties/driver/chromedriver");
					ChromeOptions options = new ChromeOptions();
					chromePrefs.put("profile.default_content_settings.popups", 0);
					chromePrefs.put("download.default_directory", System.getProperty("user.home"));
					options.merge(capabilities);
					options.setExperimentalOption("prefs", chromePrefs);
					driver = new ChromeDriver();


				} else if (browserName.equalsIgnoreCase("firefox")) {

					System.setProperty("webdriver.gecko.driver", "properties/driver/geckodriver");
					FirefoxOptions options = new FirefoxOptions();
					options.merge(capabilities);
					driver = new FirefoxDriver();

				} else if (browserName.equalsIgnoreCase("mobilebrowser")) {

					System.setProperty("webdriver.chrome.driver", "properties/driver/chromedriver");

					ChromeOptions chromeOptions = new ChromeOptions();
					chromeOptions.merge(capabilities);

					Map<String, Object> deviceMetrics = new HashMap<>();
					deviceMetrics.put("width", 1078);
					deviceMetrics.put("height", 924);
					deviceMetrics.put("pixelRatio", 3.0);
					Map<String, Object> mobileEmulation = new HashMap<>();
					mobileEmulation.put("deviceMetrics", deviceMetrics);
					mobileEmulation.put("userAgent",
							        "Mozilla/5.0 (Linux; Android 8.0.0;" +
									"Pixel 2 XL Build/OPD1.170816.004) " +
									"AppleWebKit/537.36 (KHTML,like Gecko) " +
					                "Chrome/67.0.3396.99 Mobile Safari/537.36");

					chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
					driver = new ChromeDriver(chromeOptions);


				}
			}

			else{

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

			}
		}


		//driver = new RemoteWebDriver(serverurl,capabilities);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(baseUrl);

	}

	@AfterTest
	public void tearDown() {

		driver.quit();

	}

}
