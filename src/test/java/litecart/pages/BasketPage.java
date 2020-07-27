package litecart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BasketPage extends Page{

    public BasketPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button[name *= 'remove']")
    public List<WebElement> remout;

    @FindBy(css = "[href = '#']")
    public WebElement product;

    @FindBy(css = "button[name *= 'remove']")
    public WebElement firstRemout;

    public WebElement table(){
        return driver.findElement(By.cssSelector("div[id *= 'order']"));
    }

}
