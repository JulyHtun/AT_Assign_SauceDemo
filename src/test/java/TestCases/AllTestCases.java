package TestCases;

import BaseDriver.DriverFactory;

import Pages.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AllTestCases extends DriverFactory {

    @Test(description = "Check user can login successfully ")
    public void TestCase_SuccessfulLogin() throws InterruptedException {
        try {
            //open browser
            setUpBrowser("Chrome", "https://www.saucedemo.com/");
            Thread.sleep(2000);

            //Login page
            LoginPage loginPageFactory = new LoginPage(driver);
            //login
            loginPageFactory.setTxtInTxtBoxUserName("standard_user");
            loginPageFactory.setTxtInTxtBoxPwd("secret_sauce");
            loginPageFactory.clickBtnLogin();
            Thread.sleep(3000);

            Assert.assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());

        } finally {
            closeBrowser();
        }
    }

    @Test(description = "Sorting the Price from High to Low ")
    public void TestCase_SortFromHighToLow() throws InterruptedException {
        try {
            //open browser
            setUpBrowser("Chrome", "https://www.saucedemo.com/");
            Thread.sleep(2000);

            //Login page
            LoginPage loginPageFactory = new LoginPage(driver);

            //login
            loginPageFactory.setTxtInTxtBoxUserName("standard_user");
            loginPageFactory.setTxtInTxtBoxPwd("secret_sauce");
            loginPageFactory.clickBtnLogin();
            Thread.sleep(3000);

            //Inventory page
            InventoryPage inventoryFactory = new InventoryPage(driver);

            //Inventory
            WebElement element = driver.findElement(By.xpath(".//select[@class='product_sort_container']"));
            Select sel = new Select(element);
            sel.selectByVisibleText("Price (high to low)");
            Thread.sleep(3000);

            List<WebElement> sortedItems = driver.findElements(By.className("inventory_item_price"));
            List<Double> prices = new ArrayList<>();

            for (WebElement item : sortedItems) {
                // Identify separate div
                String priceText = item.getText().replace("$", "");
                double price = Double.parseDouble(priceText);
                prices.add(price);
            }

            // Check if prices are sorted in descending order
            List<Double> sortedPrices = new ArrayList<>(prices);
            Collections.sort(sortedPrices, Collections.reverseOrder());
            Assert.assertEquals(prices, sortedPrices);

        } finally {
            closeBrowser();
        }
    }

    @Test(description = "Add to Cart the Items which has $15.99 price")
    public void TestCase_AddtoCartItems() throws InterruptedException {
        try {
            //open browser
            setUpBrowser("Chrome", "https://www.saucedemo.com/");
            Thread.sleep(2000);

            //Login page
            LoginPage loginPageFactory = new LoginPage(driver);
            //login
            loginPageFactory.setTxtInTxtBoxUserName("standard_user");
            loginPageFactory.setTxtInTxtBoxPwd("secret_sauce");
            loginPageFactory.clickBtnLogin();
            Thread.sleep(3000);

            //Inventory page
            InventoryPage inventoryFactory = new InventoryPage(driver);
            //Inventory
            WebElement element = driver.findElement(By.xpath(".//select[@class='product_sort_container']"));
            Select sel = new Select(element);
            sel.selectByVisibleText("Price (low to high)");
            Thread.sleep(3000);

            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("window.scrollTo(0,document.body.scrollHeight);");
            Thread.sleep(3000);

            inventoryFactory.AddtoCarts(driver, "$15.99");
            Thread.sleep(3000);

            inventoryFactory.ShoppingCartLink.click();
            Thread.sleep(2000);

            // Check if any item is added to the cart
            WebElement cartContainer = driver.findElement(By.className("cart_list"));
            List<WebElement> cartItems = cartContainer.findElements(By.className("cart_item"));

            Assert.assertTrue(cartItems.size() > 0);

        } finally {
            closeBrowser();
        }
    }

    @Test(description = "log the CheckOut Summary")
    public void TestCase_CheckOut() throws InterruptedException {
        try {
            //open browser
            setUpBrowser("Chrome", "https://www.saucedemo.com/");
            Thread.sleep(2000);

            //Login page
            LoginPage loginPageFactory = new LoginPage(driver);
            //login
            loginPageFactory.setTxtInTxtBoxUserName("standard_user");
            loginPageFactory.setTxtInTxtBoxPwd("secret_sauce");
            loginPageFactory.clickBtnLogin();
            Thread.sleep(3000);

            //Inventory page
            InventoryPage inventoryFactory = new InventoryPage(driver);

            //Inventory
            WebElement element = driver.findElement(By.xpath(".//select[@class='product_sort_container']"));
            Select sel = new Select(element);
            sel.selectByVisibleText("Price (low to high)");
            Thread.sleep(3000);

            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("window.scrollTo(0,document.body.scrollHeight);");
            Thread.sleep(3000);

            inventoryFactory.AddtoCarts(driver, "$15.99");
            Thread.sleep(3000);

            inventoryFactory.ShoppingCartLink.click();
            Thread.sleep(2000);

            // Check if any item is added to the cart
            WebDriverWait wait = new WebDriverWait(driver, 30); // Adjust the timeout as needed
            WebElement cartContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cart_list")));
            List<WebElement> cartItems = cartContainer.findElements(By.className("cart_item"));

            // Assert that at least one item is added to the cart
            Assert.assertTrue(cartItems.size() > 0, "No items are added to the cart");

            // Log the checkout summary
            System.out.println("Checkout Summary:");
            for (WebElement item : cartItems) {
                String itemName = item.findElement(By.className("inventory_item_name")).getText();
                String itemDescription = item.findElement(By.className("inventory_item_desc")).getText();
                String itemPrice = item.findElement(By.className("inventory_item_price")).getText();
                System.out.println("Name: " + itemName + ", Description: " + itemDescription + ", Price: " + itemPrice);
            }

            // Method to log the checkout summary
            CartPage cartFactory = new CartPage(driver);
            cartFactory.CheckOut();
            Thread.sleep(2000);

            CheckOutStep1 step1Factory = new CheckOutStep1(driver);
            //CheckOut
            step1Factory.setTxtInFirstName("standard");
            step1Factory.setTxtInLastName("user");
            step1Factory.setTxtInPostalCode("11061");
            step1Factory.clickBtnContinue();
            Thread.sleep(3000);

            Assert.assertEquals("https://www.saucedemo.com/checkout-step-two.html", driver.getCurrentUrl());

            jse = (JavascriptExecutor) driver;
            jse.executeScript("window.scrollTo(0,document.body.scrollHeight);");
            Thread.sleep(3000);

            WebElement btnFinish = driver.findElement(By.xpath("//button[@id='finish']"));
            btnFinish.click();
            Thread.sleep(3000);

            Assert.assertEquals("https://www.saucedemo.com/checkout-complete.html", driver.getCurrentUrl());
            Thread.sleep(3000);

            WebElement checkOutCompletePage =  driver.findElement(By.xpath("//span[@class='title' and contains(text(),'Complete')]"));
            WebElement checkOutCompleteSuccessText = driver.findElement(By.xpath("//h2[@class='complete-header']"));

            Assert.assertEquals(checkOutCompleteSuccessText.getText(), "Thank you for your order!", "The other placed successful message");
            Thread.sleep(3000);

            } finally {
            closeBrowser();
        }
    }

    @Test(description = "Validate the Locked_out_user")
    public void TestCase_LockedOutUserValidation() throws InterruptedException {
        try {
            //open browser
            setUpBrowser("Chrome", "https://www.saucedemo.com/");
            Thread.sleep(2000);

            //Login page
            LoginPage loginPageFactory = new LoginPage(driver);
            //login
            loginPageFactory.setTxtInTxtBoxUserName("locked_out_user");
            loginPageFactory.setTxtInTxtBoxPwd("secret_sauce");
            loginPageFactory.clickBtnLogin();
            Thread.sleep(3000);

            // Check if error message is displayed
            WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));
            String errorMessageText = errorMessage.getText();

            Assert.assertEquals(errorMessageText,"Epic sadface: Sorry, this user has been locked out.");

            Assert.assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());

        } finally {
            closeBrowser();
        }
    }
}
