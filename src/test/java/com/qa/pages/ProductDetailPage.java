package com.qa.pages;

import com.qa.base.AppDriver;
import com.qa.base.AppFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailPage extends AppFactory {

    public ProductDetailPage() {PageFactory.initElements(new AppiumFieldDecorator(AppDriver.getDriver()), this);
    }

    @AndroidFindBy(xpath = "    //android.widget.TextView[@text=\"BACK TO PRODUCTS\"]\n")
    public WebElement backButtonText;

    public String getButtonText() {
        return getAttribute(backButtonText, "text");
    }

}
