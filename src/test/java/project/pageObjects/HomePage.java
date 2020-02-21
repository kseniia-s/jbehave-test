package project.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import project.helpers.WaitHelpers;

import java.util.List;

import static project.helpers.WaitHelpers.waitDefaultTimeToWait;
import static project.helpers.WebElementExtensions.focusOnElement;

public class HomePage extends BasePage {
    @FindBy(id = "//a[contains(@class,'header-topline__user-link')]")
    private WebElement loginButton;

    @FindBy(xpath = "//button[@class='menu-toggler']")
    private WebElement productsCatalogButton;

    @FindBy(xpath = "//ul[contains(@class,'menu-categories_type_main')]//li[@class='menu-categories__item']")
    private List<WebElement> mainCategoriesList;

    @FindBy(xpath = "//li[contains(@class,'menu-categories__item_state_hovered')]//div[@class='menu__main-cats']//a[@class='menu__hidden-title']")
    private List<WebElement> categoriesList;


    @FindBy(xpath = "//button[contains(@class,'search-form__submit')]")
    @CacheLookup
    private WebElement searchButton;

    public HomePage() {
    }


    public void clickOnButton(String buttonName) throws Exception {
        switch (buttonName.toLowerCase().trim()){
            case "войдите в личный кабинет":
                loginButton.click();
                break;
            case "каталог товаров":
                productsCatalogButton.click();
                break;
            case "search":
                searchButton.click();
                break;
            default:
                throw new Exception();
        }
    }

    public void hoverOnMainCategory(String mainCategory) {
        WebElement element = mainCategoriesList
                .stream()
                .filter(i->i.getText().equals(mainCategory))
                .findFirst()
                .get();
        focusOnElement(element);
        waitDefaultTimeToWait();
    }

    public void clickOnProductsCategory(String category) {
        categoriesList
                .stream()
                .filter(i->i.getText().equals(category))
                .findFirst()
                .get()
                .click();
    }
}
