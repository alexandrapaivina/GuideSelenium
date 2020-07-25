package litecart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class WorkingWithWindows extends WebDriverSettings{

    private By locEdit = By.cssSelector("i[class *= 'pencil']");
    private By locLink = By.cssSelector("i[class *= 'fa-external-link']");

    @Test
    public void workingWithWindows() throws InterruptedException{

        //1) зайти в админку
        Login log = new Login();
        log.login();
        Thread.sleep(1000);

        //2) открыть пункт меню Countries
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        //3) открыть на редактирование какую-нибудь страну или начать создание новой
        driver.findElement(locEdit).click();

        //4) возле некоторых полей есть ссылки с иконкой в виде квадратика со стрелкой -- они ведут на внешние страницы и открываются в новом окне, именно это и нужно проверить.
        List<WebElement> links = driver.findElements(locLink);
        String mainWindow = driver.getWindowHandle();

        openWindowAndBack(links,mainWindow);
        Thread.sleep(5000);
    }

    public void openWindowAndBack(List<WebElement> list, String mainWindow) throws InterruptedException{

        for(int i=0;i<list.size();i++){
            list.get(i).click();
            Object[] oldWindows = driver.getWindowHandles().toArray();
            Thread.sleep(5000);
            String newWindow = findFirstNewWindow(oldWindows,mainWindow);
            Assert.assertNotNull(newWindow);
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(mainWindow);
        }
    }

    public String findFirstNewWindow(Object[] windows,String mainWindow){
           int i=0;
           while(i!=windows.length){
               if(!windows[i].equals(mainWindow)){
                   return windows[i].toString();
               } else i++;
           }
           return null;
    }
}
