package project.stepDefs;

import org.assertj.core.api.Assertions;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import project.pageObjects.BasePage;
import project.pageObjects.ProductsPage;
import project.settings.ScenarioContext;

import java.util.List;

public class SearchStepDef extends Steps {
    @When("user types $searchingKeyword in the search field")
    public void userTypesWordInTheSearchField(String searchingKeyword) {
        BasePage page = ScenarioContext.context().getCurrentPage();
        page.getHeader().searchByKeyword(searchingKeyword);
    }

//    @Then("each product has a keyword $searchingKeyword in a short description")
//    public void eachProductHasAKeywordSearchingKeywordInAShortDescription(String searchingKeyword) {
//
//    }

    @Then("the products are shown")
    public void theProductsAreShown() {
        List<WebElement> products = ((ProductsPage)ScenarioContext.context().getCurrentPage()).getProductsList();
        Assertions.assertThat(products.size()).isGreaterThan(0);
    }
}
