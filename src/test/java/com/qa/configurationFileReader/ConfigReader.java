package com.qa.configurationFileReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader
         {
    private final Properties properties;   // final keyword means value cannot be changed

    public ConfigReader(){
        BufferedReader reader;
        String propertyFilePath= "configuration/config.properties";
        try{
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try{
                properties.load(reader);
                reader.close();
            }
            catch(IOException exception) {
                exception.printStackTrace();
            }
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Config File not found at" + propertyFilePath);
        }
    }

    public String getAppPackage() {
    String appPackage = properties.getProperty("androidAppPackage");
    if (appPackage != null) return appPackage;
    else throw new RuntimeException("appPackage is not specified in property file");
    }

    public String getAppActivity() {
    String appActivity = properties.getProperty("androidAppActivity");
    if (appActivity != null) return appActivity;
    else throw new RuntimeException("appActivity is not specified in property file");
    }

    public String getAutomationName() {
    String automationName = properties.getProperty("androidAutomationName");
    if (automationName != null) return automationName;
    else throw new RuntimeException("automationName is not specified in property file");
    }

             public String getCommandTimeoutValue() {
                 String commandTimeoutValue = properties.getProperty("androidCommandTimeoutValue");
                 if (commandTimeoutValue != null) return commandTimeoutValue;
                 else throw new RuntimeException("commandTimeoutValue is not specified in property file");
             }

             public String getApkPath() {
                 String apkPath = properties.getProperty("androidApkPath");
                 if (apkPath != null) return apkPath;
                 else throw new RuntimeException("apkPath is not specified in property file");
             }
             public String getNoReset() {
                 String noReset = properties.getProperty("androidNoReset");
                 if (noReset != null) return noReset;
                 else throw new RuntimeException("noReset is not specified in property file");
             }
             public String getAppiumServerEndpointURL() {
                 String appiumServerEndpointURL = properties.getProperty("androidAppiumServerEndpointURL");
                 if (appiumServerEndpointURL != null) return appiumServerEndpointURL;
                 else throw new RuntimeException("appiumServerEndpointURL is not specified in property file");
             }


         }