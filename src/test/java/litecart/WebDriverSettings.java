package litecart;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSettings {

   public ChromeDriver driver;

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver","/Users/dev/AuxiliaryExecutables/chromedriver");
        driver = new ChromeDriver();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
