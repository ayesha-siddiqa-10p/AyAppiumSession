package com.qa.listeners;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.qa.base.AppDriver;
import com.qa.base.AppFactory;
import com.qa.reports.ExtentReport;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class TestListeners implements ITestListener {
    public void onTestFailure(ITestResult result) {     //log exception
        if (result.getThrowable() != null) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            result.getThrowable().printStackTrace(printWriter);
            System.out.println(stringWriter.toString());
        }


        // take screenshots on thread level using selenium func
        File file = ((TakesScreenshot) AppDriver.getDriver()).getScreenshotAs(OutputType.FILE);
        // convert img to base64 and then encode for sharing purpose
        byte[] encoded = null;
        try {
            encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        // get testng params in hashmap to create directory structure for storing screenshot

        Map<String, String> params = new HashMap<>();
        params = result.getTestContext().getCurrentXmlTest().getAllParameters();
        String imgpath = "screenshots" + File.separator + params.get("platformName") + "_" + params.get("platformVersion") + "_" + params.get("deviceName")
                + File.separator + AppFactory.getDateTime() + File.separator + result.getTestClass().getRealClass().getSimpleName() + File.separator + result.getName() + ".png";
        String completePath = System.getProperty("user.dir") + File.separator + imgpath;
        try {
            // copy files into imgpath
            FileUtils.copyFile(file,new File(imgpath));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        ExtentReport.getTest().fail("Test Fail", MediaEntityBuilder.createScreenCaptureFromPath(completePath).build());
        ExtentReport.getTest().fail("Test Fail", MediaEntityBuilder.createScreenCaptureFromBase64String(new String(encoded, StandardCharsets.US_ASCII)).build());
        ExtentReport.getTest().fail(result.getThrowable());
    }
    @Override
    public void onTestStart(ITestResult result) {
        ExtentReport.startTest(result.getName(), result.getMethod().getDescription()).assignCategory(AppDriver.getPlatformName() + "_" + AppDriver.getDeviceName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReport.getTest().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReport.getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context){
        ExtentReport.getExtentReports().flush();
    }

}