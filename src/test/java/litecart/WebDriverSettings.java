package litecart;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

public class WebDriverSettings {

    public static WebDriver driver;
    public static WebDriverWait wait;

    @BeforeClass
    public void start() {

        if(driver != null) {
            return;
        }

        System.setProperty("webdriver.chrome.driver","/Users/dev/AuxiliaryExecutables/chromedriver");
        System.setProperty("webdriver.gecko.driver", "/Users/dev/AuxiliaryExecutables//geckodriver");

        driver = new ChromeDriver();
        //driver = new SafariDriver();
        //driver = new FirefoxDriver();
        wait = new WebDriverWait(driver,20);
        driver.manage().window().maximize();

       /* DesiredCapabilities caps = new DesiredCapabilities();
          caps.setCapability("unexpectedAlertBehaviour","dismiss");
          caps.setCapability("setWindowRect","false");
          System.out.println(((HasCapabilities) driver).getCapabilities());*/


    }

    @AfterClass
    public void stop() {
       driver.quit();
       driver = null;
    }

}
