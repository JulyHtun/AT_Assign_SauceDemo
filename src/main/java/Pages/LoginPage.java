package Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //elements
    @FindBy(name = "user-name")
    public WebElement txtBoxUserName;
    @FindBy(name = "password")
    public WebElement txtBoxPwd;
    @FindBy(xpath = "//input[@id='login-button']")
    public WebElement btnLogin;

    //methods
    public void setTxtInTxtBoxUserName(String text) {
        txtBoxUserName.sendKeys(text);
    }

    public void setTxtInTxtBoxPwd(String text) {
        txtBoxPwd.sendKeys(text);
    }

    public void clickBtnLogin() {
        btnLogin.click();
    }
}
