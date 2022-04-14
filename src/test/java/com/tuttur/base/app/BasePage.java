package com.tuttur.base.app;

import com.tuttur.configs.PropertiesFile;
import com.tuttur.util.BasePageUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import java.net.MalformedURLException;
import java.net.URL;

public class BasePage  {

    public AppiumDriver appiumDriver;
    String path = System.getProperty("user.dir");
    public static String runPlatform = null;
    public String appiumServerUrl = "http://localhost:4723/wd/hub";
    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();


    @BeforeTest
    public void setUp() throws MalformedURLException {
		URL serverUrl = new URL(appiumServerUrl);
		PropertiesFile prop = new PropertiesFile();
        prop.getProperties();

        if (runPlatform.equalsIgnoreCase("android")) {
    
            //desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.3");
            //desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Simulator");
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
            desiredCapabilities.setCapability(MobileCapabilityType.APP, path + "/properties/driver/tutturApk.apk");
            //desiredCapabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
            //desiredCapabilities.setCapability("appPackage", "com.tuttur.tuttur_mobile_android");
            //desiredCapabilities.setCapability("appActivity", "com.tuttur.tuttur_mobile_android.SplashActivity");
        }
        else if(runPlatform.equalsIgnoreCase("ios")) {
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
            desiredCapabilities.setCapability(MobileCapabilityType.APP, path + "/properties/driver/tutturApk.ipa");
            desiredCapabilities.setCapability(MobileCapabilityType.UDID, "auto");

        }
        appiumDriver = new AppiumDriver(serverUrl,desiredCapabilities);
        //new BasePageUtil(appiumDriver).waitForPresenceOfBy(appiumDriver,By.id("com.tuttur.tuttur_mobile_android:id/Login"));

       // appiumDriver = new AndroidDriver(url, desiredCapabilities);
    }
}
