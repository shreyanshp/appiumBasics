package com.shreyansh;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

public class IOSTests extends BaseTest {
    private IOSDriver<WebElement> driver;

    @BeforeSuite
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
        driver = new IOSDriver<WebElement>(getServiceUrl(), capabilities);
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void checkApplication () {
        IOSElement applicationElement = (IOSElement) driver.findElementByClassName("XCUIElementTypeApplication");
        String applicationName = applicationElement.getAttribute("name");
        Assert.assertEquals(applicationName, "LalaTest");
        applicationElement = (IOSElement) driver.findElementByClassName("XCUIElementTypeNavigationBar");
        String navigationBar = applicationElement.getAttribute("name");
        Assert.assertEquals(navigationBar, "Delivery List");
    }

    @Test
    public void checkNavigationBar () {
        IOSElement applicationElement = (IOSElement) driver.findElementByClassName("XCUIElementTypeNavigationBar");
        String navigationBar = applicationElement.getAttribute("name");
        Assert.assertEquals(navigationBar, "Delivery List");
    }

    @Test
    public void checkDeliveryTable () {
        IOSElement applicationElement = (IOSElement) driver.findElementByClassName("XCUIElementTypeTable");
        String deliveryTable = applicationElement.getAttribute("name");
        Assert.assertEquals(deliveryTable, "DeliveriesTableView");
    }

    @Test
    public void countInitialDeliveries () {
        List<WebElement> windowElements = driver.findElementsByClassName("XCUIElementTypeCell");
        Assert.assertTrue(windowElements.size() == 20);
    }

    @Test
    public void clickFirstCell () {
        List<WebElement> windowElements = driver.findElementsByClassName("XCUIElementTypeCell");
        windowElements.get(0).click();

    }

    @Test
    public void clickCell2 () {
        List<WebElement> windowElements = driver.findElementsByClassName("XCUIElementTypeCell");
        System.out.println("---------"+windowElements.get(0).getText()+"----------");
        windowElements.get(0).click();

        IOSElement navigationBar = (IOSElement) new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(MobileBy.className("XCUIElementTypeNavigationBar")));
        Assert.assertEquals(navigationBar.getAttribute("name"), "Delivery Detail");

        String buttonElementId = "DeliveryCellPhoto";
        IOSElement buttonElement = (IOSElement) new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(MobileBy.AccessibilityId(buttonElementId)));
        buttonElement.click();

        IOSElement applicationElement = (IOSElement) driver.findElementByAccessibilityId("Back");
        applicationElement.click();

        IOSElement backPageElements = (IOSElement) new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(MobileBy.className("XCUIElementTypeNavigationBar")));
        Assert.assertEquals(backPageElements.getAttribute("name"), "Delivery List");

    }

}
