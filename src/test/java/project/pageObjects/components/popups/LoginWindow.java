package project.pageObjects.components.popups;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import project.helpers.WaitHelpers;
import project.pageObjects.BaseItem;
import project.settings.Browser;

public class LoginWindow extends BaseItem {

    private By emailInputField = By.xpath("//input[@formcontrolname='login']");
    private By passwordInputField = By.xpath("//input[@formcontrolname='password']");
    private By loginPopup = By.xpath("//div[contains(@class,'modal__holder')]");

    public LoginWindow() {
        WaitHelpers.waitUntilElementIsVisible(loginPopup);
    }

    public LoginWindow(WebElement root) {
        super(root);
    }

    public WebElement getLoginPopup() {
        return Browser.getDriver().findElement(loginPopup);
    }

    public void fillLoginForm(String email, String password) {
        WebElement emailField = Browser.getDriver().findElement(emailInputField);
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = Browser.getDriver().findElement(passwordInputField);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickOnLogInButton() {
        Browser.getDriver().findElement(By.xpath("//button[contains(text(),'Войти')]")).click();
        WaitHelpers.waitDefaultTimeToWait();
    }
}
