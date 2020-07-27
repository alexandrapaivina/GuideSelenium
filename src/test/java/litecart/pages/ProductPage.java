package litecart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends Page{

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[name = 'add_cart_product']")
    public WebElement addToBascket;

    public WebElement countBascket(){
        return driver.findElement(By.cssSelector("span[class = 'quantity']"));
    }

    public Select sizeProduct(){
        return new Select(driver.findElement(By.cssSelector("[name *= 'options']")));
    }

}
