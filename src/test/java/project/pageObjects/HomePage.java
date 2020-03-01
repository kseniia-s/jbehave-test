package project.pageObjects;

import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import project.helpers.WaitHelpers;

import java.util.List;

import static project.helpers.WaitHelpers.waitDefaultTimeToWait;
import static project.helpers.WaitHelpers.waitForPageSourceToBeCompletelyLoaded;
import static project.helpers.WebElementExtensions.focusOnElement;

public class HomePage extends BasePage {

    @FindBy(xpath = "//a[contains(@class,'header-topline__user-link')]")
    private WebElement loginButton;

    @FindBy(xpath = "//span[contains(@class,'exponea-close ')]")
    private List<WebElement> closeAddButton;

    @FindBy(xpath = "//button[@class='menu-toggler']")
    private WebElement productsCatalogButton;

    @FindBy(xpath = "//ul[contains(@class,'menu-categories_type_main')]//li[@class='menu-categories__item']")
    private List<WebElement> mainCategoriesList;

    @FindBy(xpath = "//li[contains(@class,'menu-categories__item_state_hovered')]//div[@class='menu__main-cats']//a[@class='menu__hidden-title']")
    private List<WebElement> categoriesList;

    @FindBy(xpath = "//a[contains(@class,'header-topline__user-link')]")
    private WebElement userNameLabel;

    @FindBy(xpath = "//button[contains(@class,'search-form__submit')]")
    @CacheLookup
    private WebElement searchButton;

    public HomePage() {
        super();
        closeAddsIfShown();
    }

    private void closeAddsIfShown() {
        WaitHelpers.waitDefaultTimeToWait();
        closeAddButton.stream().findFirst().ifPresent(WebElement::click);
    }

    public void clickOnButton(String buttonName) {
        switch (buttonName.toLowerCase().trim()) {
            case "log in to your account":
                loginButton.click();
                break;
            case "product catalog":
                productsCatalogButton.click();
                break;
            case "search":
                searchButton.click();
                break;
            default:
                throw new NotFoundException("The button with name '" + buttonName + "' is not exist.");
        }
    }

    public void hoverOnMainCategory(String mainCategory) {
        WebElement element = mainCategoriesList
                .stream()
                .filter(i -> i.getText().equals(mainCategory))
                .findFirst()
                .get();
        focusOnElement(element);
        waitDefaultTimeToWait();
    }

    public void clickOnProductsCategory(String category) {
        categoriesList
                .stream()
                .filter(i -> i.getText().equals(category))
                .findFirst()
                .get()
                .click();
    }

    public Boolean isUserLoggedIn(String userName) {
        waitForPageSourceToBeCompletelyLoaded();
        return userNameLabel.getText().trim().equals(userName);
    }
}
