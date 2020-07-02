package litecart;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverSettings {

   public static WebDriver driver;
   private static WebDriverWait wait;


    @Before
    public void start() {

        if(driver != null) {
            return;
        }

        System.setProperty("webdriver.chrome.driver","/Users/dev/AuxiliaryExecutables/chromedriver");
        System.setProperty("webdriver.gecko.driver", "/Users/dev/AuxiliaryExecutables//geckodriver");

        driver = new ChromeDriver();
        //driver = new SafariDriver();
        //driver = new FirefoxDriver();
        wait = new WebDriverWait(driver,10);

       /* DesiredCapabilities caps = new DesiredCapabilities();
          caps.setCapability("unexpectedAlertBehaviour","dismiss");
          caps.setCapability("setWindowRect","false");
          System.out.println(((HasCapabilities) driver).getCapabilities());*/

        /*Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {driver.quit(); driver = null;}));*/
    }

    @After
    public void stop() {
       driver.quit();
       driver = null;




    }

}
