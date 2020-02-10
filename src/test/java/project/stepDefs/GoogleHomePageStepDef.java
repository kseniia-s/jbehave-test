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

    @Given("an opened browser with a base URL")
    public void anOpenedBrowserWithABaseURL() {
        Browser.getDriver().navigate().to("https://www.google.com/");
    }

    @When("user searches by keyword $Automation")
    public void userSearchesByKeyword(String keyword){
        new GoogleHomePage().Search(keyword);
    }

    @Then("the word $Automation exists in all search items’ titles on the page")
    public void resultsForACategoryAndKeywordInARegion(String keyword){
        Boolean listIsFall = new GoogleResultsPage().isTextPresent(keyword);
        Assert.assertTrue(listIsFall);
    }
}
