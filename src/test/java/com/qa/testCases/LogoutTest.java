package com.qa.testCases;

import com.qa.base.AppFactory;
import com.qa.pages.LoginPage;
import com.qa.pages.LogoutPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

public class LogoutTest extends AppFactory {
    private static final Logger log = LoggerFactory.getLogger(LogoutTest.class);

    LoginTest loginTest;
    LogoutPage logoutPage;
    LoginPage loginPage;



    @BeforeMethod
    public void setup(Method method) throws IOException {
        loginTest = new LoginTest();
        loginTest.setup(method);
        loginTest.loginWithValidCred();
        logoutPage = new LogoutPage();
        loginPage = loginTest.loginPage; // loginPage is initialized from loginTest
        utilities.log().info("\n\n********** " + method.getName() + " **********\n\n");
    }

    @Test
    public void logout() {
        utilities.log().info("verify user can logout successfully");
        logoutPage.clickOnSideMenu();
        logoutPage.clickLogoutBtn();
        Assert.assertTrue(loginPage.loginBtn.isDisplayed());
    }

    @AfterClass
    public void tearDown(){
        AppFactory.quitDriver();
    }


}
