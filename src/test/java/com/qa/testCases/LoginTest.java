package com.qa.testCases;

import com.google.gson.JsonObject;
import com.qa.base.AppFactory;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductPage;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.SQLOutput;
import java.util.Objects;

public class LoginTest extends AppFactory {
    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);   //as BeforeTest is defined there

    LoginPage loginPage;
    ProductPage productPage;
    InputStream inputStream;
    JSONObject loginUser;


    @BeforeMethod
    public void setup(Method method){
        loginPage = new LoginPage();
        utilities.log().info("\nLogging" + method.getName() + "Logging\n");
    }

    @BeforeClass
    public void setupDataStream() throws IOException {
        try{
            String dataFile = "data/loginUser.json";
            inputStream = getClass().getClassLoader().getResourceAsStream(dataFile);
            JSONTokener jsonTokener= new JSONTokener(Objects.requireNonNull(inputStream));
            loginUser = new JSONObject(jsonTokener);

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }


    @Test
    public void loginWithInvalidUsername(){
        utilities.log().info("verify user cannot login with invalid username");
        loginPage.enterUserName(loginUser.getJSONObject("invalidUser").getString("userName"));
        loginPage.enterPassword(loginUser.getJSONObject("invalidUser").getString("password"));
        loginPage.clickLoginBtn();
        String expectedErrorMsg = stringHashMap.get("error_invalid_username_and_password");
        String actualErrorMsg = loginPage.getErrorMessage();
        utilities.log().info("Actual Error Message is " + actualErrorMsg + "\nExpected Error Message is " + expectedErrorMsg);
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
    }
@Test
    public void loginWithInvalidPassword() {
    utilities.log().info("verify user cannot login with invalid password");
    loginPage.enterUserName(loginUser.getJSONObject("invalidPassword").getString("userName"));
    loginPage.enterPassword(loginUser.getJSONObject("invalidPassword").getString("password"));
    loginPage.clickLoginBtn();
    String expectedErrorMsg = stringHashMap.get("error_invalid_username_and_password");
    String actualErrorMsg = loginPage.getErrorMessage();
    utilities.log().info("Actual Error Message is " + actualErrorMsg + "\nExpected Error Message is " + expectedErrorMsg);
    Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
}
@Test
    public void loginWithValidCred() {
    utilities.log().info("verify user can login with valid cred");

    loginPage.enterUserName(loginUser.getJSONObject("validUserAndPassword").getString("userName"));
    loginPage.enterPassword(loginUser.getJSONObject("validUserAndPassword").getString("password"));
    productPage = loginPage.clickLoginBtn();
    String expectedTitle = stringHashMap.get("product_title");
    String actualTitle = productPage.getTitle();
    utilities.log().info("Actual title is " + actualTitle + "\nExpected title is " + expectedTitle);
    Assert.assertEquals(actualTitle, expectedTitle);
}


    @AfterClass
    public void tearDown(){
        AppFactory.quitDriver();
    }
}
