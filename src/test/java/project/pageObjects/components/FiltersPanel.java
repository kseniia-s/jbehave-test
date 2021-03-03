package project.pageObjects.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import project.helpers.WaitHelpers;
import project.pageObjects.BaseItem;
import project.pageObjects.components.enums.FilterTypeEnum;
import project.settings.ScenarioContext;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FiltersPanel extends BaseItem {

    private By manufacturers = By.xpath("//div[@data-filter-name=\"producer\"]//li[@class='checkbox-filter__item']//a/ancestor::li[1]");
    private By minPrice = By.xpath("//input[@formcontrolname='min']");
    private By maxPrice = By.xpath("//input[@formcontrolname='max']");
    private By applyPriceButton = By.xpath("//div[contains(@class,'scrollbar')]//form//button[@type='submit']");
    private By cpuData = By.xpath("//div[@data-filter-name=\"processor\"]//following-sibling::div//li");
    private By ram = By.xpath("//span[contains(text(),\"Обсяг оперативної пам'яті\")]//parent::button//following-sibling::div//li");
    private By os = By.xpath("//span[contains(text(),'Операційна система')]//parent::button//following-sibling::div//li");

    public FiltersPanel(WebElement root) {
        super(root);
    }

    private List<WebElement> getManufacturersList() {
        return root.findElements(manufacturers);
    }

    private List<WebElement> getCpuDataList() {
        return root.findElements(cpuData);
    }

    private List<WebElement> getRamDataList() {
        return root.findElements(ram);
    }

    private List<WebElement> getOsDataList() {
        return root.findElements(os);
    }

    private void clearMinPriceField() {
        ScenarioContext.context().getBrowser().findElement(minPrice).clear();
    }

    private void clearMaxPriceField() {
        ScenarioContext.context().getBrowser().findElement(maxPrice).clear();
    }

    private void setMinPrice(String price) {
        ScenarioContext.context().getBrowser().findElement(minPrice).sendKeys(price);
    }

    private void setMaxPrice(String price) {
        ScenarioContext.context().getBrowser().findElement(maxPrice).sendKeys(price);
    }

    private void applyPriceFilter() {
        root.findElement(applyPriceButton).click();
    }

    public void fillFilters(Map<FilterTypeEnum, Object> filterValues) {
        filterValues.forEach((k, v) -> {
            switch (k) {
                case MANUFACTURER:
                    Optional<WebElement> found = Optional.empty();
                    for (WebElement manufacturer : getManufacturersList()) {
                        if (manufacturer.getText().contains(v.toString())) {
                            found = Optional.of(manufacturer);
                            break;
                        }
                    }
                    found
                            .orElseThrow(() -> new RuntimeException("No value found. Value: " + v))
                            .click();
//                    getManufacturersList()
//                            .stream()
//                            .filter(i->i.getText().equals(v))
//                            .findFirst()
//                            .orElseThrow(() -> new RuntimeException("No value found "+ v))
//                            .click();
//                    WaitHelpers.waitDefaultTimeToWait();
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
                            .filter(i -> i.getText().contains(v.toString()))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("No value found " + v))
                            .click();
                    WaitHelpers.waitDefaultTimeToWait();
                    break;
                case RAM:
                    getRamDataList()
                            .stream()
                            .filter(i -> i.getText().contains(v.toString()))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("No value found " + v))
                            .click();
                    WaitHelpers.waitDefaultTimeToWait();
                    break;
                case OS:
                    getOsDataList()
                            .stream()
                            .filter(i -> i.getText().contains(v.toString()))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("No value found " + v))
                            .click();
                    WaitHelpers.waitDefaultTimeToWait();
                    break;
                default:
                    throw new RuntimeException("Unknown filter name");
            }
        });
    }
}
