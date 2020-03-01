package project.pageObjects.components;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import project.pageObjects.BaseItem;

import static project.helpers.WaitHelpers.waitForPageSourceToBeCompletelyLoaded;

public class Header extends BaseItem {

    private By loginLink = By.xpath("//a[contains(@class,'header-topline__user-link')]");
    private By productsCatalogButton = By.xpath("//button[@class='menu-toggler']");
    private By searchField = By.xpath("//input[@search-input]");
    private By searchButton = By.xpath("//button[contains(@class,'search-form__submit')]");

    public Header(WebElement root) {
        super(root);
    }

    public void searchByKeyword(String searchingKeyword){
        root.findElement(searchField).sendKeys(searchingKeyword);
    }

    public void clickOnButtonByName(String buttonName) {
        switch (buttonName.toLowerCase().trim()) {
            case "log in to your account":
                root.findElement(loginLink).click();
                break;
            case "product catalog":
                root.findElement(productsCatalogButton).click();
                break;
            case "search":
                root.findElement(searchButton).click();
                break;
            default:
                throw new NotFoundException("The button with name '" + buttonName + "' is not exist.");
        }
    }

    public Boolean isUserLoggedIn(String userName) {
        waitForPageSourceToBeCompletelyLoaded();
        return root.findElement(loginLink).getText().trim().equals(userName);
    }
}
