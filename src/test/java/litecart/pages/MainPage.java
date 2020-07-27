package litecart.pages;

import litecart.WebDriverSettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPage extends Page {

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[class *= 'product column']")
    public List<WebElement> products;

    @FindBy(css = "a[class = 'link'][href *= 'checkout']")
    public WebElement openBasket;

}
