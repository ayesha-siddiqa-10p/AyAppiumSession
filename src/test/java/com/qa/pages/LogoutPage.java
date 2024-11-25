package com.qa.pages;

import com.qa.base.AppDriver;
import com.qa.base.AppFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage extends AppFactory {
    public LogoutPage() {
        PageFactory.initElements(new AppiumFieldDecorator(AppDriver.getDriver()), this);
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Menu\"]/android.view.ViewGroup/android.widget.ImageView")
    public WebElement sideMenuIcon;


    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"LOGOUT\"]")
    public WebElement logoutBtn;


    public void clickLogoutBtn() {
        clickElement(logoutBtn,"Clicking on logout button");
    }

    public void clickOnSideMenu() {
        clickElement(sideMenuIcon,"Clicking on side menu icon");
    }
}
