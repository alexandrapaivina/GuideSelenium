package litecart;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class WorkingBasket extends WebDriverSettings{

    private By locProduct = By.cssSelector("[class *= 'product column']");
    private By locAddToBascket = By.cssSelector("[name = 'add_cart_product']");
    private By locBascket = By.cssSelector("span[class = 'quantity']");
    private By locOptions = By.cssSelector("[name *= 'options']");
    private By locCheckout = By.cssSelector("a[class = 'link'][href *= 'checkout']");
    private By locRemoute  = By.cssSelector("button[name *= 'remove']");
    private By locOrder = By.cssSelector("div[id *= 'order']");
    private By locDucks = By.cssSelector("[href = '#']");

    @Test
    public void workingWithBasket() throws InterruptedException{

        //1) открыть главную страницу
        driver.get("http://localhost/litecart/en/");

        //4) вернуться на главную страницу,
        // повторить предыдущие шаги ещё два раза, чтобы в общей сложности в корзине было 3 единицы товара
        for(int i=1;i<4;i++){
            //2) открыть первый товар из списка
            List<WebElement> products = seachElements(locProduct);
            products.get(0).click();

            if(IsElementExists(locOptions)==Boolean.TRUE){
                choiceSelect(locOptions);
            }

            //2) добавить его в корзину (при этом может случайно добавиться товар, который там уже есть, ничего страшного)
            driver.findElement(locAddToBascket).click();
            WebElement countBascket = driver.findElement(locBascket);

            //3) подождать, пока счётчик товаров в корзине обновится
            wait.until(ExpectedConditions.textToBePresentInElement(countBascket,Integer.toString(i)));
            Assert.assertTrue(driver.findElement(locBascket).getText().equals(Integer.toString(i)));

            driver.navigate().back();
        }

        //5) открыть корзину (в правом верхнем углу кликнуть по ссылке Checkout)
        driver.findElement(locCheckout).click();
        Thread.sleep(1000);

        //) удалить все товары из корзины один за другим, после каждого удаления подождать, пока внизу обновится таблица
        List<WebElement> remout = driver.findElements(locRemoute);
        int count = remout.size();
        for (int i = 1;i<=count;i++){
            WebElement table = driver.findElement(locOrder);

            if(i!=count){driver.findElement(locDucks).click();}

            driver.findElement(locRemoute).click();
            wait.until(ExpectedConditions.stalenessOf(table));
        }
    }

    public List<WebElement> seachElements(By locator) {
        List<WebElement> links = driver.findElements(locator);
        return links;
    }

    public void choiceSelect(By locator){
        Select sel = new Select(driver.findElement(locator));
        if (sel.getOptions().size()>1){
            sel.selectByIndex(1);
        }
    }

    public static Boolean IsElementExists(By locator)
    {
        try
        {
            driver.findElement(locator);
            return true;
        }
        catch (NoSuchElementException e)
        {
            return false;
        }
    }


}
