package project.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import project.helpers.WaitHelpers;
import project.pageObjects.components.FiltersPanel;

import java.util.List;

public class ProductsPage extends BasePage{

    @FindBy(xpath = "//aside[@class='sidebar']")
    private WebElement filtersPanel;

    @FindBy(xpath = "")
    private List<WebElement> productsResultsList;

    public FiltersPanel getFiltersPanel()
    {
        WaitHelpers.waitDefaultTimeToWait();

        return new FiltersPanel(filtersPanel);
    }

//    public List<ProductTile> getProductsResults()
//    {
//        return
//    }
}
