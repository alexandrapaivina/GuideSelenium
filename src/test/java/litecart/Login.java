package litecart;

import litecart.WebDriverSettings;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class Login extends WebDriverSettings {

    private String urlLoginPage = "http://localhost//litecart/admin/login.php";

    private By inputUsername = By.name("username");
    private By inputPassword = By.name("password");
    private By checkboxRememberMe = By.name("remember_me");
    private By buttonLogin = By.name("login");

 @Test
    public void login() throws InterruptedException{
        driver.get(urlLoginPage);
        Thread.sleep(3000);
        driver.findElement(inputUsername).sendKeys("admin");
        driver.findElement(inputPassword).sendKeys("admin");
        driver.findElement(checkboxRememberMe).click();
        driver.findElement(buttonLogin).click();
    }
}
