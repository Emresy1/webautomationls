package com.tuttur.base;


import com.tuttur.configs.PropertiesFile;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.concurrent.TimeUnit;

public class BaseTest {

	public static String path = "C:\\Users\\Deniz Caliskan\\eclipse-workspace\\TutturWeb\\configs\\Configurations.properties";
	public static String baseUrl = "https://ttest:q26RwfyLotHm@alpha1.tuttur.com";

	//https://ttest:q26RwfyLotHm@alpha.tuttur.com
	   public static final String USERNAME = "emresarkaya_DBS1ib";
	   public static final String ACCESS_KEY = "x1ZVYdxWwVSuDpnsbtRY";
	   public static final String KEY = USERNAME + ":" + ACCESS_KEY;
	   public static final String URL = "http://127.0.0.1";

	public WebDriver driver;

	PropertiesFile prop = new PropertiesFile(driver);

	public static final String testDataExcelFileName = "TestData.xlsx";

	public static String browserName = null;


	@Before
	public void setUp() throws Exception {


		 String path = System.getProperty("user.dir");
		 prop.getProperties();


		DesiredCapabilities capabilities = new DesiredCapabilities();

		if (StringUtils.isEmpty(System.getProperty("key"))) {

			if (browserName.equalsIgnoreCase("chrome")) {

				System.setProperty("webdriver.chrome.driver", "properties/driver/chromedriver");
				ChromeOptions options = new ChromeOptions();
				options.merge(capabilities);
				driver = new ChromeDriver(options);
			}

			else if (browserName.equalsIgnoreCase("firefox")) {


				System.setProperty("webdriver.gecko.driver", "properties/driver/geckodriver");
				FirefoxOptions options = new FirefoxOptions();
				options.merge(capabilities);
				driver = new FirefoxDriver();
			}



		} else {



		}



		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver = new RemoteWebDriver(new URL(URL), capabilities);
		driver.get(baseUrl);


	}


	@After
	public void tearDown() throws Exception {


		driver.quit();

	}

}
