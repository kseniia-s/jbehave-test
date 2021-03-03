package project.pageObjects.components;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import project.pageObjects.BaseItem;
import project.pageObjects.components.popups.Cart;

import static project.helpers.WaitHelpers.waitForPageSourcesToBeCompletelyLoaded;
import static project.helpers.WaitHelpers.waitPageToBeCompletelyLoaded;
import static project.helpers.WaitHelpers.waitUntilElementIsVisible;

public class Header extends BaseItem {

    private By loginLink = By.xpath("(//ul[@class='header-actions']//button[@class='header__button'])[1]");
    private By cartIcon = By.xpath("(//ul[@class='header-actions']//button[@class='header__button'])[2]");
    private By cabinetMenuButton = By.xpath("(//a[@class='header__button'])[1]");
    private By cartPopup = By.xpath("//div[contains(@class,'modal__holder')]");
    private By productsCatalogButton = By.xpath("//button[@id='fat-menu']");
    private By searchField = By.xpath("//input[@search-input]");
    private By searchButton = By.xpath("//button[contains(@class,'search-form__submit')]");


    public Header(WebElement root) {
        super(root);
        waitPageToBeCompletelyLoaded();
        waitForPageSourcesToBeCompletelyLoaded();
    }

    public void searchByKeyword(String searchingKeyword) {
        root.findElement(searchField).sendKeys(searchingKeyword);
    }

    public void clickOnButtonByName(String buttonName) {
        switch (buttonName.toLowerCase().trim()) {
            case "log in to your account":
                root.findElement(loginLink).click();
                break;
            case "products catalog":
                root.findElement(productsCatalogButton).click();
                break;
            default:
                throw new NotFoundException("The button with name '" + buttonName + "' is not exist.");
        }
    }

    public void clickSearchButton() {
        root.findElement(searchButton).click();
    }

    public Cart openCart() {
        root.findElement(cartIcon).click();
        waitForPageSourcesToBeCompletelyLoaded();
        return new Cart(waitUntilElementIsVisible(cartPopup));
    }

    public void clickOnCabinetMenuButton() {
        root.findElement(cabinetMenuButton).click();
    }
}
