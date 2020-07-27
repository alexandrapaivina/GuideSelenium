package Other;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ClickAllMenu extends WebDriverSettings{

    private By menuItems = By.xpath("//li[@id='app-']/a");
    private By menuSubItems = By.cssSelector("ul.docs a");
    private By headingPage = By.cssSelector("h1");

    //Поиск элемента
    public List<WebElement> seachElements(By locator) {
        List<WebElement> links =driver.findElements(locator);
        return links;
    }

    //Проверка есть ли элемент на странице
    private boolean existsElement(By locator) {
        try {
            driver.findElement(locator);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }


    @Test
    public void ClikMenu() throws InterruptedException {

        //Авторизация
        Login log = new Login();
        log.login();
        Thread.sleep(1000);

        //Прокликивает пункты меню
        List<WebElement> items = seachElements(menuItems);
        for (int i =0 ;i< items.size();i++) {
            items = seachElements(menuItems);
            items.get(i).click();
            Assert.assertTrue(existsElement(headingPage));
            Thread.sleep(1000);

            //Прокликивает подпункты меню
            List<WebElement> subItems = seachElements(menuSubItems);
             if (subItems.size() != 0) {
                for (int j=0;j<subItems.size();j++)
                {
                    subItems = seachElements(menuSubItems);
                    subItems.get(j).click();
                    Assert.assertTrue(existsElement(headingPage));
                    Thread.sleep(1000);
                }
            }
        }
    }


}
