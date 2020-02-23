package project.pageObjects.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import project.helpers.WaitHelpers;
import project.pageObjects.BaseItem;
import project.pageObjects.components.enums.FilterTypeEnum;
import project.settings.Browser;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FiltersPanel extends BaseItem {

    private By manufacturers = By.xpath("//ctg-filter-section-checkbox//li[@class='checkbox-filter__item']//a/ancestor::li[1]");
    private By minPrice = By.xpath("//input[@formcontrolname='min']");
    private By maxPrice = By.xpath("//input[@formcontrolname='max']");
    private By cpuData = By.xpath("//div[@appfilterroot]//button[contains(text(),'Процессор')]//following-sibling::div//li");
    private By ram = By.xpath("//div[@appfilterroot]//button[contains(text(),'Объем оперативной памяти')]//following-sibling::div//li");
    private By os = By.xpath("//div[@appfilterroot]//button[contains(text(),'Операционная система')]//following-sibling::div//li");
    private By ramType = By.xpath("//div[@appfilterroot]//button[contains(text(),'Тип оперативной памяти')]//following-sibling::div//li");

    private By ramTypeButton = By.xpath("//div[@appfilterroot]//button[contains(text(),'Тип оперативной памяти')]");
    private By applyPriceButton = By.xpath("//div[contains(@class,'scrollbar')]//form//button[@type='submit']");

    public FiltersPanel(WebElement root) {
        super(root);
    }

    private List<WebElement> getManufacturersList()
    {
        return root.findElements(manufacturers);
    }

    private List<WebElement> getCpuDataList()
    {
        return root.findElements(cpuData);
    }

    private List<WebElement> getRamDataList()
    {
        return root.findElements(ram);
    }

    private List<WebElement> getOsDataList()
    {
        return root.findElements(os);
    }

    private List<WebElement> getRamTypesList()
    {
        Browser.getDriver().findElement(ramTypeButton).click();
        WaitHelpers.waitDefaultTimeToWait();

        return root.findElements(ramType);
    }


    private void clearMinPriceField(){
        Browser.getDriver().findElement(minPrice).clear();
    }

    private void clearMaxPriceField(){
        Browser.getDriver().findElement(maxPrice).clear();
    }

    private void setMinPrice(String price){
        Browser.getDriver().findElement(minPrice).sendKeys(price);
    }

    private void setMaxPrice(String price){
        Browser.getDriver().findElement(maxPrice).sendKeys(price);
    }

    private void applyPriceFilter()
    {
        root.findElement(applyPriceButton).click();
    }

    public void fillFilters(Map<FilterTypeEnum, Object> filterValues) {
        filterValues.forEach((k,v) -> {
            switch (k) {
                case MANUFACTURER:
                    Optional<WebElement> found = Optional.empty();
                    for (WebElement webElement : getManufacturersList()) {
                        if (webElement.getText().contains(v.toString())) {
                            found = Optional.of(webElement);
                            break;
                        }
                    }
                    found
                            .orElseThrow(() -> new RuntimeException("No value found "+ v))
                            .click();
//                    getManufacturersList()
//                            .stream()
//                            .filter(i->i.getText().equals(v))
//                            .findFirst()
//                            .orElseThrow(() -> new RuntimeException("No value found "+ v))
//                            .click();
//                    WaitHelpers.waitDefaultTimeToWait();
                    break;
                case MANUFACTURER_SEARCH:
                    // find search and type
                    break;
                case PRICE_MIN:
                    clearMinPriceField();
                    setMinPrice(v.toString());
                    break;
                case PRICE_MAX:
                    clearMaxPriceField();
                    setMaxPrice(v.toString());
                    applyPriceFilter();
                    WaitHelpers.waitDefaultTimeToWait();
                    break;
                case CPU:
                    getCpuDataList()
                            .stream()
                            .filter(i->i.getText().contains(v.toString()))
                            .findFirst()
                            .orElseThrow(()->new RuntimeException("No value found "+ v))
                            .click();
                    WaitHelpers.waitDefaultTimeToWait();
                    break;
                case RAM:
                    getRamDataList()
                            .stream()
                            .filter(i->i.getText().contains(v.toString()))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("No value found "+ v))
                            .click();
                    WaitHelpers.waitDefaultTimeToWait();
                    break;
                case OS:
                    getOsDataList()
                            .stream()
                            .filter(i->i.getText().contains(v.toString()))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("No value found "+ v))
                            .click();
                    WaitHelpers.waitDefaultTimeToWait();
                    break;
                case RAM_TYPE:
                    getRamTypesList()
                            .stream()
                            .filter(i->i.getText().contains(v.toString()))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("No value found "+ v))
                            .click();
                    break;
                default:
                    throw new RuntimeException("Unknown filter name");
            }
        });
    }
}
