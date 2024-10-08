package com.qa.testCases;

import com.qa.base.AppFactory;
import com.qa.pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import java.sql.SQLOutput;

public class LoginTest extends AppFactory {   //as BeforeTest is defined there

    LoginPage loginPage;

    @BeforeMethod
    public void setup(){
        loginPage = new LoginPage();
    }

    public void verifyvalidcred() throws InterruptedException {
        System.out.println("Login with valid credentials");
    loginPage.enterUserName("standard_user");
    loginPage.enterPassword("secret_sauce");
    loginPage.clickLoginBtn();
        System.out.println("user logged in successfully");
    Thread.sleep(50000);
    }


    @AfterMethod
    public void tearDown(){
        AppFactory.quitDriver();
    }
}
