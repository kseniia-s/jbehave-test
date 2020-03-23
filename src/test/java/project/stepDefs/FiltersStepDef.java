package project.stepDefs;

import com.thoughtworks.xstream.core.util.PresortedMap;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.steps.Steps;
import project.pageObjects.HomePage;
import project.pageObjects.ProductsPage;
import project.pageObjects.components.enums.FilterTypeEnum;
import project.settings.ScenarioContext;

import java.util.Map;
import java.util.SortedMap;

public class FiltersStepDef extends Steps {

    @When("user hover on the $category category")
    public void userHoverOnTheCategory(String category) {
        ((HomePage) ScenarioContext.context().getCurrentPage()).hoverOnMainCategory(category);
    }

    @When("user clicks on the $category category")
    public void userClicksOnTheCategory(String category) {
        ((HomePage) ScenarioContext.context().getCurrentPage()).clickOnProductsCategory(category);
    }

    @SuppressWarnings("unchecked")
    @When("user applies filters: $filters")
    public void userAppliesFilters(ExamplesTable filters) {
        SortedMap<FilterTypeEnum, Object> filterMap = new PresortedMap();
        for (Map<String, String> row : filters.getRows()) {
            FilterTypeEnum ft = FilterTypeEnum.getByName(row.get("filter"));
            filterMap.put(ft, row.get("value"));
        }
        try {
            new ProductsPage().getFiltersPanel().fillFilters(filterMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Then("products are shown according to the filters")
//    public void thenProductsAreShownAccordingToTheFilters(){
//        List products = new ProductsPage().getProductsResults();
//    }
}
