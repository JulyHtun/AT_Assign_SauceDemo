package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutStep1 {
    WebDriver driver;

    public CheckOutStep1(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //elements
    @FindBy(id = "first-name")
    public WebElement txtBoxFirstName;
    @FindBy(id = "last-name")
    public WebElement txtBoxLastName;
    @FindBy(id = "postal-code")
    public WebElement txtBoxPostalCode;
    @FindBy(xpath = "//input[@id='cancel']")
    public WebElement btnCancel;
    @FindBy(xpath = "//input[@id='continue']")
    public WebElement btnContinue;

    //methods
    public void setTxtInFirstName(String text) {
        txtBoxFirstName.sendKeys(text);
    }

    public void setTxtInLastName(String text) {
        txtBoxLastName.sendKeys(text);
    }

    public void setTxtInPostalCode(String text) {
        txtBoxPostalCode.sendKeys(text);
    }

    public void clickBtnContinue() {
        btnContinue.click();
    }

    public void clickBtnCancel() {
        btnCancel.click();
    }
}
