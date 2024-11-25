import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectionString {

    AppiumDriver driver;
    @BeforeTest
    public void initializer() throws MalformedURLException {
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability("appium:platformName","Android");
        capabilities.setCapability("appium:deviceName", "emulator-5554");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:app",System.getProperty("user.dir")+ "/app/DummyAPK.apk");
        capabilities.setCapability("appium:appPackage", "com.swaglabsmobileapp");
        capabilities.setCapability("appium:appActivity", "com.swaglabsmobileapp.SplashActivity");
        capabilities.setCapability("appium:noReset", false); /* cache not maintained */
        capabilities.setCapability("appium:newCommandTimeout", 5000);
//         capabilities.setCapability("appium:avd","Pixel_3a_API_35");
//        capabilities.setCapability("appium:avdLaunchTimeout","5000000");

        driver = new AndroidDriver(new URL("http://localhost:4723"), capabilities);
        System.out.println("Starting The Appium Server");
    }

    @Test
    public void sampleTest(){
        System.out.println("This is my first session testcase");
    }

    @AfterTest
    public void tearDown() throws IOException {
        driver.quit();    // will only close the session, emulator will still remain open

      // To close emulator when running on CICD
//        String cmdString = "adb -s emulator-5554 emu kill";
//       if(driver!= null){
//            try{
//              Runtime.getRuntime().exec("CMD /C " + cmdString);
//            } catch (IOException e) {
//                e.printStackTrace();
//           }
//        }
    }

}
