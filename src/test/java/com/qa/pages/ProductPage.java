package com.qa.pages;

import com.qa.base.AppDriver;
import com.qa.base.AppFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends AppFactory {

    public ProductPage() { PageFactory.initElements(new AppiumFieldDecorator(AppDriver.getDriver()), this);
    }


    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"PRODUCTS\"]")
    public WebElement productHeader;

    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"test-Item title\" and @text=\"Sauce Labs Bike Light\"]")
    public WebElement productName;

    public String getTitle() {
        return getAttribute(productHeader, "text");
    }

    public ProductDetailPage clickProductName() {
        clickElement(productName,"Clicking on product name");
        return new ProductDetailPage();
    }
}