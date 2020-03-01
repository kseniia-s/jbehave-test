package project.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import project.pageObjects.components.Header;
import project.pageObjects.components.popups.LoginWindow;
import project.settings.Browser;
import project.settings.ScenarioContext;

import static project.helpers.WaitHelpers.waitForPageSourceToBeCompletelyLoaded;

public abstract class BasePage {

    private By header = By.tagName("header");
    private By loginPopup = By.xpath("//div[contains(@class,'modal__holder')]");

    BasePage() {
        waitForPageSourceToBeCompletelyLoaded();
        PageFactory.initElements(Browser.getDriver(), this);
        ScenarioContext.context().setCurrentPage(this);
    }

    public Header getHeader() {
        return new Header(Browser.getDriver().findElement(header));
    }

    public LoginWindow getLoginPopup() {
        return new LoginWindow(Browser.getDriver().findElement(loginPopup));
    }
}