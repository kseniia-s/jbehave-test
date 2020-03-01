package project.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import project.helpers.WaitHelpers;
import project.settings.Browser;

public abstract class BaseItem {

    protected WebElement root;

    protected BaseItem() {
    }

    protected BaseItem(WebElement root)
    {
        this.root = root;
    }

    public boolean isShown()
    {
        return root.isDisplayed() && root.isEnabled() && root.getSize().height > 0;
    }
}
