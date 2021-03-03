package project.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static project.helpers.WaitHelpers.waitDefaultTimeToWait;
import static project.helpers.WaitHelpers.waitForPageSourcesToBeCompletelyLoaded;
import static project.helpers.WaitHelpers.waitPageToBeCompletelyLoaded;
import static project.helpers.WaitHelpers.waitUntilElementIsVisible;
import static project.helpers.WebElementExtensions.focusOnElement;

public class HomePage extends BasePage {

    @FindBy(xpath = "//ul[contains(@class,'menu-categories_type_main')]//li[@class='menu-categories__item']")
    private List<WebElement> mainCategoriesList;

    @FindBy(xpath = "//li[contains(@class,'menu-categories__item_state_hovered')]//div[@class='menu__main-cats']//a[@class='menu__hidden-title']")
    private List<WebElement> categoriesList;

    private By closeAddButton = By.xpath("//span[contains(@class,'exponea-close ')]");

    public HomePage() {
        waitPageToBeCompletelyLoaded();
        waitForPageSourcesToBeCompletelyLoaded();
        closeAddsIfShown();
    }

    private void closeAddsIfShown() {
        try {
            waitUntilElementIsVisible(closeAddButton).click();
        } catch (TimeoutException e) {
            //suppress
        }
    }

    public void hoverOnMainCategory(String mainCategory) {
        if ("Laptops and computers".equals(mainCategory)) {
            String mainCategoryName = "Ноутбуки та комп’ютери";
            WebElement element = mainCategoriesList
                    .stream()
                    .filter(i -> i.getText().equals(mainCategoryName))
                    .findFirst()
                    .get();
            focusOnElement(element);
        }
        waitDefaultTimeToWait();
    }

    public void clickOnProductsCategory(String category) {
        if ("Laptops".equals(category)) {
            String categoryName = "Ноутбуки";
            categoriesList
                    .stream()
                    .filter(i -> i.getText().equals(categoryName))
                    .findFirst()
                    .get()
                    .click();
        }
    }
}
