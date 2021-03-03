package project.stepDefs;

import org.assertj.core.api.Assertions;
import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import org.openqa.selenium.WebElement;
import project.pageObjects.BasePage;
import project.pageObjects.ProductsPage;
import project.settings.ScenarioContext;

import java.util.List;

public class SearchStepDef extends Steps {
    @When("user types $searchingKeyword in the search field")
    @Alias("user types <searchingKeyword> in the search field")
    public void userTypesWordInTheSearchField(@Named("searchingKeyword") String searchingKeyword) {
        BasePage page = ScenarioContext.context().getCurrentPage();
        page.getHeader().searchByKeyword(searchingKeyword);
    }

    @Then("the products are shown")
    public void theProductsAreShown() {
        List<WebElement> products = ((ProductsPage)ScenarioContext.context().getCurrentPage()).getProductsList();
        Assertions.assertThat(products.size()).isGreaterThan(0);
    }
}
