package litecart.tests;

import litecart.TestBase;
import org.testng.annotations.Test;

public class WorkingBasket extends TestBase {

    @Test
    public void workingWithBasket() throws InterruptedException {
        app.open();

        for(int i=1;i<4;i++){
            app.openFirstProduct();
            app.addToBasket();
            app.back();
        }
        app.openBascket();
        app.removeAllProductsInBascket();
    }
}
