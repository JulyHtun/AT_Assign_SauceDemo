package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.JavascriptExecutor;

public class InventoryPage {
    WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //elements
    @FindBy(xpath = "//input[@id='react-burger-menu-btn']")
    public WebElement txtBoxPwd;

    @FindBy(xpath = "//a[@class='bm-item-list']")
    public WebElement BurgerMenuItemList;

    @FindBy(id="inventory_sidebar_link")
    public WebElement MenuItem_AllItems;

    @FindBy(id="about_sidebar_link")
    public WebElement MenuItem_About;

    @FindBy(id="logout_sidebar_link")
    public WebElement MenuItem_Logout;

    @FindBy(id="reset_sidebar_link")
    public WebElement MenuItem_Reset;

    @FindBy(xpath = "//input[@id='react-burger-cross-btn']")
    public WebElement MenuItem_CrossBtn;

    @FindBy(xpath = ("//a[@class='shopping_cart_link']/span"))
    public WebElement shoppingCartContainer;

    @FindBy(xpath = ("//a[@class='shopping_cart_link']"))
    public WebElement ShoppingCartLink;

    @FindBy(xpath = ("//select[@class='product_sort_container']"))
    public WebElement ProductSortContainer;

    @FindBy(xpath = ("//span[@class='active_option']"))
    public WebElement productSortFilterItems;

    @FindBy(xpath = ("//Option[@value='hilo']"))
    public WebElement SortFilterItemHilo;

    @FindBy(id="menu_button_container")
    public WebElement MenuBtnContainer;

    @FindBy(id="inventory_container")
    public WebElement InventoryContainer;

    // Method to add items to cart by price
    public static void AddtoCarts(WebDriver driver, String price) {

        String script = "var items = document.querySelectorAll('.inventory_item_price'); " +
                "for (var i = 0; i < items.length; i++) { " +
                "   if (items[i].textContent.trim() === '" + price + "') { " +
                "       items[i].parentNode.querySelector('.btn_inventory').click(); " +
                "   } " +
                "}";
        ((JavascriptExecutor) driver).executeScript(script);
    }
}
