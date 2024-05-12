package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private static By btnCheckout() {
        return By.id("checkout");
    }

    private static By cartItems(){
        return By.xpath("//div[@class='cart_item']");
    }

    private static By Quantity(){
        return By.xpath("//div[@class='cart_item']/div[@class='cart_quantity']");
    }

    private static By removeItem(){
        return By.xpath("//div[@class='item_pricebar'][1]/button[@id='remove-sauce-labs-onesie']");
    }

    private static By btnContinueShopping(){
        return By.xpath("//div[@class='cart_footer']//button[1]");
    }

    public void CheckOut() {
        Assert.assertEquals(driver.findElements(cartItems()).size(),2);
        List<WebElement> cartElements = driver.findElements(Quantity());
        for (WebElement cartElement : cartElements) {
            Assert.assertEquals(cartElement.getText(), "1");
        }
        driver.findElement(btnCheckout()).click();
    }
}
