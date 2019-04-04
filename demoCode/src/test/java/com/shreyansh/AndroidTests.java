package com.shreyansh;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AndroidTests extends BaseTest {
    private AndroidDriver<WebElement> driver;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "../apps");
        File app = new File(appDir.getCanonicalPath(), "app-tech-android-challenge-20180918.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("app", app.getAbsolutePath());
        driver = new AndroidDriver<WebElement>(getServiceUrl(), capabilities);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @Test()
    public void checkApplicationAndroid() {
        String activity = driver.currentActivity();
        String pkg = driver.getCurrentPackage();
        System.out.println(activity + "----" + pkg);
        Assert.assertEquals(activity, ".app.MainActivity");
        Assert.assertEquals(pkg, "com.lalamove.techchallenge");
    }

    @Test
    public void checkNavigationBarAndroid() {
        AndroidElement applicationElement = (AndroidElement) driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView"));
        String navigationBar = applicationElement.getText();
        Assert.assertEquals(navigationBar, "Delivery List");
    }

    @Test
    public void countInitialDeliveriesAndroid() {
        List<WebElement> windowElements = driver.findElementsById("com.lalamove.techchallenge:id/simpleDraweeView");
        Assert.assertTrue(windowElements.size() == 20);
    }

    @Test
    public void clickFirstCellAndroid() {
        List<WebElement> windowElements = driver.findElementsById("com.lalamove.techchallenge:id/textView_description");
        System.out.println("---------"+windowElements.get(0).getText()+"----------");
        windowElements.get(0).click();

    }

    //@Test
    public void checkTransitionOnClickingCellAndroid() {
        List<WebElement> windowElements = driver.findElementsById("com.lalamove.techchallenge:id/textView_description");
        System.out.println("---------"+windowElements.get(0).getText()+"----------");
        windowElements.get(0).click();

        // TODO Refactor below for Android

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

        // TODO Refactor above for Android
    }

    @Test
    public void checkRefreshAndroid() {
        List<WebElement> windowElements = driver.findElementsById("com.lalamove.techchallenge:id/textView_description");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "down");
        js.executeScript("mobile: swipe", params);
        List<WebElement> windowScrolledElements = driver.findElementsById("com.lalamove.techchallenge:id/textView_description");
        Assert.assertTrue(windowScrolledElements.size() == 20);
    }

    @Test
    public void checkScrollAndroid() {
        List<WebElement> windowElements = driver.findElementsById("com.lalamove.techchallenge:id/textView_description");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "down");
        params.put("element", windowElements.get(19));
        js.executeScript("mobile: scroll", params);
        List<WebElement> windowScrolledElements = driver.findElementsById("com.lalamove.techchallenge:id/textView_description");
        Assert.assertTrue(windowScrolledElements.size() == 40);
    }



}



