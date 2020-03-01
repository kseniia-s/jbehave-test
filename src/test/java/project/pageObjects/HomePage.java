package project.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static project.helpers.WaitHelpers.waitDefaultTimeToWait;
import static project.helpers.WaitHelpers.waitForPageSourceToBeCompletelyLoaded;
import static project.helpers.WebElementExtensions.focusOnElement;

public class HomePage extends BasePage {

    @FindBy(xpath = "//span[contains(@class,'exponea-close ')]")
    private List<WebElement> closeAddButton;

    @FindBy(xpath = "//ul[contains(@class,'menu-categories_type_main')]//li[@class='menu-categories__item']")
    private List<WebElement> mainCategoriesList;

    @FindBy(xpath = "//li[contains(@class,'menu-categories__item_state_hovered')]//div[@class='menu__main-cats']//a[@class='menu__hidden-title']")
    private List<WebElement> categoriesList;

    public HomePage() {
        super();
        closeAddsIfShown();
    }

    private void closeAddsIfShown() {
        waitForPageSourceToBeCompletelyLoaded();
        closeAddButton.stream().findFirst().ifPresent(WebElement::click);
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
}
