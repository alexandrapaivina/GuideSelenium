package litecart;

import litecart.model.Product;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;


public class AddProduct extends WebDriverSettings{

    public Product product = new Product();

    public By locCatalog = By.cssSelector("a[href *= 'catalog']");
    public By locAddProductButton= new By.ByCssSelector("a[href *= 'edit_product']");
    public By locInformation = new By.ByCssSelector("a[href *= 'information']");
    public By locGeneral = new By.ByCssSelector("a[href *= 'general']");
    public By locPrices = new By.ByCssSelector("a[href*='prices']");
    public By locSave = new By.ByCssSelector("[name ='save']");

    //General
    public By locName = By.cssSelector("[name = 'name[en]']");
    public By locCode = By.cssSelector("[name = 'code']");
    public By locQuantity = By.cssSelector("[name = 'quantity']");
    public By locDataValidFrom = By.cssSelector("[name = 'date_valid_from']");
    public By locDataValidTo = By.cssSelector("[name='date_valid_to']");
    public By locFile = By.cssSelector("[type = 'file']");

    //Information
    public By locDescription = By.cssSelector("[dir *= 'ltr']");
    public By locKeywords = By.cssSelector("[name *= 'keywords']");
    public By locShortDescription = By.cssSelector("[name *= 'short_description']");
    public By locHeadTitle = By.cssSelector("[name *= 'head_title']");
    public By locMetaDescription = By.cssSelector("[name *= 'meta_description']");
    public By locManufacturer = By.cssSelector("[name *= 'manufacturer_id']");
    public By locSupplier = By.cssSelector("[name *= 'supplier']");

    //Prices
    public By locPrice  = By.cssSelector("[data-type='decimal'][name*='purchase_price']");
    public By locCurrency  = By.cssSelector("[name *='currency_code']");
    public By locTaxClass  = By.cssSelector("[name *='tax_class']");
    public By locPriceUSD  = By.cssSelector("[name='prices[USD]']");
    public By locPriceEUR  = By.cssSelector("[name='prices[EUR]']");


    @Test
    public void addProduct() throws InterruptedException{

        //Авторизация
        Login log = new Login();
        log.login();
        Thread.sleep(1000);
        generateProduct();

        //Для добавления товара нужно открыть меню Catalog,
        driver.findElement(locCatalog).click();
        Thread.sleep(1000);
        // в правом верхнем углу нажать кнопку "Add New Product",
        driver.findElement(locAddProductButton).click();
        // заполнить поля с информацией о товаре
        Thread.sleep(1000);

        //Information
        driver.findElement(locInformation).click();
        Thread.sleep(2000);
        choiceSelect(locManufacturer);
        choiceSelect(locSupplier);
        driver.findElement(locKeywords).sendKeys(product.keywords);
        driver.findElement(locShortDescription).sendKeys(product.shortDescription);
        driver.findElement(locDescription).sendKeys(product.description);
        driver.findElement(locHeadTitle).sendKeys(product.headTitle);
        driver.findElement(locMetaDescription).sendKeys(product.metaDescription);

        //Prices
        driver.findElement(locPrices).click();
        Thread.sleep(2000);
        clearAndSendText(locPrice,product.priceRegular);
        choiceSelect(locCurrency);
        choiceSelect(locTaxClass);
        clearAndSendText(locPriceUSD,product.priceUSD);
        clearAndSendText(locPriceEUR,product.priceEUR);

        //General
        driver.findElement(locGeneral).click();
        driver.findElement(locName).sendKeys(product.name);
        driver.findElement(locCode).sendKeys(product.code);
        clearAndSendText(locQuantity,product.quantity);
        Thread.sleep(1000);

        driver.findElement(locDataValidFrom).sendKeys(product.dataValidFrom);
        driver.findElement(locDataValidTo).sendKeys(product.dataValidTo);

        String path = new File("").getAbsolutePath();
        driver.findElement(locFile).sendKeys(path + "//test.jpg");

        //и сохранить.
        driver.findElement(locSave).click();
        Thread.sleep(1000);

        //После сохранения товара нужно убедиться, что он появился в каталоге (в админке).
        Assert.assertTrue(IsElementExists(By.linkText(product.name)));
    }

    public void generateProduct(){
        product.empName("Колесо"+generateNumber());
        product.empPriceRegular(rnd(100,1000));
        product.empPriceEUR(rnd(10,1000));
        product.empPriceUSD(rnd(10,100));
        product.empCode("Код"+generateNumber());
        product.empKeywords("Ключ"+generateNumber());
        product.empShortDescription("Колесо для складской тачки"+generateNumber());
        product.empMetaDescription("Мета"+generateNumber());
        product.empDescription("Колесо для складской тележки из вспененного полиуретана на промыштенном подшипнике"+generateNumber());
        product.empQuantity(rnd(1,10));
        product.empHeadTitle("Колесо для складской тачки"+generateNumber());

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        product.empDataValidFrom(date.format(formatter));
        product.empDataValidTo(date.plusYears(1).format(formatter));
    }

    public void clearAndSendText(By locator,String text){
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public void choiceSelect(By locator){
        Select sel = new Select(driver.findElement(locator));
        if (sel.getOptions().size()>1){
            sel.selectByIndex(1);
        }
    }

    public static String rnd(int min,int max)
    {
        max -= min;
        String number = String.valueOf((int) (Math.random() * ++max) + min);
        return number;
    }

    public String generateNumber(){
        DateFormat df = new SimpleDateFormat("ddMMyyyyHHmmss");
        Date today = Calendar.getInstance().getTime();
        String number = df.format(today);
        return number;
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
