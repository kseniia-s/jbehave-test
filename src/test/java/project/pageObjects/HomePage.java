package project.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static project.helpers.WaitHelpers.*;
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
        try{
            waitUntilElementIsVisible(closeAddButton).click();
        } catch (TimeoutException e){
            //suppress
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
}
