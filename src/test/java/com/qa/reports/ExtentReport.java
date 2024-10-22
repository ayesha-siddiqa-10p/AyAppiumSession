package com.qa.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.util.HashMap;
import java.util.Map;

    public class ExtentReport {
        static ExtentReports extentReports;
        final static String filePath = "target/Spark.html";
        static Map<Integer, ExtentTest> extentReportMap = new HashMap<>();

        // report config
        public synchronized static ExtentReports getExtentReports(){
            if(extentReports == null){
                ExtentSparkReporter spark = new ExtentSparkReporter(filePath);
                spark.config().setDocumentTitle("Appium Report");
                spark.config().setReportName("Test Execution Report");
                spark.config().setTheme(Theme.DARK);
                extentReports = new ExtentReports();
                extentReports.attachReporter(spark);
            }
            return extentReports;
        }

        // return specific thread report
        public static synchronized ExtentTest getTest(){
            return extentReportMap.get((int)(long) (Thread.currentThread().getId()));
        }

        // to log when test start
        public static synchronized ExtentTest startTest(String testName, String testDescription){
            ExtentTest test = getExtentReports().createTest(testName, testDescription);
            extentReportMap.put((int)(long) (Thread.currentThread().getId()), test);
            return test;
        }
}
