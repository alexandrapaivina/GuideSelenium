package litecart.app;

import litecart.WebDriverSettings;
import litecart.model.Product;
import litecart.pages.BasketPage;
import litecart.pages.MainPage;
import litecart.pages.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class Application {

    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;
    private ProductPage productPage;
    private BasketPage basketPage;

    public Application(){
        System.setProperty("webdriver.chrome.driver","/Users/dev/AuxiliaryExecutables/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        basketPage = new BasketPage(driver);
    }

    public void quit(){
        driver.quit();
        driver = null;
    }

    //Открытие главной страницы приложения
    public void open(){
        driver.get("http://localhost/litecart/en/");
    }

    //Открытие первого товара из списка
    public void openFirstProduct(){
        mainPage.products.get(0).click();
    }

    //Добавление товара в корзину и ожидание обновления счетчика в корзине
    public void addToBasket() throws InterruptedException{
        int count = Integer.parseInt(productPage.countBascket().getText());
        try{
                productPage.sizeProduct().selectByIndex(1);
                productPage.addToBascket.click();
        }catch (NoSuchElementException e){
            productPage.addToBascket.click();
        }
        wait.until(ExpectedConditions.textToBePresentInElement(productPage.countBascket(),Integer.toString(count+1)));
        Assert.assertTrue(productPage.countBascket().getText().equals(Integer.toString(count+1)));
    }

    public void back(){
        driver.navigate().back();
    }

    public void openBascket() throws InterruptedException{
        mainPage.openBasket.click();
        Thread.sleep(1000);
    }

    //Удаление всех товаров из корзины
    public void removeAllProductsInBascket(){
        int count = basketPage.remout.size();
        for (int i = 1;i<=count;i++){
            WebElement table = basketPage.table();

            if(i!=count){basketPage.product.click();}
            basketPage.firstRemout.click();

            wait.until(ExpectedConditions.stalenessOf(table));
        }
    }
}
