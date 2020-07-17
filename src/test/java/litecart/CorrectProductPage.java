package litecart;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CorrectProductPage extends WebDriverSettings{

    private By locProduct = By.cssSelector("div[id *= 'campaigns'] a.link");
    private By locPriceRegBlock = By.cssSelector("div[id *= 'campaigns'] .regular-price");
    private By locPriceSaleBlock = By.cssSelector("div[id *= 'campaigns'] .campaign-price");
    private By locPriceRegPage = By.cssSelector(".regular-price");
    private By locPriceSalePage = By.cssSelector(".campaign-price");

    //а) на главной странице и на странице товара совпадает текст названия товара
    //б) на главной странице и на странице товара совпадают цены (обычная и акционная)
    @Test
    public void ProductCorrect() throws InterruptedException {
        driver.get("http://192.168.64.2/litecart/en/");

        Product productBlock = new Product();
        Product productPage = new Product();

        productBlock.empName(driver.findElement(By.cssSelector("div[id *= 'campaigns'] .name")).getText());
        productBlock.empPriceRegular(driver.findElement(locPriceRegBlock).getText());
        productBlock.empPriceSale(driver.findElement(locPriceSaleBlock).getText());

        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(locProduct));

        clickProduct();
        Thread.sleep(1000);

        productPage.empName(driver.findElement(By.cssSelector("h1.title")).getText());
        productPage.empPriceRegular(driver.findElement(locPriceRegPage).getText());
        productPage.empPriceSale(driver.findElement(locPriceSalePage).getText());

        //Проверка что цены и название товара совпадают
        Assert.assertEquals(productBlock.name,productPage.name);
        Assert.assertEquals(productBlock.priceRegular,productPage.priceRegular);
        Assert.assertEquals(productBlock.priceSale,productPage.priceSale);
    }


    //в) обычная цена зачёркнутая и серая (можно считать, что "серый" цвет это такой, у которого в RGBa представлении одинаковые значения для каналов R, G и B)
    @Test
    public void propertyRegularPrice() throws InterruptedException{
        driver.get("http://192.168.64.2/litecart/en/");

        //Проверка что обычная цена на главной странице в блоке Campaigns серая
        Color colorPriceRegularBlock = parseRGBA(driver.findElement(locPriceRegBlock).getCssValue("color"));
        Assert.assertTrue(checkGray(colorPriceRegularBlock));

        Thread.sleep(1000);

        //Проверка что обычная цена на главной странице в блоке Campaigns зачеркнута
        String propertyBlockCrossOut =  driver.findElement(locPriceRegBlock).getCssValue("text-decoration");
        Assert.assertTrue(checkTextCrossOut(propertyBlockCrossOut));

        clickProduct();

        //Проверка что обычная цена на странице продукта серая
        Color colorPriceRegularPage = parseRGBA(driver.findElement(locPriceRegPage).getCssValue("color"));
        Assert.assertTrue(checkGray(colorPriceRegularPage));

        //Проверка что обычная цена на странице продукта зачеркнута
        String propertyPageCrossOut =  driver.findElement(locPriceRegPage).getCssValue("text-decoration");
        Assert.assertTrue(checkTextCrossOut(propertyPageCrossOut));
    }

    // г) акционная жирная и красная (можно считать, что "красный" цвет это такой, у которого в RGBa представлении каналы G и B имеют нулевые значения)
    //(цвета надо проверить на каждой странице независимо, при этом цвета на разных страницах могут не совпадать)
    @Test
    public void propertySalePrice() throws InterruptedException{
        driver.get("http://192.168.64.2/litecart/en/");

        //Проверка что акционная цена на главной странице в блоке Campaigns красная
        Color colorPriceSaleBlock = parseRGBA(driver.findElement(locPriceSaleBlock).getCssValue("color"));
        Assert.assertTrue(checkRed(colorPriceSaleBlock));

        //Проверка что обычная цена на главной странице в блоке Campaigns выделена жирным
        String propertyBlockCrossOut =  driver.findElement(locPriceSaleBlock).getCssValue("font-weight");
        Assert.assertTrue(checkBold(propertyBlockCrossOut));

        clickProduct();
        Thread.sleep(1000);

        //Проверка что акционная цена на странице продукта красная
        Color colorPriceSalePage = parseRGBA(driver.findElement(locPriceSalePage).getCssValue("color"));
        Assert.assertTrue(checkRed(colorPriceSalePage));

        //Проверка что обычная цена на странице продукта выделена жирным
        String propertyPageCrossOut =  driver.findElement(locPriceSalePage).getCssValue("font-weight");
        Assert.assertTrue(checkBold(propertyPageCrossOut));
    }

    // д) акционная цена крупнее, чем обычная (это тоже надо проверить на каждой странице независимо)
    @Test
    public void proreprySaleRegularSize(){
        driver.get("http://192.168.64.2/litecart/en/");

        //Проверка что у обычной цены шрифт меньше чем у акционной на главной странице в блоке Campaigns
        String sizePriceRegBlock = driver.findElement(locPriceRegBlock).getCssValue("font-size");
        String sizePriceSaleBlock = driver.findElement(locPriceSaleBlock).getCssValue("font-size");
        Assert.assertTrue(checkSize(sizePriceRegBlock,sizePriceSaleBlock));

        clickProduct();

        //Проверка что у обычной цены шрифт меньше чем у акционной на странице продукта
        String sizePriceRegPage = driver.findElement(locPriceRegPage).getCssValue("font-size");
        String sizePriceSalePage = driver.findElement(locPriceSalePage).getCssValue("font-size");
        Assert.assertTrue(checkSize(sizePriceRegPage,sizePriceSalePage));
    }

    public static Color parseRGBA(String colorText)
    {
        Pattern patRGBA = Pattern.compile("rgba *\\( *([0-9]+), *([0-9]+), *([0-9]+), *([0-9]+) *\\)");
        Pattern patRGB = Pattern.compile("rgb *\\( *([0-9]+), *([0-9]+), *([0-9]+) *\\)");
        Matcher matRGBA = patRGBA.matcher(colorText);
        Matcher matRGB = patRGB.matcher(colorText);

        if (matRGBA.matches())
        {
            return new Color(Integer.valueOf(matRGBA.group(1)),  // r
                    Integer.valueOf(matRGBA.group(2)),  // g
                    Integer.valueOf(matRGBA.group(3))); // b
        }else if(matRGB.matches()){
            return new Color(Integer.valueOf(matRGB.group(1)),  // r
                    Integer.valueOf(matRGB.group(2)),  // g
                    Integer.valueOf(matRGB.group(3))); // b
        }else return null;
    }

    public Boolean checkGray(Color color){
        if(color.getRed()==color.getGreen() && color.getGreen()==color.getBlue()) {
            return Boolean.TRUE;
        }else return Boolean.FALSE;
    }

    public Boolean checkRed(Color color){
        if(color.getGreen()==0 && color.getBlue()==0) {
            return Boolean.TRUE;
        }else return Boolean.FALSE;
    }

    public Boolean checkTextCrossOut(String property){
        if (property.contains("line-through")) {
             return Boolean.TRUE;
        }else return Boolean.FALSE;
    }

    public Boolean checkBold(String property){
        if (property.equals("bold")) {
            return Boolean.TRUE;
        }else {
            int quantity = Integer.parseInt(property);
            if(quantity>400){
                return Boolean.TRUE;
            } else return Boolean.FALSE;
        }
    }

    public Boolean checkSize(String sizeOne,String sizeTwo){
        Double sizeCountOne = Double.parseDouble(sizeOne.replaceAll("[p-x]", ""));
        Double sizeCountTwo = Double.parseDouble(sizeTwo.replaceAll("[p-x]", ""));

        if(sizeCountOne<sizeCountTwo){
            return Boolean.TRUE;
        }else return Boolean.FALSE;
    }

    public void clickProduct(){
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("arguments[0].click();", driver.findElement(locProduct));
        //driver.findElement(locProduct).click();
    }
}
