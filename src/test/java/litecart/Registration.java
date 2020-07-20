package litecart;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Registration extends WebDriverSettings{

    public User user = new User();

    public By locEmail = By.cssSelector("[name = 'email']");
    public By locFirstName = By.cssSelector("[name = 'firstname']");
    public By locLastName = By.cssSelector("[name = 'lastname']");
    public By locAdress1 = By.cssSelector("[name = 'address1']");
    public By locPostCod = By.cssSelector("[name = 'postcode']");
    public By locCity = By.cssSelector("[name = 'city']");
    public By locPass = By.cssSelector("[name = 'password']");
    public By locRetRass = By.cssSelector("[name = 'confirmed_password']");
    public By locPhone = By.cssSelector("[name = 'phone']");
    public By locCountry = By.cssSelector(" [id *= 'country_code']");
    public By locSeachCountry = By.cssSelector("[class *= 'search__field']");
    public By locCreateAccount = By.cssSelector("[name = 'create_account']");
    public By locZone = By.cssSelector("select[name = 'zone_code']");
    public By locLogout = By.cssSelector("a[href *= 'logout']");
    public By locLoginPage = By.cssSelector("a[href *= 'login']");
    public By locLogin = By.cssSelector("[name = 'login']");

    @Test
    public void registration() throws InterruptedException{

        driver.get("http://192.168.64.2/litecart/en/create_account");
        generateUser();

        //1) регистрация новой учётной записи с достаточно уникальным адресом электронной почты
        // (чтобы не конфликтовало с ранее созданными пользователями,
        // в том числе при предыдущих запусках того же самого сценария),
        //В качестве страны выбирайте United States, штат произвольный. При этом формат индекса -- пять цифр.
        driver.findElement(locEmail).sendKeys(user.email);
        driver.findElement(locFirstName).sendKeys(user.firstName);
        driver.findElement(locLastName).sendKeys(user.lastName);
        driver.findElement(locAdress1).sendKeys(user.adress1);
        driver.findElement(locCity).sendKeys(user.city);
        driver.findElement(locPhone).sendKeys(user.phone);
        driver.findElement(locPostCod).sendKeys((user.postCod).toString());
        driver.findElement(locCountry).click();
        driver.findElement(locSeachCountry).sendKeys(user.country+ Keys.ENTER);
        driver.findElement(locPass).sendKeys(user.pass);
        driver.findElement(locRetRass).sendKeys(user.pass);
        driver.findElement(locCreateAccount).click();
        Thread.sleep(1000);
        driver.findElement(locPass).sendKeys(user.pass);
        driver.findElement(locRetRass).sendKeys(user.pass);
        Select zone = new Select(driver.findElement(locZone));
        zone.selectByIndex(rndCount(1));
        driver.findElement(locCreateAccount).click();

        //2) выход (logout), потому что после успешной регистрации автоматически происходит вход,
        driver.findElement(locLogout).click();

        //3) повторный вход в только что созданную учётную запись,
        driver.findElement(locLoginPage).click();
        driver.findElement(locEmail).sendKeys(user.email);
        driver.findElement(locPass).sendKeys(user.pass);
        driver.findElement(locLogin).click();

        //4) и ещё раз выход.
        driver.findElement(locLogout).click();
    }

    public void generateUser(){
        user.empEmail("test"+generateNumber()+ "@mail.ru");
        user.empFirstName("Евдакия"+generateNumber());
        user.empLastName("Иванова"+generateNumber());
        user.empAdress1("ул.Ленина д."+rndCount(100));
        user.empCity("Краснодар");
        user.empPass(rndCount(100)+"Po89jTr");
        user.empPhone("+7"+rndCount(1000000000));
        user.empCountry("United States");
        user.empPostCod(rndCount(10000));
    }

    public String generateNumber(){
        DateFormat df = new SimpleDateFormat("ddMMyyyyHHmmss");
        Date today = Calendar.getInstance().getTime();
        String number = df.format(today);
        return number;
    }

    public static int rndCount(int count)
    {
        int max = 9*count;
        int min = count;
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }



}
