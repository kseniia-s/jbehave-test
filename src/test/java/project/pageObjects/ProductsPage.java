package project.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import project.helpers.WaitHelpers;
import project.pageObjects.components.FiltersPanel;

import java.util.List;

public class ProductsPage extends BasePage {

    private By addToCartIcon = By.xpath("//app-buy-button");

    @FindBy(xpath = "//aside[@class='sidebar']")
    private WebElement filtersPanel;

    @FindBy(xpath = "//li[contains(@class,'catalog-grid__cell')]")
    private List<WebElement> productsResultsList;

    public FiltersPanel getFiltersPanel() {
        WaitHelpers.waitDefaultTimeToWait();
        return new FiltersPanel(filtersPanel);
    }

    public List<WebElement> getProductsList() {
        WaitHelpers.waitDefaultTimeToWait();
        WaitHelpers.waitElementToBeClickable(productsResultsList.get(0));
        return productsResultsList;
    }

    public void addProductsToCart(Integer productsNumber) {
        for (int i = 0; i < productsNumber; i++) {
            getProductsList().get(i).findElement(addToCartIcon).click();
        }
    }
}
