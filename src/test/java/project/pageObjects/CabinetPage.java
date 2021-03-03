package project.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static project.helpers.WaitHelpers.waitForPageSourcesToBeCompletelyLoaded;

public class CabinetPage extends BasePage {

    @FindBy(xpath = "//p[@class='cabinet-user__name']")
    private WebElement userName;


    public Boolean isTextShown(String text) {
        waitForPageSourcesToBeCompletelyLoaded();
        return userName.getText().trim().equals(text);
    }
}
