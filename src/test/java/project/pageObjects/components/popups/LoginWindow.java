package project.pageObjects.components.popups;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import project.helpers.WaitHelpers;
import project.pageObjects.BaseItem;
import project.settings.ScenarioContext;

public class LoginWindow extends BaseItem {

    private By emailInputField = By.xpath("//input[@formcontrolname='login']");
    private By passwordInputField = By.xpath("//input[@formcontrolname='password']");

    public LoginWindow(WebElement root) {
        super(root);
        WaitHelpers.waitDefaultTimeToWait();
    }

    public void fillLoginForm(String email, String password) {
        WebElement emailField = ScenarioContext.context().getBrowser().findElement(emailInputField);
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = ScenarioContext.context().getBrowser().findElement(passwordInputField);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickOnLogInButton() {
        ScenarioContext.context().getBrowser().findElement(By.xpath("//button[contains(text(),'Войти')]")).click();
        WaitHelpers.waitDefaultTimeToWait();
    }
}
