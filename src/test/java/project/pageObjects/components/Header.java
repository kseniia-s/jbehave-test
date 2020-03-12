package project.pageObjects.components;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import project.pageObjects.BaseItem;
import project.pageObjects.components.popups.Cart;
import project.settings.Browser;

import static project.helpers.WaitHelpers.waitForPageSourcesToBeCompletelyLoaded;
import static project.helpers.WaitHelpers.waitPageToBeCompletelyLoaded;

public class Header extends BaseItem {

    private By loginLink = By.xpath("//a[contains(@class,'header-topline__user-link')]");
    private By cartIcon = By.xpath("//li[contains(@class,'type_cart')]");
    private By cart = By.xpath("//div[@modaloverlay and contains(@class,'cart-modal')]");
    private By productsCatalogButton = By.xpath("//button[@class='menu-toggler']");
    private By searchField = By.xpath("//input[@search-input]");
    private By searchButton = By.xpath("//button[contains(@class,'search-form__submit')]");


    public Header(WebElement root) {
        super(root);
        waitPageToBeCompletelyLoaded();
        waitForPageSourcesToBeCompletelyLoaded();
    }

    public void searchByKeyword(String searchingKeyword){
        root.findElement(searchField).sendKeys(searchingKeyword);
    }

    public void clickOnButtonByName(String buttonName) {
        switch (buttonName.toLowerCase().trim()) {
            case "log in to your account":
                root.findElement(loginLink).click();
                break;
            case "каталог товаров":
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
        waitForPageSourcesToBeCompletelyLoaded();
        return root.findElement(loginLink).getText().trim().equals(userName);
    }

    public Cart openCart(){
        root.findElement(cartIcon).click();
        return new Cart(Browser.getDriver().findElement(cart));
    }
}
