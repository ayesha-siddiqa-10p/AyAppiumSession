package com.qa.testCases;

import com.qa.base.AppFactory;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductDetailPage;
import com.qa.pages.ProductPage;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Objects;

public class ProductsTest extends AppFactory {
    private static final Logger log = LoggerFactory.getLogger(ProductsTest.class);

    ProductPage productPage;
    ProductDetailPage productDetailPage;
    LoginTest loginTest;

    @BeforeMethod
    public void setup(Method method) throws IOException {
        loginTest = new LoginTest();
        loginTest.setup(method);
        loginTest.loginWithValidCred();
        productPage = loginTest.productPage;
        utilities.log().info("\n\n********** " + method.getName() + " **********\n\n");
    }


    @Test
    public void openProductDetailPage() throws IOException {
        utilities.log().info("verify clicking on product opens the detail page");
        productDetailPage = productPage.clickProductName();
        String expectedTitle = stringHashMap.get("backButton_title");
        String actualTitle = productDetailPage.getButtonText();
        utilities.log().info("Actual title is " + actualTitle + "\nExpected title is " + expectedTitle);
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @AfterClass
    public void tearDown(){
        AppFactory.quitDriver();
    }

}
