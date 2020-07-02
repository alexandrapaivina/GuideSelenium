package litecart;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductStickers extends WebDriverSettings{

    private By locatorProducts = By.cssSelector("div.content a.link");
    private By locatorNew = By.xpath("//div[@title='New']");
    private By locatorSale = By.xpath("//div[@title='On Sale']");


    //Поиск элемента
    public List<WebElement> seachElements(By locator) {
        List<WebElement> links = driver.findElements(locator);
        return links;
    }

    @Test
    public void availabilityStickers () throws InterruptedException {

        driver.get("http://192.168.64.2/litecart/en/");
        Thread.sleep(1000);

        List<WebElement> products = seachElements(locatorProducts);
        List<WebElement> stickersNew = seachElements(locatorNew);
        List<WebElement> stickersSale = seachElements(locatorSale);

        Assert.assertTrue(products.size()==stickersNew.size()+stickersSale.size());
    }

}
