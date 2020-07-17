package litecart;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ProductStickers extends WebDriverSettings{

    private By locatorProducts = By.cssSelector(".product");
    private By locatorSticker = By.cssSelector(".sticker:last-child");


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
        List<WebElement> stickers = seachElements(locatorSticker);

        Assert.assertTrue(products.size()==stickers.size());
    }

}
