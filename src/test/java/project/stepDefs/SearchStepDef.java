package project.stepDefs;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import project.pageObjects.components.Header;

public class SearchStepDef extends Steps {
    @When("user writes $searchingKeyword in the search field")
    public void userWritesWordInTheSearchField(String searchingKeyword) {
        new Header().searchByKeyword(searchingKeyword);
    }

    @Then("each product has a keyword $searchingKeyword in a short description")
    public void eachProductHasAKeywordSearchingKeywordInAShortDescription(String searchingKeyword) {

    }

    @Then("the products are shown")
    public void theProductsAreShown() {

    }
}
