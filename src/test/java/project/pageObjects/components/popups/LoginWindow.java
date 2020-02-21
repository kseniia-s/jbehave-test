package project.pageObjects.components.popups;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import project.pageObjects.BaseItem;

public class LoginWindow extends BaseItem {

    private By loginPopup = By.id("//a[contains(@class,'header-topline__user-link')]");

    public LoginWindow(){}

    public LoginWindow(WebElement root) {
        super(root);
    }

    public WebElement getLoginPopup() {
        return root.findElement(loginPopup);
    }
}
