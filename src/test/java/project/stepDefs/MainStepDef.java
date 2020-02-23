package project.stepDefs;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.steps.Steps;
import org.junit.Assert;
import project.pageObjects.HomePage;
import project.pageObjects.components.popups.LoginWindow;
import project.settings.Browser;

public class MainStepDef extends Steps {
    @Given("an opened browser with an $URL")
    public void anOpenedBrowserWithABaseURL(String URL) {
        Browser.getDriver().navigate().to(URL);
    }

    @When("user fills $email and $password fields")
    public void userFillsEmailAndPasswordFields(ExamplesTable tableData) {

    }

    @When("user clicks on the $buttonName button")
    public void userClicksOnTheButtonNameButton(String buttonName) throws Exception {
        new HomePage().clickOnButton(buttonName);
    }

    @Then("popup with login form is shown")
    public void popupWithLoginFormIsShown() {
        Assert.assertTrue("The modal window with login form is not shown", new LoginWindow().isShown());
    }

    @Then("user is logged in")
    public void userIsLoggedIn() {

    }
}
