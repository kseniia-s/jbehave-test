package project.pageObjects;

import org.openqa.selenium.WebElement;

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
