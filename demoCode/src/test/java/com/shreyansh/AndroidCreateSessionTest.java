package com.shreyansh;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;

public class AndroidCreateSessionTest extends BaseTest {
    private AndroidDriver<WebElement> driver;

    @BeforeClass
    public void setUp() throws Exception {
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "../apps");
        File app = new File(appDir.getCanonicalPath(), "app-tech-android-challenge-20180918.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("app", app.getAbsolutePath());
        driver = new AndroidDriver<WebElement>(getServiceUrl(), capabilities);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test()
    public void testCreateSession() {
        String activity = driver.currentActivity();
        String pkg = driver.getCurrentPackage();
        System.out.println(activity +"----"+ pkg);
        Assert.assertEquals(activity, ".app.MainActivity");
        Assert.assertEquals(pkg, "com.lalamove.techchallenge");
    }
}