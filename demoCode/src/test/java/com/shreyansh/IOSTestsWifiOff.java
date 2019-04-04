package com.shreyansh;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.mobile.NetworkConnection;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IOSTestsWifiOff extends BaseTest {
    private IOSDriver<WebElement> driver;
    static AppiumDriver driver2;

    @BeforeSuite(alwaysRun=true)
    public void setUp() throws Exception {
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "../apps");
        File app = new File(appDir.getCanonicalPath(), "app-tech-ios-challenge-simulator-20190125.zip");

        String deviceName = System.getenv("IOS_DEVICE_NAME");
        String platformVersion = System.getenv("IOS_PLATFORM_VERSION");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", deviceName == null ? "iPhone 6s" : deviceName);
        capabilities.setCapability("platformVersion", platformVersion == null ? "12.0" : platformVersion);
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.apple.Preferences");
        driver = new IOSDriver<WebElement>(getServiceUrl(), capabilities);
    }




    @AfterSuite(alwaysRun=true)
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void checkRefreshwithWifiOff() {
        enableWifi(false);
        List<WebElement> windowElements = driver.findElementsByClassName("XCUIElementTypeCell");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "down");
        js.executeScript("mobile: swipe", params);
        List<WebElement> windowScrolledElements = driver.findElementsByClassName("XCUIElementTypeCell");
        Assert.assertTrue(windowScrolledElements.size() == 0);
    }


    public static void wifiOff() {
        NetworkConnection mobileDriver = (NetworkConnection) driver2;
        if (mobileDriver.getNetworkConnection() != NetworkConnection.ConnectionType.AIRPLANE_MODE) {
            // enabling Airplane mode
            mobileDriver.setNetworkConnection(NetworkConnection.ConnectionType.AIRPLANE_MODE);
        }
    }

    //Element's info
     By wifiTab = MobileBy.xpath("//XCUIElementTypeCell[@visible='true' and @name='Wi-Fi']");
     By wifiBtn = MobileBy.xpath("//XCUIElementTypeSwitch[@visible='true' and @name='Wi-Fi']");
    public  void enableWifi(boolean expectedWifiStatus) {
        driver.findElement(wifiTab).click(); // To open Settings > Wi-fi
        if (isWifiEnabled() != expectedWifiStatus) {
            driver.findElement(wifiBtn).click(); // To switch Wi-fi
        }
    }

    public  boolean isWifiEnabled() {
        return driver.findElement(wifiBtn).getAttribute("value").equals("1");
    }
}
