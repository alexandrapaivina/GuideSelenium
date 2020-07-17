package litecart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CountriesSorting  extends WebDriverSettings{

    private By locatorCountries = By.cssSelector("td:nth-of-type(5) a");
    private By locatorZone = By.cssSelector("td:nth-of-type(6)");
    private By locatorCountriesZone = By.cssSelector("input[name*='[name]'][type='hidden']");
    private By locatorSelectsZone = By.cssSelector("select[name*='[zone_code]']");

    ArrayList<Integer> countZone = new ArrayList<Integer>();
    List<WebElement> countries = (List<WebElement>) driver;


    //1а) проверить, что страны расположены в алфавитном порядке
    @Test
    public void sortCountries () throws InterruptedException {

        logintAdmin ();
        driver.get("http://192.168.64.2/litecart/admin/?app=countries&doc=countries");

        //Получение списка городов и проверка что список по алфавиту
        countries = seachElements(locatorCountries);
        listInAlphabetical(countries);
    }

    /*1б) для тех стран, у которых количество зон отлично от нуля
    -- открыть страницу этой страны и там проверить,
    что зоны расположены в алфавитном порядке*/
    @Test
    public void sortCountriesInZone() throws InterruptedException {
        logintAdmin();
        driver.get("http://192.168.64.2/litecart/admin/?app=countries&doc=countries");

        //Получение зон
        List<WebElement> zone = seachElements(locatorZone);
        countries = seachElements(locatorCountries);
        int j=0;
        while ( j != zone.size())
        {
            countZone.add(Integer.parseInt(zone.get(j).getText()));
            j++;
        }

        //Переход в страну с зоной != 0 и там проверка что страны по алфавиту
        for(int i =0;i<countries.size();i++)
        {
            if (countZone.get(i)!=0){
                countries.get(i).click();
                List<WebElement> countriesInZone =  seachElements(locatorCountriesZone);
                listInAlphabetical(countriesInZone,"value");
                driver.navigate().back();
                countries = seachElements(locatorCountries);
            }
        }
    }

    //2) зайти в каждую из стран и проверить, что зоны расположены в алфавитном порядке
    @Test
    public void sortCountriesInGEO() throws InterruptedException{
        logintAdmin();
        driver.get("http://192.168.64.2/litecart/admin/?app=geo_zones&doc=geo_zones");
        countries = seachElements(locatorCountries);

        //Переход в страну и проверка селектов с зонами
        for (int i=0;i<countries.size();i++)
        {
            countries = seachElements(locatorCountries);
            countries.get(i).click();
            Thread.sleep(1000);

            List<WebElement> selectZone = driver.findElements(locatorSelectsZone);
            ArrayList<WebElement> options = new ArrayList<WebElement>();
            int j=0;
            while ( j != selectZone.size())
            {
                Select sel = new Select(selectZone.get(j));
                options.add(sel.getFirstSelectedOption());
                j++;
            }
            listInAlphabetical(options);
            driver.navigate().back();
        }
    }

    //Поиск элемента
    public List<WebElement> seachElements(By locator) {
        List<WebElement> elem = driver.findElements(locator);
        return elem;
    }

    //Проверка что список стран по алфавиту
    //Добавление в список через getText
    static void listInAlphabetical(List<WebElement> countries){

        ArrayList<String> listOne = new ArrayList<String>();
        ArrayList<String> listTwo = new ArrayList<String>();

        for(int i =0;i<countries.size();i++)
        {
                listOne.add(countries.get(i).getText());
                listTwo.add(countries.get(i).getText());
        }
        Collections.sort(listTwo);
        Assert.assertEquals(listOne,listTwo);
    }

    //Проверка что список стран по алфавиту
    //Добавление в список через getAttribute
    static void listInAlphabetical(List<WebElement> countries, String property){

        ArrayList<String> listOne = new ArrayList<String>();
        ArrayList<String> listTwo = new ArrayList<String>();

        for(int i =0;i<countries.size();i++)
        {
                listOne.add(countries.get(i).getAttribute(property));
                listTwo.add(countries.get(i).getAttribute(property));
        }
        Collections.sort(listTwo);
        Assert.assertEquals(listOne,listTwo);
    }

    public void logintAdmin () throws InterruptedException{
        //Авторизация
        Login log = new Login();
        log.login();
        Thread.sleep(1000);
    }

}
