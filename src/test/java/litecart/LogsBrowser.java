package litecart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class LogsBrowser extends WebDriverSettings{

    private By locProducts = By.xpath("//td/a[not(@title='Edit')][contains(@href,'product')]");

    //Сделайте сценарий, который проверяет, не появляются ли в логе браузера
    // сообщения при открытии страниц в учебном приложении,
    // а именно -- страниц товаров в каталоге в административной панели.
    @Test
    public void logsBrowser() throws InterruptedException{

        //1) зайти в админку
        Login log = new Login();
        log.login();
        Thread.sleep(1000);

        //2) открыть каталог, категорию, которая содержит товары
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");

        //3) последовательно открывать страницы товаров и проверять,
        // не появляются ли в логе браузера сообщения (любого уровня)
       List<WebElement> product = driver.findElements(locProducts);
        int count = product.size();
        for(int i=0;i<count;i++){
            product.get(i).click();

            Thread.sleep(1000);
            LogEntries logs = driver.manage().logs().get("browser");
            logs.forEach(l -> System.out.println(l));
            Assert.assertTrue(logs.getAll().isEmpty());

            driver.navigate().back();
            product = driver.findElements(locProducts);
        }
    }
}
