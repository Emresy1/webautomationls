package com.tuttur.base.app;


import com.tuttur.util.BasePageUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest  {

    public AppiumDriver appiumDriver;
    String path = System.getProperty("user.dir");




    @BeforeTest
    public void setUp() throws MalformedURLException {

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        //desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.3");
        //desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Simulator");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.APP, path + "/properties/driver/tutturApk.apk");
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        desiredCapabilities.setCapability("appPackage", "com.tuttur.tuttur_mobile_android");
        desiredCapabilities.setCapability("appActivity", "com.tuttur.tuttur_mobile_android.SplashActivity");

        URL url = new URL("http://localhost:4723/wd/hub");

        appiumDriver = new AppiumDriver(url,desiredCapabilities);
        new BasePageUtil(appiumDriver).waitForPresenceOfBy(appiumDriver,By.id("com.tuttur.tuttur_mobile_android:id/Login"));

       // appiumDriver = new AndroidDriver(url, desiredCapabilities);
    }
}
