package com.qa.base;
import com.aventstack.extentreports.Status;
import com.qa.reports.ExtentReport;
import com.qa.utils.ConfigReader;
import com.qa.utils.Utilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

import static com.qa.utils.Utilities.WAIT;

public class AppFactory {
    public static AppiumDriver driver;

    public static ConfigReader configReader;

    protected HashMap<String, String> stringHashMap = new HashMap<>();

    InputStream stringsIs;

    public Utilities utilities = new Utilities();

    static Logger log = LogManager.getLogger(AppFactory.class.getName());

    public static String dateTime;
    private static AppiumDriverLocalService server;

    @BeforeSuite
    public void upAndRunningAppiumServer() {
        server = getAppiumServerDefault();
        if (!utilities.checkIfAppiumServerIsRunning(4723)) {
            server.start();
            server.clearOutPutStreams();
        } else {
            utilities.log().info("Appium Server is already up and running");
        }
    }

    @AfterSuite
    public void shutdownServer() {
        server.stop();
    }

    public AppiumDriverLocalService getAppiumServerDefault() {
        return AppiumDriverLocalService.buildDefaultService();
    }

    @BeforeTest
    @Parameters({"platformName", "platformVersion", "deviceName"})
    public void initializer(String platformName, String platformVersion, String deviceName) throws IOException, ParserConfigurationException, SAXException {
        try {
            configReader = new ConfigReader();
            String xmlFileName = "strings/strings.xml";
            stringsIs = getClass().getClassLoader().getResourceAsStream(xmlFileName);
            stringHashMap = utilities.parseStringXML(stringsIs);  // parse func will return data in key value pair
            dateTime = utilities.getDateTime();

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("appium:platformName", "Android");
            capabilities.setCapability("appium:platformVersion", "15");
            capabilities.setCapability("appium:deviceName", "emulator-5554");
            capabilities.setCapability("appium:automationName", configReader.getAutomationName());
            capabilities.setCapability("appium:app", System.getProperty("user.dir") + configReader.getApkPath());
            capabilities.setCapability("appium:appPackage", configReader.getAppPackage());
            capabilities.setCapability("appium:appActivity", configReader.getAppActivity());
            capabilities.setCapability("appium:noReset", configReader.getNoReset());  /* cache not maintained */

            driver = new AndroidDriver(new URL(configReader.getAppiumServerEndpointURL()), capabilities);
            utilities.log().info("AppURL is {}", configReader.getAppiumServerEndpointURL());
            AppDriver.setDriver(driver);
            AppDriver.setDeviceName(deviceName);
            AppDriver.setPlatformName(platformName);
            utilities.log().info("Starting The Appium Server");
        }
        catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

    public void waitForElementVisibility(WebElement element){
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(WAIT));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickElement(WebElement element, String msg){
        this.waitForElementVisibility(element);
        utilities.log().info(msg);
        ExtentReport.getTest().log(Status.INFO,msg);
        element.click();
    }

    public void sendKeys(WebElement element,String text,String msg){
        this.waitForElementVisibility(element);
        utilities.log().info(msg);
        ExtentReport.getTest().log(Status.INFO,msg);
        element.sendKeys(text);
    }

    public String getAttribute(WebElement element,String attribute){
        this.waitForElementVisibility(element);
        return element.getAttribute(attribute);
    }

    public static String getDateTime() {
        return dateTime;
    }

        @AfterTest
        public static void quitDriver(){
            if(driver!=null){
                driver.quit();
            }
    }
}
