package com.qa.pages;

import com.qa.base.AppDriver;
import com.qa.base.AppFactory;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.qa.utils.Utilities.WAIT;

public class LoginPage extends AppFactory {
        public LoginPage() {
            PageFactory.initElements(new AppiumFieldDecorator(AppDriver.getDriver()), this);
        }

        @AndroidFindBy(accessibility = "test-Username")
        public WebElement userNameTextBox;

        @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc=\"test-Password\"]")
        public WebElement passwordTextBox;

        @AndroidFindBy(accessibility = "test-LOGIN")
        public WebElement loginBtn;



        By swagslabHeader = By.xpath("//android.widget.ScrollView[@content-desc=\"test-Login\"]/android.view.ViewGroup/android.widget.ImageView[1]");

        @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Username and password do not match any user in this service.\"]")
        public WebElement errorMessage;

        public void enterUserName(String userName) {
                sendKeys(userNameTextBox, userName, "Entering username" + userName);
        }

        public void enterPassword(String password) {
                sendKeys(passwordTextBox, password, "Entering password");
        }

        public ProductPage clickLoginBtn() {
                clickElement(loginBtn,"Clicking on login button");
                return  new ProductPage(); // initialiizing the landing page to make the element accessable in testcase
        }


        public String getErrorMessage() {
                return getAttribute(errorMessage, "text");
        }



}
