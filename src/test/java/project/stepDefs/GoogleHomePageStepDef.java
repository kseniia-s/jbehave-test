package project.stepDefs;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;
import project.pageObjects.GoogleHomePage;
import project.pageObjects.GoogleResultsPage;
import project.settings.Browser;


public class GoogleHomePageStepDef extends Steps {

    @When("user searches by keyword $keyword")
    public void userSearchesByKeyword(String keyword){
        new GoogleHomePage().Search(keyword);
    }

    @Then("the word $Automation exists in all search items’ titles on the page")
    public void resultsForACategoryAndKeywordInARegion(String keyword){
        Boolean listIsFall = new GoogleResultsPage().isTextPresent(keyword);
        Assert.assertTrue(listIsFall);
    }
}
