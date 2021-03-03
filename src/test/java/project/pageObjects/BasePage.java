package project.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import project.pageObjects.components.Header;
import project.pageObjects.components.popups.Cart;
import project.pageObjects.components.popups.LoginWindow;
import project.settings.ScenarioContext;

import static project.helpers.WaitHelpers.waitForPageSourcesToBeCompletelyLoaded;
import static project.helpers.WaitHelpers.waitPageToBeCompletelyLoaded;
import static project.helpers.WaitHelpers.waitUntilElementIsVisible;

public abstract class BasePage {

    private By header = By.tagName("header");
    private By loginPopup = By.xpath("//div[contains(@class,'modal__holder')]");
    private By cartPopup = By.xpath("//single-modal-window/div[@class='modal__holder modal__holder_show_animation modal__holder_size_large']");

    BasePage() {
        waitPageToBeCompletelyLoaded();
        waitForPageSourcesToBeCompletelyLoaded();
        PageFactory.initElements(ScenarioContext.context().getBrowser(), this);
        ScenarioContext.context().setCurrentPage(this);
    }

    public Header getHeader() {
        return new Header(ScenarioContext.context().getBrowser().findElement(header));
    }

    public LoginWindow getLoginPopup() {
        return new LoginWindow(ScenarioContext.context().getBrowser().findElement(loginPopup));
    }

    public Cart getCart() {
        return new Cart(waitUntilElementIsVisible(cartPopup));
    }
}