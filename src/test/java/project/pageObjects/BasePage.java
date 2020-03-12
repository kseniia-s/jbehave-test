package project.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import project.pageObjects.components.Header;
import project.pageObjects.components.popups.Cart;
import project.pageObjects.components.popups.LoginWindow;
import project.settings.Browser;
import project.settings.ScenarioContext;

import static project.helpers.WaitHelpers.*;

public abstract class BasePage {

    private By header = By.tagName("header");
    private By loginPopup = By.xpath("//div[contains(@class,'modal__holder')]");
    private By cartPopup = By.xpath("//div[@modaloverlay and contains(@class,'cart-modal')]");

    BasePage() {
        waitPageToBeCompletelyLoaded();
        waitForPageSourcesToBeCompletelyLoaded();
        PageFactory.initElements(Browser.getDriver(), this);
        ScenarioContext.context().setCurrentPage(this);
    }

    public Header getHeader() {
        return new Header(Browser.getDriver().findElement(header));
    }

    public LoginWindow getLoginPopup() {
        return new LoginWindow(Browser.getDriver().findElement(loginPopup));
    }

    public Cart getCart() {
        return new Cart(waitUntilElementIsVisible(cartPopup));
    }
}