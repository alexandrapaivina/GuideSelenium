package Other;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ProductStickers extends WebDriverSettings{

    private By locProducts = By.cssSelector(".product");
    private By locSticker = By.cssSelector(".sticker");

    @Test
    public void availabilityStickers () throws InterruptedException {

        driver.get("http://localhost/litecart/en/");

        List<WebElement> products = seachElements(locProducts);

        for(int i=0;i<products.size();i++){
            Assert.assertTrue(IsElementExists(products.get(i),locSticker));
        }
    }

    //Поиск элемента
    public List<WebElement> seachElements(By locator) {
        List<WebElement> links = driver.findElements(locator);
        return links;
    }

    public static Boolean IsElementExists(WebElement elem,By locator)
    {
        try
        {
            elem.findElement(locator);
            return true;
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }
}
